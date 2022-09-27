package com.calebe.datafetching.component;

import com.calebe.datafetching.lazy.LazyCompany;
import com.calebe.datafetching.projection.CompanyNameProjectionSpringData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LazyCompanyRepository extends JpaRepository<LazyCompany, Integer> {
    CompanyNameProjectionSpringData findSingleById(int companyId);
}
