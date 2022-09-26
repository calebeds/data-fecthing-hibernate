package com.calebe.datafetching;

import com.calebe.datafetching.component.ProductService;
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

}
