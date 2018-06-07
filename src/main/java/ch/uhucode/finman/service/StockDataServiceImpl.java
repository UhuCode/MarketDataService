/**
 * 
 */
package ch.uhucode.finman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import ch.uhucode.finman.domain.StockSymbol;
import ch.uhucode.finman.domain.StockQuote;
import ch.uhucode.finman.repository.StockQuoteRepository;
import ch.uhucode.finman.repository.StockSymbolRepository;
import ch.uhucode.finman.util.LogUtil;

/**
 * @author thomas
 *
 */
@Service
public class StockDataServiceImpl implements StockDataService {
	
	private static final LogUtil logger = LogUtil.getLogger(StockDataServiceImpl.class);
	
	@Autowired
	private final StockSymbolRepository stockSymbolRepository;
	
	@Autowired
	private final StockQuoteRepository stockQuoteRepository;
	
	public StockDataServiceImpl(StockSymbolRepository stockRepository, StockQuoteRepository stockQuoteRepository) {
		this.stockSymbolRepository = stockRepository;
		this.stockQuoteRepository = stockQuoteRepository;
	}

	@Override
	public StockSymbol getStock(String symbol) {
		return stockSymbolRepository.findBySymbol(symbol);
	}

	@Override
	public StockSymbol saveOrUpdate(StockSymbol stock) {
		stock = stockSymbolRepository.save(stock);
		return stock;
	}

	@Override
	public void addStockQuotes(StockSymbol stock, List<StockQuote> stockQuotes) {
		for(StockQuote sq : stockQuotes) {
			addStockQuote(stock, sq);
		}
	}

	@Override
	public StockQuote addStockQuote(StockSymbol stock, StockQuote stockQuote) {
		StockQuote dbsq = stockQuoteRepository.findOneByStockSymbolAndTimepoint(stock, stockQuote.getTimepoint());
		if (dbsq == null) {
			stockQuote.setStockSymbol(stock);
			stockQuote = stockQuoteRepository.save(stockQuote);
			logger.sayOut("StockQuote inserted: "+ stockQuote);
			return stockQuote;
		} else {
			dbsq.setOpen(stockQuote.getOpen());
			dbsq.setClose(stockQuote.getClose());
			dbsq.setHigh(stockQuote.getHigh());
			dbsq.setLow(stockQuote.getLow());
			dbsq.setVolume(stockQuote.getVolume());
			dbsq = stockQuoteRepository.save(dbsq);				
			logger.sayOut("StockQuote updated: "+ dbsq);
			return dbsq;
		}
	}

	@Override
	public Iterable<StockSymbol> getStocks() {
		return stockSymbolRepository.findAll();
	}

	@Override
	public Iterable<StockQuote> getStockQuotes(StockSymbol s) {
		return stockQuoteRepository.findByStockSymbol(s);
	}

}
