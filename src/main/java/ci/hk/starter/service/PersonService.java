package ci.hk.starter.service;

import java.util.List;

import ci.hk.starter.model.Person;

public interface PersonService {
	
	Person getById(long id);
	
	List<Person> getAll();

}
