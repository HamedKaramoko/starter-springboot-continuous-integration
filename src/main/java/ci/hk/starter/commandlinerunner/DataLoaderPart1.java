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
@Order(1)
public class DataLoaderPart1 implements CommandLineRunner {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataLoaderPart1.class);
	
	@Autowired
	private PersonRepository personRepository;

	@Override
	public void run(String... args) throws Exception {
		
		personRepository.save(new Person(1L, "Hamed KARAMOKO"));
		personRepository.save(new Person(2L, "Mariama KARAMOKO"));
		
		LOGGER.info("", "I logged two persons for the first part");
	}

}
