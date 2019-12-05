package net.bluefsd.main;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestFsdApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseTestController {
	protected String token = "Bear eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2t5IiwiZXhwIjoyMDAxNTUyMTE4LCJpYXQiOjE1Njk1NTIxMTh9.IIbFaDpKcgZ-0ixxxHXKS0eOI5Yyh5XaD3SJuIvVLR1wgcn4e2eCp4q3AHf2HZWETNhbeT79XuscHHxstC1YhQ";
	protected MockMvc mockMvc;

	@Autowired
	protected WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	public void printResponse(ResultActions matcher) throws Exception {
		String responseStr = matcher.andReturn().getResponse().getContentAsString();
		System.out.println("return json = " + responseStr);
		System.out.println("---------------------------------------------");
	}
}
