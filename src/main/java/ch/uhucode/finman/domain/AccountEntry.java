/**
 * 
 */
package ch.uhucode.finman.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author thomas
 *
 */
@Entity
public class AccountEntry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    private BigDecimal amount;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
    private EntryType entryType;

    @ManyToOne
    private Account account;

    @ManyToOne
    private Transaction transaction;

    public AccountEntry(BigDecimal amount, Account account, EntryType debit) {
		// TODO Auto-generated constructor stub
	}


}
