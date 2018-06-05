/**
 * 
 */
package ch.uhucode.finman.repository;

import org.springframework.data.repository.CrudRepository;

import ch.uhucode.finman.domain.StockSymbol;

/**
 * @author thomas
 *
 */
public interface StockSymbolRepository extends CrudRepository<StockSymbol, Long> {
	
	public StockSymbol findBySymbol(String symbol);
	

}
