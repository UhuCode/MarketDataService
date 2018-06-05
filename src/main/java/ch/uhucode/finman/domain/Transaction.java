/**
 * 
 */
package ch.uhucode.finman.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 * @author thomas
 *
 */
@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String info;

    private Timestamp created;

	@OneToMany(mappedBy = "transaction")
    private List<AccountEntry> entries;

    public Transaction(Set<AccountEntry> entries2, String info2, long epochMilli) {
		// TODO Auto-generated constructor stub
	}


}
