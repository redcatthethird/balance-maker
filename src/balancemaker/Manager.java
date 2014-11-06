package balancemaker;

/**
 *
 * @author Red
 */
import java.util.LinkedList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class Manager{
    public final static ObservableList<Transaction> transactions = FXCollections.observableArrayList();
    public final static ObservableList<Buyer> buyers = FXCollections.observableArrayList();
    private static final BinaryOperator<Float> sum = (x, y) -> x + y;
    
    static { transactions.addListener(Manager::updateBuyers); }
    
    public static List<Transaction> getTransactionsFromBuyer(Buyer b) {
        return transactions.stream().filter((t) -> (t.getBuyer().equals(b)))
                .collect(Collectors.toList());
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
                         .map(Transaction.Debt::getAmount).reduce(0f, sum);
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
    private static void updateBuyers(javafx.beans.Observable o) {
        LinkedList<Buyer> temp = new LinkedList<>();
        buyers.clear();
        for (Transaction t : transactions) {
            temp.add(t.getBuyer());
            t.getDebtList().forEach((d) -> temp.add(d.getDebtor()));
        }
        buyers.addAll(
                temp.stream().distinct()
                .sorted((Buyer o1, Buyer o2) -> o1.getId() - o2.getId())
                .collect(Collectors.toCollection(FXCollections::observableArrayList))
        );
    }
    
    // TODO: Unstatify the Manager.
}
