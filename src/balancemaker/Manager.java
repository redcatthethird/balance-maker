package balancemaker;

/**
 *
 * @author Red
 */
import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.CollectionList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.FunctionList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.UniqueList;
import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.event.ListEventPublisher;
import ca.odell.glazedlists.impl.ReadOnlyList;
import ca.odell.glazedlists.util.concurrent.ReadWriteLock;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.TransformationList;
public class Manager {
    public final EventList<Transaction> transactions = new BasicEventList<>();
    public final EventList<Buyer> buyers;
    private final BinaryOperator<Float> sum = (x, y) -> x + y;
    
    public Manager() {
        //transactions.addListEventListener(this::updateBuyers);        
        
        // Building tree model
        CollectionList.Model<Transaction, Buyer> buyerTreeModel = parent -> {
            EventList<Buyer> buyers1;
            buyers1 = new BasicEventList<>(
                    transactions.getPublisher(),
                    transactions.getReadWriteLock());
            buyers1.add(parent.getBuyer());
            buyers1.addAll(parent.getDebtList().stream().map(d -> d.getDebtor()).collect(Collectors.toList()));
            return buyers1;
        };
        
        // Constructing the list pipeline for the buyers
        CollectionList<Transaction, Buyer> collectedBuyers
                = new CollectionList<>(transactions, buyerTreeModel);
        buyers = //new ReadOnlyList<>(
                //new SortedList<>(
                        new UniqueList<>(collectedBuyers, Buyer.idComparator)
                        //,
                        //Buyer.idComparator)
        //)
                ;
    }
    
    public List<Transaction> getTransactionsFromBuyer(Buyer b) {
        return transactions.stream().filter((t) -> (t.getBuyer().equals(b)))
                .collect(Collectors.toList());
    }
    
    // Returns the amount b should pay.
    public float getDebts(Buyer b) {
        return transactions.stream().filter((t) -> (!t.getBuyer().equals(b)))
                .map((t) -> t.getDebtFromBuyer(b)).reduce(0f, sum);
    }
    // Returns the amount b should receive.
    public float getDebtsTo(Buyer b) {
        float debt = 0f;
        for (Transaction t : transactions)
            if (t.getBuyer().equals(b))
                debt += t.getDebtList().stream()
                         .map(Transaction.Debt::getAmount).reduce(0f, sum);
        return debt;
    }
    // Returns the amount b should receive.
    public float getDebtsTo(Buyer b1, Buyer b2) {
        return transactions.stream().filter(t -> (t.getBuyer().equals(b2)))
                .map((t) -> t.getDebtFromBuyer(b1)).reduce(0f, sum);
    }
    /*
    // Returns the amount spent by b.
    public float getAmountSpent(Buyer b) {
        float amount = 0f;
        for (Transaction t : transactions)
            if (t.getBuyer().equals(b))
                amount += t.getAmount();
        return amount;
    }
    // Returns the amount spent by b after d.
    public float getAmountSpent(Buyer b, Date d) {
        float amount = 0f;
        for (Transaction t : transactions)
            if (t.getBuyer().equals(b) && t.getDate().compareTo(d) > 0)
                amount += t.getAmount();
        return amount;
    }
    // Returns the amount spent by b between d1 and d2.
    public float getAmountSpent(Buyer b, Date d1, Date d2) {
        float amount = 0f;
        for (Transaction t : transactions)
            if(t.getBuyer().equals(b) && t.getDate().compareTo(d1)>0 
                    && t.getDate().compareTo(d2)<0)
                amount += t.getAmount();
        return amount;
    }
    // Returns the amount spent by b at store.
    public float getAmountSpent(Buyer b, String store) {
        float amount = 0f;
        for (Transaction t : transactions)
            if(t.getBuyer().equals(b) && t.getStore().equals(store))
                amount += t.getAmount();
        return amount;
    }*/
    
    // This will maintain the buyers list, keeping only referenced buyers
    /*private void updateBuyers(ListEvent<Transaction> le) {
        do {
            if (le.isReordering()) continue;
            if (le.getType() == ListEvent.)
        } while (le.nextBlock());
        
        LinkedList<Buyer> temp = new LinkedList<>();
        buyers.clear();
        for (Transaction t : transactions) {
            temp.add(t.getBuyer());
            t.getDebtList().forEach(d -> temp.add(d.getDebtor()));
        }
        buyers.addAll(
                temp.stream().distinct()
                .sorted((Buyer o1, Buyer o2) -> o1.getId() - o2.getId())
                .collect(Collectors.toCollection(FXCollections::observableArrayList))
        );
    }*/
}
