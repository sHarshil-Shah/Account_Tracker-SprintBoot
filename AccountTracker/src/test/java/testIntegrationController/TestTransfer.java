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
import org.springframework.test.context.junit4.SpringRunner;

import com.wipro.model.Transaction;
import com.wipro.repository.AccountRepository;
import com.wipro.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.wipro.AccountTrackerApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestTransfer {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	AccountRepository repo;

	@Autowired
	CustomerService cusSev;

	@Test
	public void testTransfer() {
		Transaction t = new Transaction(200, 2L, 3L);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Transaction> entity = new HttpEntity<Transaction>(t, headers);

		assertEquals(200,
				restTemplate.exchange("http://localhost:" + port + "/transfer", HttpMethod.PUT, entity, String.class)
						.getStatusCodeValue());
	}
}