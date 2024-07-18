package com.pdaniel.swapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class ProjectApplicationTests {

	@Test
	void startsApplicationAndLoadsContext() {
		assertDoesNotThrow(()-> ProjectApplication.main(new String[]{}));
	}

}
