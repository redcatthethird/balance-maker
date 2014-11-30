/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balancemaker.ui;

import balancemaker.Buyer;
import balancemaker.Transaction;
import ca.odell.glazedlists.gui.AdvancedTableFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Red
 */
class TransactionTableFormat implements AdvancedTableFormat<Transaction> {
    private final String[] columns = { "Id", "Store", "Date", "Receipt",
        "Amount", "Buyer", "Payback"};
    
    List<Buyer> buyers;

    public TransactionTableFormat(List<Buyer> buyers) {
        this.buyers = buyers;
    }

    @Override
    public Class getColumnClass(int column) {
        switch (column) {
            case 0: return Integer.class;
            case 2: return Date.class;
            case 1:
            case 3: return String.class;
            case 5: return Buyer.class;
            case 6: return Boolean.class;
            default: return Float.class;
        }
    }

    @Override
    public Comparator getColumnComparator(int column) {
        switch (column) {
            case 5: return Buyer.idComparator;
            case 0:
            case 2:
            case 1:
            case 3:
            case 4:
            default: return Comparator.naturalOrder();
        }
    }

    @Override
    public int getColumnCount() { return columns.length + buyers.size(); }

    @Override
    public String getColumnName(int column) {
        if (column < columns.length) {
            return columns[column];
        } else if (column < getColumnCount()) {
            return buyers.get(column - columns.length).getName();
        }
        return null;
    }

    @Override
    public Object getColumnValue(Transaction t, int column) {
        if (column < columns.length)
            switch (column) {
                case 0: return t.getId();
                case 1: return t.getStore();
                case 2: return t.getDate();
                case 3: return t.getReceipt();
                case 4: return t.getAmount();
                case 5: return t.getBuyer();
                case 6: return t.isPayback();
                default: return null;
            }
        else if (column < getColumnCount()) {
            Buyer b = buyers.get(column - columns.length);
            return t.getDebtFromBuyer(b);
        }
        return null;
    }
    
}
