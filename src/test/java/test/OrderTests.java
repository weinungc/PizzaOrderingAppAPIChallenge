/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.Application;
import com.example.repository.IngredientRepository;
import com.example.repository.OrderRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@RunWith(SpringRunner.class)
@SpringBootTest(classes =Application.class)
@AutoConfigureMockMvc
public class OrderTests {

	@Autowired
	public MockMvc mockMvc;

	@Autowired
	public OrderRepository orderRepository;
	
	@Autowired
	private IngredientRepository ingredientRepository;

	@Before
	public void deleteAllBeforeTests() throws Exception {
		orderRepository.deleteAll();
		ingredientRepository.deleteAll();
	}


	@Test
	public void shouldCreateEntity() throws Exception {
		
		mockMvc.perform(post("/ingredient").contentType(MediaType.APPLICATION_JSON).content(
				"{\"name\": \"cheese\", \"inventory\":100,\"price\":2.0}")).andExpect(
						status().isCreated());
		mockMvc.perform(post("/ingredient").contentType(MediaType.APPLICATION_JSON).content(
				"{\"name\": \"potato\", \"inventory\":25,\"price\":3.0}")).andExpect(
						status().isCreated());
		mockMvc.perform(post("/ingredient").contentType(MediaType.APPLICATION_JSON).content(
				"{\"name\": \"meatball\", \"inventory\":10,\"price\":1.0}")).andExpect(
						status().isCreated());
		mockMvc.perform(post("/ingredient").contentType(MediaType.APPLICATION_JSON).content(
				"{\"name\": \"mushroom\", \"inventory\":67,\"price\":5.0}")).andExpect(
						status().isCreated());
		String json = "{\n" + 
				"  \"customer\": {\n" + 
				"    \"name\": \"weinung\",\n" + 
				"    \"phone\": \"(626)8995371\",\n" + 
				"    \"address\": \"501 S Berendo St\"\n" + 
				"  },\n" + 
				"  \"orderdetails\": [\n" + 
				"    {\n" + 
				"      \"pizza\": {\n" + 
				"        \"size\": \"median\",\n" + 
				"        \"base\": \"dough\",\n" + 
				"        \"sauce\": \"regular\",\n" + 
				"        \"price\": 10,\n" + 
				"        \"ingredients\": [\n" + 
				"          \"cheese\",\n" + 
				"          \"mushroom\"\n" + 
				"        ]\n" + 
				"      },\n" + 
				"      \"qty\": 2,\n" + 
				"      \"price\": 20\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"pizza\": {\n" + 
				"        \"size\": \"median\",\n" + 
				"        \"base\": \"dough\",\n" + 
				"        \"sauce\": \"regular\",\n" + 
				"        \"price\": 10,\n" + 
				"        \"ingredients\": [\n" + 
				"          \"cheese\",\n" + 
				"          \"potato\"\n" + 
				"        ]\n" + 
				"      },\n" + 
				"      \"qty\": 2,\n" + 
				"      \"price\": 20\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"pizza\": {\n" + 
				"        \"size\": \"median\",\n" + 
				"        \"base\": \"dough\",\n" + 
				"        \"sauce\": \"regular\",\n" + 
				"        \"price\": 10,\n" + 
				"        \"ingredients\": [\n" + 
				"          \"cheese\",\n" + 
				"          \"meatball\"\n" + 
				"        ]\n" + 
				"      },\n" + 
				"      \"qty\": 2,\n" + 
				"      \"price\": 20\n" + 
				"    }\n" + 
				"  ],\n" + 
				"  \"total\": 60\n" + 
				"}";
		
		mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(
				status().isCreated());
	}


