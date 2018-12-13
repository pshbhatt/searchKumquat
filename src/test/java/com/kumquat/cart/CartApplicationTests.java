package com.kumquat.cart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kumquat.cart.controller.CartController;
import com.kumquat.cart.model.Cart;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CartApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private MockMvc mockMvc;

	List<Cart> cartList;
	@Before
	public void setup(){

			cartList = Arrays.asList(new Cart("suit","Versace","black",111.25), new Cart("dress","Gucci","black",110),
					new Cart("jeans","Armani","blue", 90));


	}

	@Test
	public void testWebserviceEndPoint() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/search/articles/suit/black"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}

	@Test
	public void testWebserviceEndPointSorting() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/search/sort/price/asc"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}


}

