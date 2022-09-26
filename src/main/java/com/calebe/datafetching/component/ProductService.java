package com.calebe.datafetching.component;

import com.calebe.datafetching.eager.EagerCompany;
import com.calebe.datafetching.lazy.LazyCompany;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.Type;
import java.util.List;

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

    @Transactional
    public int countProductsByCompanyLazilyWithInternalHibernateAPI(int companyId) {
        LazyCompany company = entityManager.find(LazyCompany.class, companyId);
        Hibernate.initialize(company.getProducts());
        return company.getProducts().size();
    }

    @Transactional
    public int countAvailableProducts() {
        TypedQuery<LazyCompany> companyTypedQuery = entityManager.createQuery("FROM LazyCompany company JOIN FETCH company.products", LazyCompany.class);
        List<LazyCompany> companies = companyTypedQuery.getResultList();
        int productCount = 0;
        for(LazyCompany company: companies) {
            productCount += company.getProducts().size();
        }
        return productCount;
    }

    @Transactional
    public int countProductsByCompanyWithJPQL(int companyId) {
        TypedQuery<LazyCompany> query = entityManager.createQuery("FROM LazyCompany company JOIN FETCH company.products WHERE company.id = :id", LazyCompany.class);
        query.setParameter("id", companyId);
        LazyCompany company = query.getSingleResult();
        return company.getProducts().size();
    }

    @Transactional
    public int countProductsByCompanyWithCriteriaAPI(int companyId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<LazyCompany> cq = cb.createQuery(LazyCompany.class);
        Root<LazyCompany> from = cq.from(LazyCompany.class);
        from.fetch("products");
        cq.where(cb.equal(from.get("id"), companyId));
        TypedQuery<LazyCompany> query = entityManager.createQuery(cq);
        LazyCompany company = query.getSingleResult();
        return company.getProducts().size();
    }
}
