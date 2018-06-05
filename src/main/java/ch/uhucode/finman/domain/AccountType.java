/**
 * 
 */
package ch.uhucode.finman.domain;

/**
 * @author thomas
 *
 */
public enum AccountType {
	
	ASSET(EntryType.DEBIT),
	LIABILITY(EntryType.CREDIT),
	EQUITY(EntryType.CREDIT),
	REVENUE(EntryType.CREDIT),
	EXPENSE(EntryType.DEBIT);
	
	
	private EntryType increaseType;
	
	private AccountType(EntryType increaseType) {
		this.increaseType = increaseType;
	}

	/**
	 * @return the increaseType
	 */
	public EntryType getIncreaseType() {
		return increaseType;
	}
	
}
