package ci.hk.starter.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ci.hk.starter.exception.FunctionalException;
import ci.hk.starter.model.Person;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceImplTest {
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Autowired
    private PersonService personService;
	
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test case validating the method {@link PersonService#getById(long)}.
	 * 
	 * When the given id matches one person's id then this person is returned.
	 * 
	 * @throws Exception when something wrong.
	 */
	@Test
	public void testGetByIdWhenPersonServiceReturnAPerson() throws Exception{
		
		Person personFound = personService.getById(1L);
		
		assertThat(personFound.getId(), is(1L));
		assertThat(personFound.getName(), is("Hamed KARAMOKO"));
	}
	
	/**
	 * Test case validating the method {@link PersonService#getById(long)}.
	 * 
	 * When the given id doesn't match a person's id then a {@link FunctionalException} is thrown with the cause as message.
	 * 
	 * @throws Exception when something wrong.
	 */
	@Test
	public void testGetByIdWhenPersonServiceThrowAnException() throws Exception{
		
		exceptionRule.expect(FunctionalException.class);
		exceptionRule.expectMessage("No person with id -> 99 found");
		
		personService.getById(99L);
				
	}
	
	/**
	 * Test case validating the method {@link PersonService#getAll()}.
	 * 
	 * When at least one person is found then a {@link List} of all persons found is returned.
	 * 
	 * @throws Exception when something wrong.
	 */
	@Test
	public void testGetAllWhenAtLeastOnePersonExist() throws Exception{
		
		List<Person> personsFound = personService.getAll();
		
		assertThat(personsFound.size(), is(4));
		assertThat(personsFound.get(0).getName(), is("Hamed KARAMOKO"));
		assertThat(personsFound.get(3).getName(), is("Ali KARAMOKO"));
	}

}
