/**
 * 
 */
package ch.uhucode.finman.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



/**
 * @author thomas
 *
 */
@Entity
public class StockSymbol {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
	private String symbol;
	
    @Column(nullable = true)
	private String name;
	
    @Column(nullable = true)
	private String currency;
	
    @Column(nullable = true)
	private String exchange;
	
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "stock")
	//private List<StockQuote> stockQuotes;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the exchange
	 */
	public String getExchange() {
		return exchange;
	}

	/**
	 * @param exchange the exchange to set
	 */
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	/**
	 * @return the stockQuotes
	 */
//	public List<StockQuote> getStockQuotes() {
//		return stockQuotes;
//	}

	/**
	 * @param stockQuotes the stockQuotes to set
	 */
//	public void setStockQuotes(List<StockQuote> stockQuotes) {
//		this.stockQuotes = stockQuotes;
//	}

}
