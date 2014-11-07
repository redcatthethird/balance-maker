package balancemaker;

/**
 * Immutable.
 * @author Red
 */
public final class Buyer extends Identifiable {
    private final String name;
    
    public static Buyer none = new Buyer("Anyone");

    public Buyer(String name) {
        super();
        this.name = name;
    }

    public String getName() { return name; }

    @Override
    public String toString() { return name; }
    public String toString(boolean verbose) {
        return "Buyer{" + id + ": " + name + '}';
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
}