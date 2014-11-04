package balancemaker;

import java.util.*;

public final class Transaction extends IdentifiableInstanceManager {
    private final String store;
    private final Date date;
    private final String receipt;
    private final float amount;
    private final Buyer buyer;
    private final boolean payback;
    private final ArrayList<Debt> debts;
    
    // TODO: Remove getters, make variables public final and refactor code. For all my immutable classes.

    /**
     * Immutable.
     */
    public final static class Debt {
        private final float amount;
        private final Buyer debtor;
        // Transaction.this.buyer

        public Debt(Buyer debtor) { this(0f, debtor); }
        public Debt(float amount, Buyer debtor) {
            this.amount = amount;
            this.debtor = debtor;
        }

        public float getAmount(){ return amount; }
        public Buyer getDebtor(){ return debtor; }
        //public Buyer getLender() { return Transaction.this.buyer; }
    }

    public Transaction(String store, String receipt, Date date, float amount,
        Buyer buyer, boolean payback, ArrayList<Debt> debts) {
            super();
            this.store = store;
            this.receipt = receipt;
            this.date = date;
            this.amount = amount;
            this.buyer = buyer;
            this.payback = payback;
            this.debts = new ArrayList<>(debts);
            
            // If there is a non-null debt to oneself
            if (getDebtFromBuyer(buyer).getAmount() > 0.009)
                // throw a bloody exception.
                throw new IllegalArgumentException("Non-null debt to oneself.");
        }

    public String getStore(){ return store; }
    public String getReceipt(){ return receipt; }
    public Date getDate(){ return date; }
    public float getAmount(){ return amount; }
    public Buyer getBuyer(){ return buyer; }
    public boolean isPayback(){ return payback; }
    // I wish Iterators couldn't remove things. I really do.
    public Iterator<Debt> getDebtsIterator(){ return debts.iterator(); }
    public Debt getDebt(int index) { return debts.get(index); }
    
    @Override
    public String toString() {
        return "Transaction{" + "id=" + id + ", store=" + store
                + ", date=" + date + ", amount=" + amount + ", buyer=" + buyer + '}';
    }
    public String toString(boolean verbose) {
        if (verbose)
            return "Transaction{" + "id=" + id + ", store=" + store
                    + ", date=" + date + ", receipt=" + receipt
                    + ", amount=" + amount + ", buyer=" + buyer +
                    ", payback=" + payback + ", debts=" + debts + '}';
        else
            return toString();
    }
    
    
    public Debt getDebtFromBuyer(Buyer b) {
        for (Debt d : debts)
            if (d.debtor == b)
                return d;
        return new Debt(b);
    }
    
    public float getTotalDebt() {
        float debt = 0f;
        for (Debt d : debts)
            debt += d.amount;
        return debt;
    }
    public ArrayList<Buyer> getDebtors() {
        ArrayList<Buyer> debtors = new ArrayList<>();
        for (Debt d : debts)
            debtors.add(d.debtor);
        return debtors;
    }
}