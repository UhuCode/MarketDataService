/**
 * 
 */
package ch.uhucode.finman.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


/**
 * @author thomas
 *
 */
@Entity
public class Account {
	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
	private String number;
	
	private String name;
	
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private AccountType accountType;
	
	@OneToMany(mappedBy = "account")
    private List<AccountEntry> entries = new ArrayList<>();


}
