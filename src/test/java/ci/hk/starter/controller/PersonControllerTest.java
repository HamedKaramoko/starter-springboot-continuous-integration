package ci.hk.starter.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import ci.hk.starter.exception.FunctionalException;
import ci.hk.starter.mapper.PersonMapper;
import ci.hk.starter.model.Person;
import ci.hk.starter.service.PersonService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
    private PersonService personService;
	
	@SpyBean
	private PersonMapper personMapper;
	
	private List<Person> persons;

	@Before
	public void setUp() throws Exception {
		Person person1 =  new Person(1L, "Hamed Karamoko");
		
		Person person2 =  new Person(2L, "Mariama Karamoko");
		
		persons = Arrays.asList(person1, person2);
		
		when(personMapper.fromPersonToPersonDto(any())).thenCallRealMethod();
		when(personMapper.fromPersonListToPersonDtoList(anyList())).thenCallRealMethod();
		
	}

	/**
	 * Test case validating the method {@link PersonController#getById(long)}.
	 * 
	 * When the given id matches one person's id then an Http response is returned with status {@link HttpStatus#OK} along with this person found.
	 * 
	 * @throws Exception when something wrong.
	 */
	@Test
	public void testGetByIdWhenPersonServiceReturnAPerson() throws Exception{
		
		when(personService.getById(anyLong())).thenReturn(persons.get(0));
		
		mockMvc.perform(get("/persons/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(
				matchAll(
						status().isOk(),
						jsonPath("$.id", Matchers.is(persons.get(0).getId().intValue())),
						jsonPath("$.name", Matchers.is(persons.get(0).getName()))
						)
				);
	}
	
	/**
	 * Test case validating the method {@link PersonController#getById(long)}.
	 * 
	 * When the given id doesn't match one person's id then an Http response is returned with status {@link HttpStatus#NOT_FOUND} and a message describing it.
	 * 
	 * @throws Exception when something wrong.
	 */
	@Test
	public void testGetByIdWhenPersonServiceThrowAnException() throws Exception{
		
		when(personService.getById(anyLong())).thenThrow(new FunctionalException(HttpStatus.NOT_FOUND, "There are no person with this id"));
		
		mockMvc.perform(get("/persons/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(
				matchAll(status().is(404),
						content().string("There are no person with this id")
						))
		.andDo(print());
	}
	
	/**
	 * Test case validating the method {@link PersonController#getAll()}.
	 * 
	 * When at least one person is found then an Http response is returned with status {@link HttpStatus#OK} along with a {@link List} of all persons found.
	 * 
	 * @throws Exception when something wrong.
	 */
	@Test
	public void testGetAllWhenAtLeastOnePersonExist() throws Exception{
		
		when(personService.getAll()).thenReturn(persons);
		
		mockMvc.perform(get("/persons/")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(
				matchAll(
						status().isOk(),
						jsonPath("$.length()", Matchers.is(persons.size())),
						jsonPath("$[0].id", Matchers.is(persons.get(0).getId().intValue())),
						jsonPath("$[0].name", Matchers.is(persons.get(0).getName())),
						jsonPath("$[1].id", Matchers.is(persons.get(1).getId().intValue())),
						jsonPath("$[1].name", Matchers.is(persons.get(1).getName()))
						)
				);
	}
	
	/**
	 * Test case validating the method {@link PersonController#getAll()}.
	 * 
	 * When at no person is found then an Http response is returned with status {@link HttpStatus#OK} along with an empty list.
	 * 
	 * @throws Exception when something wrong.
	 */
	@Test
	public void testGetAllWhenNoPersonExist() throws Exception{
		
		when(personService.getAll()).thenReturn(Collections.emptyList());
		
		mockMvc.perform(get("/persons/")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(
				matchAll(
						status().isOk(),
						jsonPath("$.length()", Matchers.is(0)),
						content().string("[]")
						)
				)
		.andDo(print());
	}
}
