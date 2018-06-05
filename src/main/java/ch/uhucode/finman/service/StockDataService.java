/**
 * 
 */
package ch.uhucode.finman.service;

import java.util.List;

import ch.uhucode.finman.domain.StockSymbol;
import ch.uhucode.finman.domain.StockQuote;

/**
 * @author thomas
 *
 */
public interface StockDataService {
	
	public StockSymbol getStock(String symbol);
	
	public StockSymbol saveOrUpdate(StockSymbol stock);
	
	public void addStockQuotes(StockSymbol stock, List<StockQuote> stockQuotes);

	public Iterable<StockSymbol> getAllStocks();
	
	
}
