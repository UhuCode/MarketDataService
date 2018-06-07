/**
 * 
 */
package ch.uhucode.finman.domain;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
import javax.persistence.JoinColumn;

/**
 * @author thomas
 *
 */
@Entity
public class StockQuote {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //@ManyToOne(optional = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_symbol_id", nullable = false)
    private StockSymbol stockSymbol;
    
    @Column(nullable = false)
	private Date timepoint;
    
    @Column(nullable = true, precision = 19, scale = 4)
	private BigDecimal open;
    
    @Column(nullable = true, precision = 19, scale = 4)
	private BigDecimal high;
    
    @Column(nullable = true, precision = 19, scale = 4)
	private BigDecimal low;
    
    @Column(nullable = true, precision = 19, scale = 4)
	private BigDecimal close;
    
    @Column(nullable = true)
	private long volume;
    
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @UpdateTimestamp
    private Timestamp lastModified;
    
	
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
	 * @return the stock
	 */
	public StockSymbol getStockSymbol() {
		return stockSymbol;
	}
	/**
	 * @param stock the stock to set
	 */
	public void setStockSymbol(StockSymbol stock) {
		this.stockSymbol = stock;
	}
	/**
	 * @return the timepoint
	 */
	public Date getTimepoint() {
		return timepoint;
	}
	/**
	 * @param timepoint the timepoint to set
	 */
	public void setTimepoint(Date timepoint) {
		this.timepoint = timepoint;
	}
	/**
	 * @return the open
	 */
	public BigDecimal getOpen() {
		return open;
	}
	/**
	 * @param open the open to set
	 */
	public void setOpen(BigDecimal open) {
		this.open = open;
	}
	/**
	 * @return the high
	 */
	public BigDecimal getHigh() {
		return high;
	}
	/**
	 * @param high the high to set
	 */
	public void setHigh(BigDecimal high) {
		this.high = high;
	}
	/**
	 * @return the low
	 */
	public BigDecimal getLow() {
		return low;
	}
	/**
	 * @param low the low to set
	 */
	public void setLow(BigDecimal low) {
		this.low = low;
	}
	/**
	 * @return the close
	 */
	public BigDecimal getClose() {
		return close;
	}
	/**
	 * @param close the close to set
	 */
	public void setClose(BigDecimal close) {
		this.close = close;
	}
	/**
	 * @return the volume
	 */
	public long getVolume() {
		return volume;
	}
	/**
	 * @param volume the volume to set
	 */
	public void setVolume(long volume) {
		this.volume = volume;
	}
	/**
	 * @return the lastModified
	 */
	public Timestamp getLastModified() {
		return lastModified;
	}
	/**
	 * @param lastModified the lastModified to set
	 */
	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}
	
	public String toString() {
		return "StockQuote: " + "id: " + id + " symbol: " + stockSymbol.getSymbol() +  " timepoint: " + timepoint;
	}
    

}
