/**
 * 
 */
package ch.uhucode.finman.domain.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.uhucode.finman.domain.StockSymbol;
import ch.uhucode.finman.domain.StockQuote;
import ch.uhucode.finman.service.StockDataService;
import ch.uhucode.finman.service.YahooStockDataService;
import yahoofinance.Stock;
import yahoofinance.histquotes.HistoricalQuote;

/**
 * @author thomas
 *
 */
@Controller
@RequestMapping(path="/finman") // This means URL's start with /finman (after Application path)
public class StockDataController {
	
	@Autowired
	private StockDataService stockDataService;
	
	@Autowired
	private YahooStockDataService yahooStockDataService;
	
	
	@GetMapping(path="/update") // Map ONLY GET Requests
	public @ResponseBody String updateStock (@RequestParam String symbol) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		
		StockSymbol stock = stockDataService.getStock(symbol);
		if (stock == null) {
			StockSymbol n = new StockSymbol();
			n.setSymbol(symbol);
			Stock s = null;
			try {
				s = yahooStockDataService.getStock(symbol, false);
				n.setName(s.getName());
				n.setCurrency(s.getCurrency());
				n.setExchange(s.getStockExchange());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stock = stockDataService.saveOrUpdate(n);
		}
		List<HistoricalQuote> list = yahooStockDataService.getStockHistory(symbol, false);
		List<StockQuote> stockQuotes = map(list);
		stockDataService.addStockQuotes(stock, stockQuotes);
		return "updated";
	}
	
	private static List<StockQuote> map(List<HistoricalQuote> list) {
		List<StockQuote> ml = new ArrayList<StockQuote>();
		for(HistoricalQuote hq : list) {
			StockQuote he = new StockQuote();
			he.setTimepoint(new Date(hq.getDate().getTimeInMillis()));
			he.setOpen(hq.getOpen());
			he.setHigh(hq.getHigh());
			he.setLow(hq.getLow());
			he.setClose(hq.getClose());
			he.setVolume(hq.getVolume());
			ml.add(he);
		}
		return ml;

	}


	@GetMapping(path="/all")
	public @ResponseBody Iterable<StockSymbol> getAllStocks() {
		// This returns a JSON or XML with the stocks
		return stockDataService.getAllStocks();
	}

}