	@Test
	public void shouldQueryEntity() throws Exception {
		mockMvc.perform(post("/ingredient").contentType(MediaType.APPLICATION_JSON).content(
				"{\"name\": \"cheese\", \"inventory\":100,\"price\":2.0}")).andExpect(
						status().isCreated());
		mockMvc.perform(post("/ingredient").contentType(MediaType.APPLICATION_JSON).content(
				"{\"name\": \"potato\", \"inventory\":25,\"price\":3.0}")).andExpect(
						status().isCreated());
		mockMvc.perform(post("/ingredient").contentType(MediaType.APPLICATION_JSON).content(
				"{\"name\": \"meatball\", \"inventory\":10,\"price\":1.0}")).andExpect(
						status().isCreated());
		mockMvc.perform(post("/ingredient").contentType(MediaType.APPLICATION_JSON).content(
				"{\"name\": \"mushroom\", \"inventory\":67,\"price\":5.0}")).andExpect(
						status().isCreated());
		
		String json = "{\n" + 
				"  \"customer\": {\n" + 
				"    \"name\": \"weinung\",\n" + 
				"    \"phone\": \"(626)8995371\",\n" + 
				"    \"address\": \"501 S Berendo St\"\n" + 
				"  },\n" + 
				"  \"orderdetails\": [\n" + 
				"    {\n" + 
				"      \"pizza\": {\n" + 
				"        \"size\": \"median\",\n" + 
				"        \"base\": \"dough\",\n" + 
				"        \"sauce\": \"regular\",\n" + 
				"        \"price\": 10,\n" + 
				"        \"ingredients\": [\n" + 
				"          \"cheese\",\n" + 
				"          \"mushroom\"\n" + 
				"        ]\n" + 
				"      },\n" + 
				"      \"qty\": 2,\n" + 
				"      \"price\": 20\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"pizza\": {\n" + 
				"        \"size\": \"median\",\n" + 
				"        \"base\": \"dough\",\n" + 
				"        \"sauce\": \"regular\",\n" + 
				"        \"price\": 10,\n" + 
				"        \"ingredients\": [\n" + 
				"          \"cheese\",\n" + 
				"          \"potato\"\n" + 
				"        ]\n" + 
				"      },\n" + 
				"      \"qty\": 2,\n" + 
				"      \"price\": 20\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"pizza\": {\n" + 
				"        \"size\": \"median\",\n" + 
				"        \"base\": \"dough\",\n" + 
				"        \"sauce\": \"regular\",\n" + 
				"        \"price\": 10,\n" + 
				"        \"ingredients\": [\n" + 
				"          \"cheese\",\n" + 
				"          \"meatball\"\n" + 
				"        ]\n" + 
				"      },\n" + 
				"      \"qty\": 2,\n" + 
				"      \"price\": 20\n" + 
				"    }\n" + 
				"  ],\n" + 
				"  \"total\": 60\n" + 
				"}";

		MvcResult mvcResult = mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(
				status().isCreated()).andReturn();
		String location = mvcResult.getResponse().getHeader("Location");
		

		mockMvc.perform(
				get(location)).andExpect(
						status().isOk()).andExpect(
								jsonPath("$.customer.name").value(
										"weinung"));
	}

