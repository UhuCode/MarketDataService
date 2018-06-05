/**
 * 
 */
package ch.uhucode.finman.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ch.uhucode.finman.domain.StockSymbol;
import ch.uhucode.finman.domain.StockQuote;

/**
 * @author thomas
 *
 */
public interface StockQuoteRepository extends CrudRepository<StockQuote, Long> {
	
	public List<StockQuote> findByStockSymbol(StockSymbol stock);
	public StockQuote findOneByStockSymbolAndTimepoint(StockSymbol stock, Date timepoint);

}
