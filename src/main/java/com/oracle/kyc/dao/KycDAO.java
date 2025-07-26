package com.oracle.kyc.dao;

import com.oracle.kyc.model.Kyc;
import java.util.List;

public interface KycDAO {

    Kyc saveKyc(Kyc kyc);

    Kyc getKycByCustomerId(String customerId);

    List<Kyc> getAllKycs();

    Kyc updateKyc(Kyc kyc);

    void deleteKyc(String customerId);
}
