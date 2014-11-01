package balancemaker;

/**
 *
 * @author Red
 */
import java.util.*;
public class Manager{
    private static ArrayList<Transaction> _transactions = new ArrayList<>();
    private static ArrayList<Buyer> _buyers = new ArrayList<>();
    
    public static void addTransaction(Transaction t) {
        _transactions.add(t);
    }
    public static void appendTransactionList(ArrayList<Transaction> tList){
        _transactions.addAll(tList);
    }
    // The list of Buyers should be updated after removing any Transaction.
    public static void removeTransaction(int tIndex) {
        _transactions.remove(tIndex);
    }
    public static void addBuyer(Buyer b) {
        if (!_buyers.contains(b))
            _buyers.add(b);
    }
    public static Iterator<Transaction> getTransactionsIterator() {
        return _transactions.iterator();
    }
    public static Iterator<Buyer> getBuyersIterator() {
        return _buyers.iterator();
    }
    public static int getTransactionsNumber() { return _transactions.size(); }
    public static int getBuyersNumber() { return _buyers.size(); }
    public static Transaction getTransaction(int index) {
        return _transactions.get(index);
    }
    // The index will correspond to the Buyer's id.
    public static Buyer getBuyer(int index) { return _buyers.get(index); }
    
    public static ArrayList<Transaction> getTransactionsFromBuyer(Buyer b) {
        ArrayList<Transaction> tList = new ArrayList<>();
        for (Transaction t: _transactions)
            if (t.getBuyer().equals(b))
                tList.add(t);
        return tList;
    }
    
    // Returns the amount b should pay.
    public static float getDebts(Buyer b) {
        float debt = 0f;
        for (Transaction t : _transactions)
            if (!t.getBuyer().equals(b))
                debt += t.getDebtFromBuyer(b).getAmount();
        return debt;
    }
    // Returns the amount b should receive.
    public static float getDebtsTo(Buyer b) {
        float debt = 0f;
        for (Transaction t : _transactions)
            if (t.getBuyer().equals(b))
                for (Iterator it = t.getDebtsIterator(); it.hasNext();) {
                    Transaction.Debt next = (Transaction.Debt)it.next();
                    debt += next.getAmount();
                }
        return debt;
    }
    // Returns the amount b should receive.
    public static float getDebtsTo(Buyer b1, Buyer b2) {
        float debt = 0f;
        for (Transaction t : _transactions)
            if (t.getBuyer().equals(b2))
                debt += t.getDebtFromBuyer(b1).getAmount();
        return debt;
    }
    
    // Returns the amount spent by b.
    public static float getAmountSpent(Buyer b) {
        float amount = 0f;
        for (Transaction t : _transactions)
            if (t.getBuyer().equals(b))
                amount += t.getAmount();
        return amount;
    }
    // Returns the amount spent by b after d.
    public static float getAmountSpent(Buyer b, Date d) {
        float amount = 0f;
        for (Transaction t : _transactions)
            if (t.getBuyer().equals(b) && t.getDate().compareTo(d) > 0)
                amount += t.getAmount();
        return amount;
    }
    // Returns the amount spent by b between d1 and d2.
    public static float getAmountSpent(Buyer b, Date d1, Date d2) {
        float amount = 0f;
        for (Transaction t : _transactions)
            if(t.getBuyer().equals(b) && t.getDate().compareTo(d1)>0 
                    && t.getDate().compareTo(d2)<0)
                amount += t.getAmount();
        return amount;
    }
    // Returns the amount spent by b at store.
    public static float getAmountSpent(Buyer b, String store) {
        float amount = 0f;
        for (Transaction t : _transactions)
            if(t.getBuyer().equals(b) && t.getStore().equals(store))
                amount += t.getAmount();
        return amount;
    }
    
    // This will populate the _buyers list with all _buyers referenced.
    public static void updateBuyers() {
        //addBuyer(Buyer.none);
        _buyers.clear();
        for (Transaction t : _transactions) {
            addBuyer(t.getBuyer());
            for (Iterator<Transaction.Debt> it = t.getDebtsIterator(); it.hasNext();) {
                Transaction.Debt next = it.next();
                addBuyer(next.getDebtor());
            }
        }
        _buyers.sort((Buyer o1, Buyer o2) -> o1.getId() - o2.getId());
        
        
    }
    
    // TODO: Lambdas
}