	@Test
	public void shouldUpdateEntity() throws Exception {
		mockMvc.perform(post("/ingredient").contentType(MediaType.APPLICATION_JSON).content(
				"{\"name\": \"cheese\", \"inventory\":100,\"price\":2.0}")).andExpect(
						status().isCreated());
		mockMvc.perform(post("/ingredient").contentType(MediaType.APPLICATION_JSON).content(
				"{\"name\": \"potato\", \"inventory\":100,\"price\":3.0}")).andExpect(
						status().isCreated());
		mockMvc.perform(post("/ingredient").contentType(MediaType.APPLICATION_JSON).content(
				"{\"name\": \"meatball\", \"inventory\":100,\"price\":1.0}")).andExpect(
						status().isCreated());
		mockMvc.perform(post("/ingredient").contentType(MediaType.APPLICATION_JSON).content(
				"{\"name\": \"mushroom\", \"inventory\":100,\"price\":5.0}")).andExpect(
						status().isCreated());
		mockMvc.perform(post("/ingredient").contentType(MediaType.APPLICATION_JSON).content(
				"{\"name\": \"peper\", \"inventory\":100,\"price\":5.0}")).andExpect(
						status().isCreated());

		String json = "{\n" + 
				"  \"customer\": {\n" + 
				"    \"name\": \"weinung\",\n" + 
				"    \"phone\": \"(626)8995371\",\n" + 
				"    \"address\": \"501 S Berendo St\"\n" + 
				"  },\n" + 
				"  \"orderdetails\": [\n" + 
				"    {\n" + 
				"      \"pizza\": {\n" + 
				"        \"size\": \"median\",\n" + 
				"        \"base\": \"dough\",\n" + 
				"        \"sauce\": \"regular\",\n" + 
				"        \"price\": 10,\n" + 
				"        \"ingredients\": [\n" + 
				"          \"cheese\",\n" + 
				"          \"mushroom\"\n" + 
				"        ]\n" + 
				"      },\n" + 
				"      \"qty\": 2,\n" + 
				"      \"price\": 20\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"pizza\": {\n" + 
				"        \"size\": \"median\",\n" + 
				"        \"base\": \"dough\",\n" + 
				"        \"sauce\": \"regular\",\n" + 
				"        \"price\": 10,\n" + 
				"        \"ingredients\": [\n" + 
				"          \"cheese\",\n" + 
				"          \"potato\"\n" + 
				"        ]\n" + 
				"      },\n" + 
				"      \"qty\": 2,\n" + 
				"      \"price\": 20\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"pizza\": {\n" + 
				"        \"size\": \"median\",\n" + 
				"        \"base\": \"dough\",\n" + 
				"        \"sauce\": \"regular\",\n" + 
				"        \"price\": 10,\n" + 
				"        \"ingredients\": [\n" + 
				"          \"cheese\",\n" + 
				"          \"meatball\"\n" + 
				"        ]\n" + 
				"      },\n" + 
				"      \"qty\": 2,\n" + 
				"      \"price\": 20\n" + 
				"    }\n" + 
				"  ],\n" + 
				"  \"total\": 60\n" + 
				"}";

		MvcResult mvcResult = mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(
				status().isCreated()).andReturn();
		String location = mvcResult.getResponse().getHeader("Location");
		
		String json2 = "{\n" + 
				"  \"customer\": {\n" + 
				"    \"name\": \"weinung\",\n" + 
				"    \"phone\": \"(626)8995371\",\n" + 
				"    \"address\": \"501 S Berendo St\"\n" + 
				"  },\n" + 
				"  \"orderdetails\": [\n" + 
				"    {\n" + 
				"      \"pizza\": {\n" + 
				"        \"size\": \"median\",\n" + 
				"        \"base\": \"dough\",\n" + 
				"        \"sauce\": \"regular\",\n" + 
				"        \"price\": 15,\n" + 
				"        \"ingredients\": [\n" + 
				"          \"peper\",\n" +
				"          \"mushroom\"\n" + 
				"        ]\n" + 
				"      },\n" + 
				"      \"qty\": 2,\n" + 
				"      \"price\": 30\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"pizza\": {\n" + 
				"        \"size\": \"median\",\n" + 
				"        \"base\": \"dough\",\n" + 
				"        \"sauce\": \"regular\",\n" + 
				"        \"price\": 15,\n" + 
				"        \"ingredients\": [\n" + 
				"          \"cheese\",\n" + 
				"          \"peper\",\n" +
				"          \"potato\"\n" + 
				"        ]\n" + 
				"      },\n" + 
				"      \"qty\": 2,\n" + 
				"      \"price\": 30\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"pizza\": {\n" + 
				"        \"size\": \"median\",\n" + 
				"        \"base\": \"dough\",\n" + 
				"        \"sauce\": \"regular\",\n" + 
				"        \"price\": 15,\n" + 
				"        \"ingredients\": [\n" + 
				"          \"cheese\",\n" + 
				"          \"peper\",\n" +
				"          \"meatball\"\n" + 
				"        ]\n" + 
				"      },\n" + 
				"      \"qty\": 2,\n" + 
				"      \"price\": 30\n" + 
				"    }\n" + 
				"  ],\n" + 
				"  \"total\": 60\n" + 
				"}";
		mockMvc.perform(put(location).contentType(MediaType.APPLICATION_JSON)
				.content(json2)).andExpect(status().isOk());
		
		mockMvc.perform(
				get("/ingredient?name=cheese")).andExpect(
						status().isOk()).andExpect(
								jsonPath("$.inventory").value(
										96));
		
		mockMvc.perform(
				get(location)).andExpect(
						status().isOk()).andExpect(
								jsonPath("$.orderdetails[0].pizza.ingredients[0]").value(
										"peper"));
		
	}


