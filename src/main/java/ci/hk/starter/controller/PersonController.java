package ci.hk.starter.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ci.hk.starter.dto.PersonDto;
import ci.hk.starter.mapper.PersonMapper;
import ci.hk.starter.service.PersonService;

@RestController
public class PersonController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private PersonMapper personMapper;
	
	/**
	 * Service allowing to get all the person saved.
	 * When no person found it returns an empty {@link List}.
	 * 
	 * @return a {@link List} of {@link PersonDto}.
	 */
	@GetMapping(value="/persons", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<PersonDto> getAll() {
		return personMapper.fromPersonListToPersonDtoList(personService.getAll());
	}
	
	/**
	 * Service allowing to obtain a person by its id.
	 * When no person found a {@link RuntimeException} is thrown.
	 * 
	 * @param id represents the id of the person to find.
	 * 
	 * @return a {@link PersonDto} object having this id.
	 */
	@GetMapping(value="/persons/{id}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PersonDto getById(@PathVariable long id) {
		
		LOGGER.info("Trying to find a person with id {}", id);
		
		return personMapper.fromPersonToPersonDto(personService.getById(id));
	}
	
}
