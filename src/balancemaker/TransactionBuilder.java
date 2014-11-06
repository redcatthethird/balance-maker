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
    private boolean payback = false;
    private final LinkedList<Transaction.Debt> debts = new LinkedList<>();

    public TransactionBuilder Store(String store) {
        this.store = store;
        return this;
    }
    public TransactionBuilder Receipt(String receipt) {
        this.receipt = receipt;
        return this;
    }
    public TransactionBuilder Date(Date date) {
        this.date = date;
        return this;
    }
    public TransactionBuilder Amount(float amount) {
        this.amount = amount;
        return this;
    }
    public TransactionBuilder Buyer(Buyer buyer) {
        this.buyer = buyer;
        return this;
    }
    public TransactionBuilder Payback(boolean payback) {
        this.payback = payback;
        return this;
    }
    public TransactionBuilder addDebt(float amount, Buyer debtor) {
        debts.add(new Transaction.Debt(amount, debtor));
        return this;
    }

    public Transaction createTransaction() throws IllegalStateException {
        if (store == null || store.isEmpty())
            throw new IllegalStateException("Store is empty or null");
        if (buyer == null || !buyer.isValid())
            throw new IllegalStateException("Buyer is null or invalid");
        if (amount == Float.NaN || amount < 0f)
            throw new IllegalStateException("Float is NaN or less than 0");
        
        return new Transaction(store, receipt, date, amount, buyer, payback, debts);
    }
}
