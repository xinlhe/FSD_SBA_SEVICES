package com.ibm.sba.microservice.smc.userclient.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.HashMap;
import java.util.Map;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.sba.microservice.smc.userclient.SBA_MicroService_UserClient_Config;
import net.bluefsd.main.BaseTestController;

/**
 * UserControllerTest
 * 
 * @author XinLongHe
 * 
 * @since 2019.11.29
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Import({ SBA_MicroService_UserClient_Config.class })
public class UserControllerTest extends BaseTestController {
	static int userId = 0;

	//@Test
	public void u_1_addAdmin() throws Exception {
	    
		Map<String, Object> map = new HashMap<>();
		
		ObjectMapper mapper = new ObjectMapper();
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/user/adduser");
		
		request.content(mapper.writeValueAsString(map)).header("Authorization", token).contentType(MediaType.APPLICATION_JSON_UTF8);
		
		UserManager.createAdmin(request);
		
		ResultActions matcher = this.mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		
		matcher.andDo(MockMvcResultHandlers.print());
		
		matcher.andExpect(jsonPath("$.id").isNumber());
		
		matcher.andExpect(jsonPath("$.ret").value("success"));
		
		String responseStr = matcher.andReturn().getResponse().getContentAsString();
		
		JSONObject object = (JSONObject) JSONObject.parseObject(responseStr);
		
		userId = (Integer) object.get("id");
		
		printResponse(matcher);
	}

	// @Test
	public void u_2_addUser() throws Exception {
	    
		Map<String, Object> map = new HashMap<>();
		
		ObjectMapper mapper = new ObjectMapper();
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/user/adduser");
		
		request.content(mapper.writeValueAsString(map)).header("Authorization", token).contentType(MediaType.APPLICATION_JSON_UTF8);
		
		UserManager.createUser(request);
		
		ResultActions matcher = this.mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		
		matcher.andDo(MockMvcResultHandlers.print());
		
		printResponse(matcher);
	}

	//@Test
	public void u_4_update() throws Exception {
		
	    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/user/update");
		
	    ObjectMapper mapper = new ObjectMapper();
		
	    Map<String, Object> map = new HashMap<>();
		
	    request.content(mapper.writeValueAsString(map)).header("Authorization", token).contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8);
		
	    UserManager.update(request, userId);
		
	    ResultActions matcher = this.mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		
	    matcher.andDo(MockMvcResultHandlers.print());
		
	    matcher.andExpect(jsonPath("$.ret").value("success"));
	}
	
	// @Test
	public void userLogin() throws Exception {
		
	    String rawPassword = "2222";
		
		ResultActions matcher = this.mockMvc.perform(post("/user/login").param("username", "usky").param("password", rawPassword).accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		
		matcher.andDo(MockMvcResultHandlers.print());
	}
	
	//@Test
	public void userCanAccess() throws Exception {
	    
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/user/canaccess");
		
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, Object> map = new HashMap<>();
		
		request.content(mapper.writeValueAsString(map)).header("Authorization", token).contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8);

		ResultActions matcher = this.mockMvc.perform( request).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

		matcher.andDo(MockMvcResultHandlers.print());
	}
	
	//@Test
	public void sendMail() throws Exception {
	    
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/user/mail");
		
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, Object> map = new HashMap<>();
		
		request.content(mapper.writeValueAsString(map)).header("Authorization", token).contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8);

		UserManager.createUser(request);
		
		ResultActions matcher = this.mockMvc.perform( request).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		
		matcher.andDo(MockMvcResultHandlers.print());
	}

}
