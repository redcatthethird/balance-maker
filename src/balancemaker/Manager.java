package balancemaker;

/**
 *
 * @author Red
 */
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
public class Manager{
    public static ArrayList<Transaction> transactions = new ArrayList<>();
    public static ArrayList<Buyer> buyers = new ArrayList<>();
    private static final BinaryOperator<Float> sum = (x, y) -> x + y;
    
    public static void addTransaction(Transaction t) {
        transactions.add(t);
    }
    public static void appendTransactionList(ArrayList<Transaction> tList){
        transactions.addAll(tList);
    }
    // The list of Buyers should be updated after removing any Transaction.
    public static void removeTransaction(int tIndex) {
        transactions.remove(tIndex);
    }
    public static void addBuyer(Buyer b) {
        if (!buyers.contains(b))
            buyers.add(b);
    }
    public static Iterator<Transaction> getTransactionsIterator() {
        return transactions.iterator();
    }
    public static Iterator<Buyer> getBuyersIterator() {
        return buyers.iterator();
    }
    public static int getTransactionsNumber() { return transactions.size(); }
    public static int getBuyersNumber() { return buyers.size(); }
    public static Transaction getTransaction(int index) {
        return transactions.get(index);
    }
    // The index will correspond to the Buyer's id.
    public static Buyer getBuyer(int index) { return buyers.get(index); }
    
    public static ArrayList<Transaction> getTransactionsFromBuyer(Buyer b) {
        ArrayList<Transaction> tList = new ArrayList<>();
        for (Transaction t: transactions)
            if (t.getBuyer().equals(b))
                tList.add(t);
        return tList;
    }
    
    // Returns the amount b should pay.
    public static float getDebts(Buyer b) {
        return transactions.stream().filter((t) -> (!t.getBuyer().equals(b)))
                .map((t) -> t.getDebtFromBuyer(b)).reduce(0f, sum);
    }
    // Returns the amount b should receive.
    public static float getDebtsTo(Buyer b) {
        float debt = 0f;
        for (Transaction t : transactions)
            if (t.getBuyer().equals(b))
                debt += t.getDebtList().stream()
                        .map(Transaction.Debt::getAmount)
                        .reduce(0f, sum);
        return debt;
    }
    // Returns the amount b should receive.
    public static float getDebtsTo(Buyer b1, Buyer b2) {
        return transactions.stream().filter((t) -> (t.getBuyer().equals(b2)))
                .map((t) -> t.getDebtFromBuyer(b1)).reduce(0f, sum);
    }
    /*
    // Returns the amount spent by b.
    public static float getAmountSpent(Buyer b) {
        float amount = 0f;
        for (Transaction t : transactions)
            if (t.getBuyer().equals(b))
                amount += t.getAmount();
        return amount;
    }
    // Returns the amount spent by b after d.
    public static float getAmountSpent(Buyer b, Date d) {
        float amount = 0f;
        for (Transaction t : transactions)
            if (t.getBuyer().equals(b) && t.getDate().compareTo(d) > 0)
                amount += t.getAmount();
        return amount;
    }
    // Returns the amount spent by b between d1 and d2.
    public static float getAmountSpent(Buyer b, Date d1, Date d2) {
        float amount = 0f;
        for (Transaction t : transactions)
            if(t.getBuyer().equals(b) && t.getDate().compareTo(d1)>0 
                    && t.getDate().compareTo(d2)<0)
                amount += t.getAmount();
        return amount;
    }
    // Returns the amount spent by b at store.
    public static float getAmountSpent(Buyer b, String store) {
        float amount = 0f;
        for (Transaction t : transactions)
            if(t.getBuyer().equals(b) && t.getStore().equals(store))
                amount += t.getAmount();
        return amount;
    }*/
    
    // This will populate the buyers list with all buyers referenced
    // and will remove any duplicate buyers.
    public static void updateBuyers() {
        //addBuyer(Buyer.none);
        buyers.clear();
        for (Transaction t : transactions) {
            addBuyer(t.getBuyer());
            t.getDebtList().forEach((d) -> addBuyer(d.getDebtor()));
        }
        buyers = buyers.stream()
                .sorted((Buyer o1, Buyer o2) -> o1.getId() - o2.getId())
                .distinct()
                .collect(ArrayList<Buyer>::new, ArrayList<Buyer>::add, ArrayList<Buyer>::addAll);
    }
}
