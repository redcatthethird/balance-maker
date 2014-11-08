package balancemaker;

import java.util.*;

public final class Transaction extends Identifiable {
    private final String store;
    private final Date date;
    private final String receipt;
    private final float amount;
    private final Buyer buyer;
    private final boolean payback;
    private final ArrayList<Debt> debts;
    
    /**
     * For private use, thus package private.
     * Immutable.
     */
    public final static class Debt {
        public final static Debt nullDebt = new Debt(null);
        private final float amount;
        private final Buyer debtor;

        Debt(Buyer debtor) { this(0f, debtor); }
        Debt(float amount, Buyer debtor) {
            this.amount = amount;
            this.debtor = debtor;
        }

        public float getAmount(){ return amount; }
        public Buyer getDebtor(){ return debtor; }
    }

    Transaction(String store, String receipt, Date date, float amount,
        Buyer buyer, boolean payback, List<Debt> debts) {
            super();
            this.store = store;
            this.receipt = receipt;
            this.date = date;
            this.amount = amount;
            this.buyer = buyer;
            this.payback = payback;
            this.debts = new ArrayList<>(debts);
            
            // If there is a non-null debt to oneself
            if (getDebtFromBuyer(buyer) > 0.009)
                throw new IllegalArgumentException("Non-null debt to oneself.");
        }

    public String getStore(){ return store; }
    public String getReceipt(){ return receipt; }
    public Date getDate(){ return date; }
    public float getAmount(){ return amount; }
    public Buyer getBuyer(){ return buyer; }
    public boolean isPayback(){ return payback; }
    
    public List<Debt> getDebtList() { return Collections.unmodifiableList(debts); }
    
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
    
    
    public float getDebtFromBuyer(Buyer b) {
        for (Debt d : debts) {
            if (d.debtor == b)
                return d.amount;
        }
        return 0;
    }
    /*public float getTotalDebt() {
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
    }*/
    
    // TODO: For the sake of sake, get some databases in here. Please.
}