package testService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.wipro.model.Customer;
import com.wipro.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.wipro.AccountTrackerApplication.class)
public class TestCustomerService {

	@Autowired
	CustomerService curServ;

	@MockBean
	RestTemplate template;

	@Test
	public void testGetCustomerById() {
		Customer c = curServ.getCustomerById(1L);
		assertTrue("Name 1".equals(c.getName()));
	}

	@Test
	public void testGetAllCustomers() {
		List<Customer> c = curServ.getAllCustomers();
		assertEquals(2, c.size());
	}

	@Test
	public void testCreateCustomers() {
		Customer c = curServ.saveOrUpdate(new Customer(3L, "Name 3"));
		assertTrue(c.getName().equals("Name 3"));
		assertEquals(2, curServ.getAllCustomers().size());
	}

	@Test
	public void testUpdateCustomers() {
		curServ.saveOrUpdate(new Customer(3L, "Name 4"));
		assertTrue("Name 4".equals(curServ.getCustomerById(3L).getName()));
	}

	@Test
	public void testDeleteCustomers() {
		curServ.delete(2L);
		assertThrows(NoSuchElementException.class, () -> {
			curServ.getCustomerById(2L);
		});

	}
}