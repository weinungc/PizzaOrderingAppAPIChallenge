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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class IngredientTests {

	@Autowired
	public MockMvc mockMvc;

	@Autowired
	public IngredientRepository ingredientRepository;

	@Before
	public void deleteAllBeforeTests() throws Exception {
		ingredientRepository.deleteAll();
	}

	@Test
	public void shouldCreateEntity() throws Exception {

		mockMvc.perform(post("/ingredient").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\": \"cheese\", \"inventory\":100,\"price\":2.0}")).andExpect(status().isCreated());
	}

	@Test
	public void shouldQueryEntity() throws Exception {

		mockMvc.perform(post("/ingredient").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\": \"cheese\", \"inventory\":100,\"price\":2.0}")).andExpect(status().isCreated());

		mockMvc.perform(get("/ingredient?name=cheese")).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("cheese")).andExpect(jsonPath("$.inventory").value(100))
				.andExpect(jsonPath("$.price").value(2.0));
	}

	@Test
	public void shouldUpdateEntity() throws Exception {

		MvcResult mvcResult = mockMvc
				.perform(post("/ingredient").contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\": \"cheese\", \"inventory\":100,\"price\":2.0}"))
				.andExpect(status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(put(location).contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\": \"cheese\", \"inventory\":200,\"price\":3.0}")).andExpect(status().isOk());

		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("cheese"))
				.andExpect(jsonPath("$.inventory").value(200)).andExpect(jsonPath("$.price").value(3.0));

	}

	@Test
	public void shouldDeleteEntity() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(post("/ingredient").contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\": \"cheese\", \"inventory\":100,\"price\":2.0}"))
				.andExpect(status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");
		mockMvc.perform(delete(location)).andExpect(status().isNoContent());
	}
}