package com.oracle.kyc.service;

import com.oracle.kyc.dto.KycDTO;
import com.oracle.kyc.model.Kyc;

import java.util.List;

public interface KycService {

    KycDTO saveKyc(KycDTO kycDTO);

    KycDTO getKycByCustomerId(String customerId);

    List<KycDTO> getAllKycs();

    KycDTO updateKyc(String customerId, KycDTO kycDTO);

    void deleteKyc(String customerId);
    
    void reviewKyc(String customerId, String status, String remark);

}
