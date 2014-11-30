package balancemaker;

import java.util.Comparator;

/**
 * Immutable.
 * @author Red
 */
public final class Buyer {
    protected static int ID = -1;
    public static Buyer none = new Buyer("Anyone");
    protected final String name;
    protected final int id;

    public Buyer(String name) {
        this.id = ++ID;
        this.name = name;
    }
    public String getName() { return name; }

    @Override
    public String toString() { return toString(false); }
    public String toString(boolean verbose) {
        if (verbose) return "Buyer{" + id + ": " + name + '}';
        else return name;
    }
    
    public int getId() { return id; }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
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
        final Buyer other = (Buyer) obj;
        return this.id == other.id;
    }
    
    // Shorthand forms for the Manager methods.
    /*
    public java.util.List<Transaction> getTransactions() {
        return Manager.getTransactionsFromBuyer(this); }
    public float getDebts() { return Manager.getDebts(this); }
    public float getDebtsTo() { return Manager.getDebtsTo(this); }
    public float getDebtsTo(Buyer b) { return Manager.getDebtsTo(this, b); }
    public float getAmountSpent() {
        return Manager.getAmountSpent(this);}
    public float getAmountSpent(Date d) {
        return Manager.getAmountSpent(this, d);}	
    public float getAmountSpent(Date d1, Date d2){
        return Manager.getAmountSpent(this, d1, d2);}
    public float getAmountSpent(String store){
        return Manager.getAmountSpent(this, store);}*/
    
    // FIXME: Debt computing algorithm.
    
    public static Comparator<Buyer> idComparator = (o1, o2) -> o1.id - o2.id;
}