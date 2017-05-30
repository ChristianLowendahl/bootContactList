package se.prodentus.contact_list;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ContactListControllerTest {
	
	@Autowired
	private ContactListRepository contactListRepository;
	@Autowired
    private MockMvc mockMvc;
	private Contact contact = new Contact(new Long(1), "Anders", "Andersson", "010-111 11 11", "anders@prodentus.se");
	
	@Before
	public void setup() {
		contactListRepository.deleteAll();
		contactListRepository.save(contact);
		contactListRepository.save(
				new Contact(new Long(2), "Berit", "Bengtsson", "020-222 22 22", "berit@prodentus.se"));
		contactListRepository.save(
				new Contact(new Long(3), "Carl", "Carlsson", "030-333 33 33", "carl@prodentus.se"));
		contactListRepository.save(
				new Contact(new Long(4), "Diana", "Davidsson", "040-444 44 44", "diana@prodentus.se"));
	}
	
	
	@Test
	public void contactsGet_ShouldReturnFourElements() throws Exception {
		// The "get" method belongs to the class MockMvcRequestBuilders and is static imported
		// so that the class name is not needed. The method returns a requestBuilder.
		// mockMvc.perform takes a requestBuilder as parameter and returns a resultAction
		// the resultAction has methods as .andDo, andExpect.
		ResultActions resultActions = mockMvc.perform(get("/contacts/"));
		resultActions
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(4)));
			// .andExpect(jsonPath("$.yourKeyValue", is("WhatYouExpect")));
			//.andExpect(jsonPath("$.content").value(""));
	}
	

	@Test
	public void contactsGet_ShouldContainCorrectContact() throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/contacts/"));
		resultActions
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.[0].firstName", is(contact.getFirstName())))
			.andExpect(jsonPath("$.[0].lastName", is(contact.getLastName())))
			.andExpect(jsonPath("$.[0].phoneNumber", is(contact.getPhoneNumber())))
			.andExpect(jsonPath("$.[0].emailAddress", is(contact.getEmailAddress())));
	}
	
	
	@Test
	public void contactsGetFindByFirstName_ShouldFindCorrectContact() throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/contacts/?firstName=" + contact.getFirstName()));
		resultActions
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$.[0].firstName", is(contact.getFirstName())));
	}
	
	@Test
	public void contactsPost_ShouldSaveContact() throws Exception {
		contactListRepository.deleteAll();
		ObjectMapper objectMapper = new ObjectMapper();
		String contactString = objectMapper.writeValueAsString(contact);
		ResultActions resultActions = mockMvc.perform(post("/contacts/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(contactString));
		resultActions
			.andDo(print())
			.andExpect(status().isCreated());
		List <Contact> contacts = contactListRepository.findAll();
		Contact savedContact = contacts.get(0);
		assertEquals(contact.getFirstName(), savedContact.getFirstName());
	
	}
	
	@Test
	public void contactsDelete_ShouldDeleteContact() throws Exception {
		contactListRepository.deleteAll();
		Contact createdContact = contactListRepository.save(contact);
		ResultActions resultActions = mockMvc.perform(delete("/contacts/" + createdContact.getId()));
		resultActions
			.andDo(print())
			.andExpect(status().isNoContent());
		List <Contact> contacts = contactListRepository.findAll();
		assertEquals(0, contacts.size());
	}

}