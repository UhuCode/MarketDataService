/**
 * 
 */
package ch.uhucode.finman.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * @author thomas
 *
 */
public class TransactionBuilder {
    final private Set<AccountEntry> entries = new HashSet<>();
    final private String info;

    private TransactionBuilder(@org.springframework.lang.Nullable String info) {
        this.info = info;
    }

    public static TransactionBuilder create(String  info) {
        return new TransactionBuilder(info);
    }

    public static TransactionBuilder create() {
        return new TransactionBuilder(null);
    }

    public TransactionBuilder debit(BigDecimal amount, Account account) {
        entries.add(new AccountEntry(amount, account, EntryType.DEBIT));
        return this;
    }

    public TransactionBuilder credit(BigDecimal amount, Account account) {
        entries.add(new AccountEntry(amount, account, EntryType.CREDIT));
        return this;
    }

    public Transaction build() {
        return new Transaction(entries, info, Instant.now().toEpochMilli());
    }

}
