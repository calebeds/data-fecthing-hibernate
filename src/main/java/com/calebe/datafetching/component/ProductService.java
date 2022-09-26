package com.calebe.datafetching.component;

import com.calebe.datafetching.eager.EagerCompany;
import com.calebe.datafetching.lazy.LazyCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class ProductService {
    @PersistenceContext
    private EntityManager entityManager;

//    @Autowired
//    private LazyCompanyRepository repository;

    @Transactional
    public int countProductsByCompany(int companyId) {
        EagerCompany company = entityManager.find(EagerCompany.class, companyId);
        return company.getProducts().size();
    }

    @Transactional
    public int countProductsByCompany(LazyCompany company) {
        company = entityManager.merge(company);
        return company.getProducts().size();
    }

    @Transactional
    public int countProductsByCompanyLazily(int companyId) {
        LazyCompany company = entityManager.find(LazyCompany.class, companyId);
        return company.getProducts().size();
    }

    public int countProductsByCompanyLazilyWithoutTx(int companyId) {
        LazyCompany company = entityManager.find(LazyCompany.class, companyId);
        return company.getProducts().size();

    }

    @Transactional
    public LazyCompany findCompanyById(int companyId) {
        return entityManager.find(LazyCompany.class, companyId);
    }
}
