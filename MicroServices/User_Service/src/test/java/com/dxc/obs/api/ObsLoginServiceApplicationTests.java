package com.dxc.obs.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@SpringBootTest
@Testcontainers
class ObsLoginServiceApplicationTests {

	@Container
	static OracleContainer oracleContainer = new OracleContainer("oracle: 12.2.0.1");
	
	static void setProperties(DynamicPropertyRegistry dynmicPropertyRegistry) {
		
		dynmicPropertyRegistry.add("spring.datasource.url", oracleContainer::getJdbcUrl);
	}
	
	
	@Test
	void contextLoads() {
	}

}
