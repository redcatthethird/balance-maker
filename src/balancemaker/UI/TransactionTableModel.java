/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balancemaker.UI;

import balancemaker.Buyer;
import balancemaker.Transaction;
import balancemaker.Manager;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Red
 */
public class TransactionTableModel extends AbstractTableModel {
    private final int MIN_COLS = 7;
    private final String[] columnNames = { "Id", "Store", "Date", "Receipt",
        "Amount", "Buyer", "Payback"};
    
    public TransactionTableModel() {
        Manager.updateBuyers();
    }
    
    @Override
    public int getRowCount() { return Manager.getTransactionsNumber(); }
    @Override
    public int getColumnCount() {
        return MIN_COLS + Manager.getBuyersNumber();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Transaction t = Manager.getTransaction(rowIndex);
        if (columnIndex < MIN_COLS)
            switch (columnIndex) {
                case 0: return t.getId();
                case 1: return t.getStore();
                case 2: return t.getDate();
                case 3: return t.getReceipt();
                case 4: return t.getAmount();
                case 5: return t.getBuyer().getName();
                case 6: return t.isPayback();
                default: return null;
            }
        else {
            Buyer b = Manager.getBuyer(columnIndex - MIN_COLS);
            return t.getDebtFromBuyer(b);
        }
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        if (columnIndex < MIN_COLS) {
            return columnNames[columnIndex];
        } else {
            return Manager.getBuyer(columnIndex - MIN_COLS).getName();
        }
    }
    @Override
    public Class getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) { return false; }
    
    
    public void removeRow(int rowIndex) {
        Manager.removeTransaction(rowIndex);
        Manager.updateBuyers();
    }
    public void removeRows(int rowStartIndex, int rowEndIndex) {
        for (int i = rowEndIndex-1; i >= rowStartIndex; i--) {
            //System.out.println(Manager.getTransaction(i));
            Manager.removeTransaction(i);
        }
        Manager.updateBuyers();
    }
}
