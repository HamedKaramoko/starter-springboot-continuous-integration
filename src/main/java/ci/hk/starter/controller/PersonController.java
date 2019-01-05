package ci.hk.starter.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ci.hk.starter.model.Person;
import ci.hk.starter.service.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);
	
	@Autowired
	private PersonService personService;
	
	/**
	 * Service allowing to obtain a person by its id.
	 * When no person found a {@link RuntimeException} is thrown.
	 * 
	 * @param id represents the id of the person to find.
	 * 
	 * @return a {@link Person} object having this id.
	 */
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Person getById(@PathVariable long id) {
		
		LOGGER.info("Trying to find a person with id {}", id);
		
		return personService.getById(id);
	}
	
	/**
	 * Service allowing to get all the person saved.
	 * When no person found it returns an empty {@link List}.
	 * 
	 * @return a {@link List} of {@link Person}.
	 */
	@GetMapping(value="/", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Person> getAll() {
		return personService.getAll();
	}

}
