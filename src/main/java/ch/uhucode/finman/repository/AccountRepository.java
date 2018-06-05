/**
 * 
 */
package ch.uhucode.finman.repository;

import org.springframework.data.repository.CrudRepository;

import ch.uhucode.finman.domain.Account;

/**
 * @author thomas
 *
 */
public interface AccountRepository extends CrudRepository<Account, Long> {

}
