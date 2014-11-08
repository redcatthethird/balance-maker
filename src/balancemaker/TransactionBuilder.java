package balancemaker;

import java.time.Instant;
import java.util.*;

/**
 * This class will be used to build any Transaction.
 * Required fields: store, amount, buyer;
 * Class invariant: amount >= 0
 * @author Red
 */
public class TransactionBuilder {
    private String store;
    private String receipt = "";
    private Date date = Date.from(Instant.now());
    private float amount = Float.NaN;
    private Buyer buyer;
    private boolean payback;
    private final LinkedList<Transaction.Debt> debts = new LinkedList<>();

    public TransactionBuilder Store(String store) {
        if (store == null || store.isEmpty())
            throw new IllegalArgumentException("Store is empty or null.");
        this.store = store;
        return this;
    }
    public TransactionBuilder Receipt(String receipt) {
        if (receipt == null)
            throw new IllegalArgumentException("Receipt is null.");
        this.receipt = receipt;
        return this;
    }
    public TransactionBuilder Date(Date date) {
        this.date = date;
        return this;
    }
    public TransactionBuilder Amount(float amount) {
        if (amount == Float.NaN || amount < 0f)
            throw new IllegalArgumentException("Amount is NaN or less than 0.");
        this.amount = amount;
        return this;
    }
    public TransactionBuilder Buyer(Buyer buyer) {
        if (buyer == null || buyer.equals(Buyer.none))
            throw new IllegalArgumentException("Buyer cannot be null or nobody.");
        this.buyer = buyer;
        return this;
    }
    public TransactionBuilder Payback(boolean payback) {
        this.payback = payback;
        return this;
    }
    public TransactionBuilder addDebt(float amount, Buyer debtor) {
        if (debtor == null || debtor.equals(Buyer.none))
            throw new IllegalArgumentException("Debtor cannot be null or nobody.");
        debts.add(new Transaction.Debt(amount, debtor));
        return this;
    }

    public Transaction createTransaction() throws IllegalStateException {
        if (store == null || store.isEmpty())
            throw new IllegalStateException("Store is empty or null.");
        if (buyer == null)
            throw new IllegalStateException("Buyer is null.");
        if (amount == Float.NaN || amount < 0f)
            throw new IllegalStateException("Amount is NaN or less than .");
        
        return new Transaction(store, receipt, date, amount, buyer, payback, debts);
    }
}
