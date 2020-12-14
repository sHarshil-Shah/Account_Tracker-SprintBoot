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

import com.wipro.model.Account;
import com.wipro.service.AccountService;
import com.wipro.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.wipro.AccountTrackerApplication.class)
public class TestAccountService {

	@Autowired
	AccountService accServ;
	@Autowired
	CustomerService cusServ;

	@MockBean
	RestTemplate template;

	@Test
	public void testGetAccountById() {
		Account c = accServ.getAccountById(1L);
		assertEquals(1L, c.getId());
	}

	@Test
	public void testGetAllAccounts() {
		List<Account> c = accServ.getAllAccounts();
		assertEquals(2, c.size());
	}

	@Test
	public void testCreateAccounts() {
		Account c = accServ.saveOrUpdate(new Account(3L, "Saving", 2500.4, cusServ.getCustomerById(1L)));
		assertTrue(c.getFund() == 2500.4);
		assertEquals(2, accServ.getAllAccounts().size());
	}

	@Test
	public void testUpdateAccounts() {
		accServ.saveOrUpdate(new Account(3L, "Current", 254, accServ.getAccountById(3L).getCust()));
		assertTrue(254 == accServ.getAccountById(3L).getFund());
	}

	@Test
	public void testDeleteAccounts() {
		accServ.delete(2L);
		assertThrows(NoSuchElementException.class, () -> {
			accServ.getAccountById(2L);
		});

	}
}