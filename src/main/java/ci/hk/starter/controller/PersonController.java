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
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Person getById(@PathVariable long id) {
		
		LOGGER.info("Trying to find a person with id {}", id);
		
		return personService.getById(id);
	}
	
	@GetMapping(value="/", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Person> getAll() {
		return personService.getAll();
	}

}
