/**
 * 
 */
package ci.hk.starter.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import ci.hk.starter.dto.PersonDto;
import ci.hk.starter.exception.FunctionalException;
import ci.hk.starter.model.Person;
import ci.hk.starter.repository.PersonRepository;

/**
 * @author hamedkaramoko
 *
 */
@Mapper
public abstract class PersonMapperWithBeanInside {
	
	@Autowired
	private PersonRepository personRepository;
	
	public PersonDto fromPersonToPersonDto(Person person) {
		
		Person p = personRepository.findById(1L).orElseThrow(()-> new FunctionalException(HttpStatus.NOT_FOUND, "Person not found in mapper"));
		
		return new PersonDto(p.getId(), p.getName());
	}

}
