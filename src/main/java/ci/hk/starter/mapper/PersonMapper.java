/**
 * 
 */
package ci.hk.starter.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import ci.hk.starter.dto.PersonDto;
import ci.hk.starter.model.Person;

/**
 * @author hamedkaramoko
 *
 */
@Mapper(componentModel="spring")
public interface PersonMapper {

	/**
	 * Converts from {@link Person} to {@link PersonDto}
	 * @param person person to convert.
	 * @return
	 */
	PersonDto fromPersonToPersonDto(Person person);
	
	/**
	 * Converts from a @{@link List} of {@link Person} to a @{@link List} of {@link PersonDto}
	 * @param persons persons to convert.
	 * @return
	 */
	List<PersonDto> fromPersonListToPersonDtoList(List<Person> persons);
}
