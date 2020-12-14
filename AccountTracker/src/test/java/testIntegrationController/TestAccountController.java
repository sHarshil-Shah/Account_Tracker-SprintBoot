package testIntegrationController;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.wipro.model.Account;
import com.wipro.repository.AccountRepository;
import com.wipro.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.wipro.AccountTrackerApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestAccountController {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	AccountRepository repo;

	@Autowired
	CustomerService cusSev;

	@Test
	public void testGetAllAccountsByCustomerId() throws Exception {
		Account[] a = this.restTemplate.getForObject("http://localhost:" + port + "/customers/1/accounts",
				Account[].class);
		assertEquals(3, a.length);
	}

	@Test
	public void testAddAccount() {
		Account c = new Account(4L, "Current", 250.14, cusSev.getCustomerById(1L));
		ResponseEntity<String> responseEntity = this.restTemplate
				.postForEntity("http://localhost:" + port + "/customers/1/accounts", c, String.class);
		assertEquals(200, responseEntity.getStatusCodeValue());

	}

	@Test
	public void testUpdateAccount() {
		Account c = new Account(1L, "Savings", 2514.0, cusSev.getCustomerById(1L));

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Account> entity = new HttpEntity<Account>(c, headers);

		assertEquals(200, restTemplate.exchange("http://localhost:" + port + "/customers/1/accounts/" + c.getId(),
				HttpMethod.PUT, entity, String.class).getStatusCodeValue());
	}

	@Test
	public void testDeleteAccount() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Account> entity = new HttpEntity<Account>(headers);

		assertEquals(200, restTemplate.exchange("http://localhost:" + port + "/customers/1/accounts/1",
				HttpMethod.DELETE, entity, String.class).getStatusCodeValue());
	}
}