package com.oracle.kyc.dao;

import com.oracle.kyc.model.Kyc;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class KycDAOImpl implements KycDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Kyc saveKyc(Kyc kyc) {
        entityManager.persist(kyc);
        return kyc;
    }

    @Override
    public Kyc getKycByCustomerId(String customerId) {
        return entityManager.find(Kyc.class, customerId);
    }

    @Override
    public List<Kyc> getAllKycs() {
        return entityManager.createQuery("SELECT k FROM Kyc k", Kyc.class).getResultList();
    }

    @Override
    public Kyc updateKyc(Kyc kyc) {
        return entityManager.merge(kyc);
    }

    @Override
    public void deleteKyc(String customerId) {
        Kyc kyc = getKycByCustomerId(customerId);
        if (kyc != null) {
            entityManager.remove(kyc);
        }
    }
}
