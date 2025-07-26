package com.oracle.kyc.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.kyc.dao.KycDAO;
import com.oracle.kyc.dto.AccountRequestDTO;
import com.oracle.kyc.dto.AccountResponseDTO;
import com.oracle.kyc.dto.CustomerResponseDTO;
import com.oracle.kyc.dto.KycDTO;
import com.oracle.kyc.exception.KycNotFoundException;
import com.oracle.kyc.model.Kyc;
import com.oracle.kyc.proxy.AccountServiceProxy;
import com.oracle.kyc.proxy.CustomerServiceProxy;
import com.oracle.kyc.util.KycMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class KycServiceImpl implements KycService {

    private static final Logger logger = LoggerFactory.getLogger(KycServiceImpl.class);

    @Autowired
    private KycDAO kycDAO;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CustomerServiceProxy customerServiceProxy;

    @Autowired
    private AccountServiceProxy accountServiceProxy;

    @Override
    public KycDTO saveKyc(KycDTO kycDTO) {
        Kyc kyc = KycMapper.toEntity(kycDTO);
        kyc.setKycStatus("PENDING");
        Kyc saved = kycDAO.saveKyc(kyc);
        return KycMapper.toDto(saved);
    }

    @Override
    public KycDTO getKycByCustomerId(String customerId) {
        Kyc kyc = kycDAO.getKycByCustomerId(customerId);
        if (kyc == null) throw new KycNotFoundException(customerId);
        return KycMapper.toDto(kyc);
    }

    @Override
    public List<KycDTO> getAllKycs() {
        return kycDAO.getAllKycs().stream().map(KycMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public KycDTO updateKyc(String customerId, KycDTO kycDTO) {
        Kyc existing = kycDAO.getKycByCustomerId(customerId);
        if (existing == null) throw new KycNotFoundException(customerId);

        Kyc updated = KycMapper.toEntity(kycDTO);
        updated.setCustomerId(customerId);
        Kyc saved = kycDAO.updateKyc(updated);
        return KycMapper.toDto(saved);
    }

    @Override
    public void deleteKyc(String customerId) {
        Kyc existing = kycDAO.getKycByCustomerId(customerId);
        if (existing == null) throw new KycNotFoundException(customerId);
        kycDAO.deleteKyc(customerId);
    }

    @Override
    public void reviewKyc(String customerId, String status, String remark) {
        Kyc kyc = kycDAO.getKycByCustomerId(customerId);
        if (kyc == null) throw new KycNotFoundException(customerId);

        kyc.setKycStatus(status.toUpperCase());
        kyc.setAdminRemark(remark);
        kycDAO.updateKyc(kyc);

        CustomerResponseDTO customer = customerServiceProxy.getCustomerById(customerId);

        if ("REJECTED".equalsIgnoreCase(status)) {
            emailService.sendKycRejectionEmail(customer.getEmail(), customerId, remark);
        } else if ("VERIFIED".equalsIgnoreCase(status)) {
            logger.info("KYC VERIFIED for customerId={}, starting account creation process...", customerId);

            try {
                AccountRequestDTO accountRequest = new AccountRequestDTO(customerId);
                logger.info("Account creation request payload: customerId={}, accountType={}",
                        accountRequest.getCustomerId(), accountRequest.getAccountType());

                AccountResponseDTO response = accountServiceProxy.createAccount(accountRequest);

                logger.info("Account created for customerId={}: accountNumber={}, accountStatus={}, message={}",
                        customerId,
                        response.getAccountNumber(),
                        response.getAccountStatus(),
                        response.getMessage());

                // Optional: emailService.sendAccountCreationEmail(customer.getEmail(), response.getAccountNumber());

            } catch (Exception e) {
                logger.error("ERROR creating account for customerId={}: {}", customerId, e.getMessage(), e);
            }
        }
    }
}
