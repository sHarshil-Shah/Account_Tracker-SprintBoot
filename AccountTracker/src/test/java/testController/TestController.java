package testController;

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

import com.wipro.model.Customer;
import com.wipro.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.wipro.AccountTrackerApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestController {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	CustomerRepository repo;

	@Test
	public void testGetCustomers() throws Exception {
		Customer[] c = this.restTemplate.getForObject("http://localhost:" + port + "/customers", Customer[].class);
		assertEquals(2, c.length);
	}

	@Test
	public void testAddCustomer() {
		Customer c = new Customer(100, "RAJ");
		ResponseEntity<String> responseEntity = this.restTemplate
				.postForEntity("http://localhost:" + port + "/customer", c, String.class);
		assertEquals(200, responseEntity.getStatusCodeValue());

	}

	@Test
	public void testUpdateCustomer() {
		Customer c = new Customer(1, "RAJ");

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Customer> entity = new HttpEntity<Customer>(c, headers);

		assertEquals(200, restTemplate
				.exchange("http://localhost:" + port + "/customers/" + c.getId(), HttpMethod.PUT, entity, String.class)
				.getStatusCodeValue());
	}

	@Test
	public void testDeleteCustomer() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Customer> entity = new HttpEntity<Customer>(headers);

		assertEquals(200,
				restTemplate
						.exchange("http://localhost:" + port + "/customers/1", HttpMethod.DELETE, entity, String.class)
						.getStatusCodeValue());
	}
}