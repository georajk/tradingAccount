package com.trading.account;

import com.trading.account.model.TradeEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class TradingAccountApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private TestRestTemplate restTemplate;


	@LocalServerPort
	private int port;

	String getRootUrl() {
		return "http://localhost:" + port + "/api";
	}

	@Test
	public void testLogEvent() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);


		ResponseEntity<String> response = restTemplate.postForEntity(getRootUrl() + "/logEvent",
				new TradeEvent(), String.class);

		Assertions.assertNotNull(response.getBody());
	}

	@Test
	public void testGetAccount() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.postForEntity(getRootUrl() + "/account",
				new TradeEvent(), String.class);

		Assertions.assertNull(response.getBody());

		response = restTemplate.postForEntity(getRootUrl() + "/logEvent",
				new TradeEvent(), String.class);

		Assertions.assertNotNull(response.getBody());
	}

	@Test
	public void testGetSize() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/size",
				HttpMethod.GET, entity, String.class);

		Assertions.assertNotNull(response.getBody());
	}

	@Test
	public void testClearCache() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/clear",
				HttpMethod.GET, entity, String.class);

		Assertions.assertNotNull(response.getBody());
	}

}
