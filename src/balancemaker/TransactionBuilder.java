package balancemaker;

import java.time.Instant;
import java.util.*;

/**
 * This class will be used to build any Transaction,
 * except for the Debt list. Those will be added to the finished Transaction.
 * Required fields: store, amount, buyer;
 * @author Red
 */
public class TransactionBuilder {
    private String store;
    private String receipt = "";
    private Date date = Date.from(Instant.now());
    private float amount;
    private Buyer buyer;
    private boolean payback = false;
    private ArrayList<Transaction.Debt> debts = new ArrayList<>();

    public TransactionBuilder() { }

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
    
    public TransactionBuilder addDebt(Transaction.Debt debt) {
        debts.add(debt);
        return this;
    }
    public TransactionBuilder addDebt(float amount, Buyer debtor) {
        // This thing here is why the Debt inner class must be static.
        return addDebt(new Transaction.Debt(amount, debtor));
    }

    public Transaction createTransaction() {
        return new Transaction(store, receipt, date, amount, buyer, payback, debts);
    }
    
}