	@Test
	public void shouldDeleteEntity() throws Exception {

		mockMvc.perform(post("/ingredient").contentType(MediaType.APPLICATION_JSON).content(
				"{\"name\": \"cheese\", \"inventory\":100,\"price\":2.0}")).andExpect(
						status().isCreated());
		mockMvc.perform(post("/ingredient").contentType(MediaType.APPLICATION_JSON).content(
				"{\"name\": \"potato\", \"inventory\":100,\"price\":3.0}")).andExpect(
						status().isCreated());
		mockMvc.perform(post("/ingredient").contentType(MediaType.APPLICATION_JSON).content(
				"{\"name\": \"meatball\", \"inventory\":100,\"price\":1.0}")).andExpect(
						status().isCreated());
		mockMvc.perform(post("/ingredient").contentType(MediaType.APPLICATION_JSON).content(
				"{\"name\": \"mushroom\", \"inventory\":100,\"price\":5.0}")).andExpect(
						status().isCreated());
		mockMvc.perform(post("/ingredient").contentType(MediaType.APPLICATION_JSON).content(
				"{\"name\": \"peper\", \"inventory\":100,\"price\":5.0}")).andExpect(
						status().isCreated());

		String json = "{\n" + 
				"  \"customer\": {\n" + 
				"    \"name\": \"weinung\",\n" + 
				"    \"phone\": \"(626)8995371\",\n" + 
				"    \"address\": \"501 S Berendo St\"\n" + 
				"  },\n" + 
				"  \"orderdetails\": [\n" + 
				"    {\n" + 
				"      \"pizza\": {\n" + 
				"        \"size\": \"median\",\n" + 
				"        \"base\": \"dough\",\n" + 
				"        \"sauce\": \"regular\",\n" + 
				"        \"price\": 10,\n" + 
				"        \"ingredients\": [\n" + 
				"          \"cheese\",\n" + 
				"          \"mushroom\"\n" + 
				"        ]\n" + 
				"      },\n" + 
				"      \"qty\": 2,\n" + 
				"      \"price\": 20\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"pizza\": {\n" + 
				"        \"size\": \"median\",\n" + 
				"        \"base\": \"dough\",\n" + 
				"        \"sauce\": \"regular\",\n" + 
				"        \"price\": 10,\n" + 
				"        \"ingredients\": [\n" + 
				"          \"cheese\",\n" + 
				"          \"potato\"\n" + 
				"        ]\n" + 
				"      },\n" + 
				"      \"qty\": 2,\n" + 
				"      \"price\": 20\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"pizza\": {\n" + 
				"        \"size\": \"median\",\n" + 
				"        \"base\": \"dough\",\n" + 
				"        \"sauce\": \"regular\",\n" + 
				"        \"price\": 10,\n" + 
				"        \"ingredients\": [\n" + 
				"          \"cheese\",\n" + 
				"          \"meatball\"\n" + 
				"        ]\n" + 
				"      },\n" + 
				"      \"qty\": 2,\n" + 
				"      \"price\": 20\n" + 
				"    }\n" + 
				"  ],\n" + 
				"  \"total\": 60\n" + 
				"}";

		MvcResult mvcResult = mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(
				status().isCreated()).andReturn();
		String location = mvcResult.getResponse().getHeader("Location");
		mockMvc.perform(delete(location)).andExpect(status().isNoContent());
	}
}