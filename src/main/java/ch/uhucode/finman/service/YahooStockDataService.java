/**
 * 
 */
package ch.uhucode.finman.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import ch.uhucode.finman.domain.StockQuote;
import ch.uhucode.finman.util.LogUtil;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistQuotesRequest;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

/**
 * @author thomas
 *
 */
@Service
public class YahooStockDataService { 

	private static final LogUtil logger = LogUtil.getLogger(YahooStockDataService.class);
	private static final Hashtable<String, Stock> stockTable = new Hashtable<String, Stock>();
	private static final Hashtable<String, List<HistoricalQuote>> stockHistoryTable = new Hashtable<String, List<HistoricalQuote>>();
	/**
	 * 
	 */
	public YahooStockDataService() {
	}

	public Stock requestStockData(String symbol) throws IOException {
		Stock stock;
		logger.sayOut("Requesting data for: " + symbol);

		stock = YahooFinance.get(symbol);
		if(stock == null || !stock.isValid()) {
			logger.sayOut("No data found for: " + symbol);
		} else {
			logger.sayOut("Retrieved data for: " + symbol);
			stockTable.put(symbol, stock);
			stock.print();
		}
		return stock;
	}

	public Stock getStock(String symbol, boolean refresh) throws IOException {
		Stock stock;
		if(!refresh && stockTable.containsKey(symbol)) {
			stock = stockTable.get(symbol);
		} else {
			stock = requestStockData(symbol);
		}
		return stock;
	}

	public Stock getStock(String symbol) throws IOException {
		return getStock(symbol, false);
	}

	public List<HistoricalQuote> getStockHistory(String symbol, boolean refresh) {
		Stock stock = null;
		try {
			stock = getStock(symbol, refresh);
		} catch (IOException e) {
			logger.sayErr(e.getMessage());
			e.printStackTrace();
		}
		if (stock != null) {
			return getStockHistory(stock, refresh);
		}
		return null;
	}

	public List<HistoricalQuote> getStockHistory(Stock stock, boolean refresh) {
		List<HistoricalQuote> stockHistory;
		if(!refresh && stockHistoryTable.containsKey(stock.getSymbol())) {
			stockHistory = stockHistoryTable.get(stock.getSymbol());
		} else {
			stockHistory = requestHistory(stock);
		}
		return stockHistory;
	}

	public List<HistoricalQuote> getStockHistory(Stock stock) {
		return getStockHistory(stock, false);
	}

	public List<HistoricalQuote> requestHistory(Stock stock, Date from, Date to, Interval interval) {
		Calendar fromCal = Calendar.getInstance();
		fromCal.setTime(from);
		Calendar toCal = Calendar.getInstance();
		toCal.setTime(to);
		return requestHistory(stock, fromCal, toCal, interval);
	}
	
	public List<HistoricalQuote> requestHistory(Stock stock, Calendar from, Calendar to, Interval interval) {
		logger.sayOut("Requesting history data for: " + stock.getName());
		List<HistoricalQuote> list = new ArrayList<HistoricalQuote>();
		try {
			list = stock.getHistory(from, to, interval);
			if(!list.isEmpty()) {
				//logger.sayOut("Received " + list.size() + " history data records. ");
				list = cleanHistoryList(list);
				logger.sayOut("Cleaned List has " + list.size() + " history data records. ");
				stockHistoryTable.put(stock.getSymbol(), list);
				return list;
			}
		} catch (IOException e) {
			logger.sayErr(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public List<HistoricalQuote> requestHistory(Stock stock) {
		Calendar fromCal = HistQuotesRequest.DEFAULT_FROM;
		Calendar toCal = HistQuotesRequest.DEFAULT_TO;
		Interval interval = Interval.DAILY;
		return requestHistory(stock, fromCal, toCal, interval);
	}
	
	private List<HistoricalQuote> cleanHistoryList(List<HistoricalQuote> recs) {
		List<HistoricalQuote> list = new ArrayList<HistoricalQuote>();
		Iterator<HistoricalQuote> iter = recs.iterator();
		while(iter.hasNext()) {
			HistoricalQuote itm = (HistoricalQuote)iter.next();
			if (itm.getClose() != null) {
				list.add(itm);
			}
		}
		return list;
	}
	


}
