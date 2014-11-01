package balancemaker;


import java.util.ArrayList;
import java.util.Date;

/**
 * Immutable.
 * @author Red
 */
public final class Buyer {
    private static int ID = -1;
    private final int id;
    private final String name;
    
    public static Buyer none = new Buyer("Anyone");

    public Buyer(String name) {
            this.id = ++ID;
            this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
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
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
    public String toString(boolean verbose) {
        return "Buyer{" + id + ": " + name + '}';
    }
    
    // Shorthand forms for the Manager methods.
    public ArrayList<Transaction> getTransactions() {
        return Manager.getTransactionsFromBuyer(this); }
    public float getDebts() { return Manager.getDebts(this); }
    public float getDebtsTo() { return Manager.getDebtsTo(this); }
    public float getDebtsTo(Buyer b) {
        return Manager.getDebtsTo(this, b); }
    
    public float getAmountSpent() {
        return Manager.getAmountSpent(this);}
    public float getAmountSpent(Date d) {
        return Manager.getAmountSpent(this, d);}	
    public float getAmountSpent(Date d1, Date d2){
        return Manager.getAmountSpent(this, d1, d2);}
    public float getAmountSpent(String store){
        return Manager.getAmountSpent(this, store);}
    	
    //public void payDebtTo(Buyer buyer) {}
}