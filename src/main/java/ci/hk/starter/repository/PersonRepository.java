/**
 * 
 */
package ci.hk.starter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ci.hk.starter.model.Person;

/**
 * @author hamedkaramoko
 *
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
