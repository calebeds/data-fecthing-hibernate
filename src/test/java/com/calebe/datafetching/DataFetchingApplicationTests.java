package com.calebe.datafetching;

import com.calebe.datafetching.component.ProductService;
import com.calebe.datafetching.lazy.LazyCompany;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

@SpringBootTest
class DataFetchingApplicationTests {
	private static final Logger LOGGER = Logger.getLogger(DataFetchingApplicationTests.class.getName());

	@Autowired
	private ProductService productService;
	@Test
	void testEagerFetching() {
		productService.countProductsByCompany(1);
	}

	@Test
	void testLazyFetching() {
		productService.countProductsByCompanyLazily(1);
	}

	@Test
	void testLazyInitializationException() {
		productService.countProductsByCompanyLazilyWithoutTx(1);
	}

	@Test
	void testLazyInitializationExceptionWithingDifferentTx() {
		LazyCompany company = productService.findCompanyById(1);
		productService.countProductsByCompany(company);
	}
	@Test
	void testLazyFetchingWithInternalHibernateDifferentTx() {
		productService.countProductsByCompanyLazilyWithInternalHibernateAPI(1);
	}
	@Test
	void testNPlusOneProblem() {
		productService.countAvailableProducts();
	}


}
