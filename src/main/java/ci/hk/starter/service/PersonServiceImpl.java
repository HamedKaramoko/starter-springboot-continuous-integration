package ci.hk.starter.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ci.hk.starter.exception.FunctionalException;
import ci.hk.starter.model.Person;

@Service
public class PersonServiceImpl implements PersonService {
	
	private final List<Person> personList = Arrays.asList(new Person(1L, "Hamed KARAMOKO"), new Person(2L, "Mariama KARAMOKO"), new Person(3L, "Habib KARAMOKO"), new Person(4L, "Ali KARAMOKO"));

	@Override
	public Person getById(long id) {
		
		return personList.stream()
				.filter(person -> person.getId() == id)
				.findFirst()
				.orElseThrow(() -> new FunctionalException(HttpStatus.NOT_FOUND, String.format("No person with id -> %s found", id)));
	}

	@Override
	public List<Person> getAll() {
		return personList;
	}

}
