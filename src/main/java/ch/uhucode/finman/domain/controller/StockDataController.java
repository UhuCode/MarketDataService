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
import ch.uhucode.finman.service.YahooFinanceService;
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
	private YahooFinanceService yahooFinanceService;
	
	
	@GetMapping(path="/update") // Map ONLY GET Requests
	public @ResponseBody String updateStock (@RequestParam String symbol) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		
		StockSymbol stock = yahooFinanceService.updateStockSymbol(symbol);
		return "Stock updated";
	}
	
	@GetMapping(path="/updateHistory") // Map ONLY GET Requests
	public @ResponseBody String updateHistory (@RequestParam String symbol) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		
		yahooFinanceService.updateStockQuotes(symbol);
		return "Stock History updated";
	}
	
	@GetMapping(path="/showHistory")
	public @ResponseBody Iterable<StockQuote> showStocks(@RequestParam String symbol) {
		StockSymbol s = stockDataService.getStock(symbol);
		return stockDataService.getStockQuotes(s);
	}

	@GetMapping(path="/showStock")
	public @ResponseBody StockSymbol showStock(@RequestParam String symbol) {
		// This returns a JSON or XML with the stocks
		return stockDataService.getStock(symbol);
	}

}
