/**
 * 
 */
package ci.hk.starter.commandlinerunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import ci.hk.starter.model.Person;
import ci.hk.starter.repository.PersonRepository;

/**
 * @author hamedkaramoko
 *
 */
@Component
@Order(2)
public class DataLoaderPart2 implements CommandLineRunner {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataLoaderPart2.class);
	
	@Autowired
	private PersonRepository personRepository;

	@Override
	public void run(String... args) throws Exception {
		
		personRepository.save(new Person(3L, "Habib KARAMOKO"));
		personRepository.save(new Person(4L, "Ali KARAMOKO"));
		
		LOGGER.info("", "I logged two persons for the second part");
	}

}
