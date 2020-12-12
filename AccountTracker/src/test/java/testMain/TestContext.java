package testMain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wipro.controller.CustomerController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.wipro.AccountTrackerApplication.class)
public class TestContext {

	@Autowired
	private CustomerController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
}