package de.kubikhq.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.HashMap;

import org.junit.jupiter.api.*;

import de.kubikhq.demo.controller.*;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private DemoController dc;

	@Test
	void contextLoads() {
	}

	@Test
	void testCodeResponse() {
		Map<String,String> params = new HashMap<String,String>();
		params.put("code", "500");
		ResponseEntity<?> entity = dc.getRequest(params);
		Assertions.assertEquals(entity.getStatusCode(),HttpStatus.valueOf(500));
	}

	@Test
	void testTimeResponse() {
		Map<String,String> params = new HashMap<String,String>();
		params.put("time", "100");
		ResponseEntity<?> entity = dc.getRequest(params);
		Assertions.assertEquals(entity.getStatusCode(),HttpStatus.valueOf(200));
	}
}
