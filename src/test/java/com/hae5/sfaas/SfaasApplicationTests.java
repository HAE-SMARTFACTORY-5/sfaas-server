package com.hae5.sfaas;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@ActiveProfiles("test")
@Sql({"classpath:initTable.sql"})
@SpringBootTest
class SfaasApplicationTests {

	@Test
	void contextLoads() {
	}

}
