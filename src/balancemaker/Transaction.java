package balancemaker;

import java.util.*;

public class Transaction {
    private static int ID = 0;
    private final int id;
    private final String store;
    private final Date date;
    private final String receipt;
    private final float amount;
    private final Buyer buyer;
    private final boolean payback;
    private final ArrayList<Debt> debts;

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
            this.id = ++ID;
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

    public int getId(){ return id; }
    public String getStore(){ return store; }
    public String getReceipt(){ return receipt; }
    public Date getDate(){ return date; }
    public float getAmount(){ return amount; }
    public Buyer getBuyer(){ return buyer; }
    public boolean isPayback(){ return payback; }
    // I wish Iterators couldn't remove things. I really do.
    public Iterator<Debt> getDebtsIterator(){ return debts.iterator(); }
    public Debt getDebt(int index) { return debts.get(index); }

    //public Transaction addDebt(Debt debt) { debts.add(debt); return this; }
    /*public Transaction addDebt(float amount, Buyer debtor) {
        return addDebt(new Debt(amount, debtor));
    }*/
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Transaction other = (Transaction) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

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