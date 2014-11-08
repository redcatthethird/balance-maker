/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package balancemaker.UI;

import balancemaker.*;
import java.awt.event.*;
import javafx.collections.ListChangeListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Red
 */
public class BalanceMakerUI extends javax.swing.JFrame{
    private final Manager manager = new Manager();
        
    /**
     * Creates new form BalanceMakerUI
     */
    public BalanceMakerUI() {
        initComponents();
        postInit();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addTransaction = new javax.swing.JButton();
        removeTransaction = new javax.swing.JButton();
        exportFile = new javax.swing.JButton();
        importFile = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        buyerLeft = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        buyerRight = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        extendDebtsButton = new javax.swing.JToggleButton();
        debtLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        transactionTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Balance Maker");

        addTransaction.setText("Add transaction");
        addTransaction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addTransactionMouseClicked(evt);
            }
        });

        removeTransaction.setText("Remove transaction");
        removeTransaction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removeTransactionMouseClicked(evt);
            }
        });

        exportFile.setText("Export to file");

        importFile.setText("Import from file");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Debt from");

        buyerLeft.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buyerLeft.setModel(new DisplayBuyerComboBoxModel());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("to");

        buyerRight.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buyerRight.setModel(new DisplayBuyerComboBoxModel());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText(":");

        extendDebtsButton.setBackground(new java.awt.Color(204, 0, 0));
        extendDebtsButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        extendDebtsButton.setText("◄");

        debtLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(buyerLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(buyerRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(debtLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(extendDebtsButton)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(buyerLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(buyerRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(extendDebtsButton)
                    .addComponent(debtLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        transactionTable.setModel(new TransactionTableModel(manager));
        transactionTable.setName(""); // NOI18N
        transactionTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        transactionTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                transactionTableKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(transactionTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addTransaction)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeTransaction)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exportFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(importFile)
                        .addGap(0, 532, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addTransaction)
                    .addComponent(removeTransaction)
                    .addComponent(exportFile)
                    .addComponent(importFile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void removeTransactionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeTransactionMouseClicked
        removeSelectedTransactions();
    }//GEN-LAST:event_removeTransactionMouseClicked
    private void transactionTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_transactionTableKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) removeSelectedTransactions();
    }//GEN-LAST:event_transactionTableKeyPressed
    private void addTransactionMouseClicked(MouseEvent evt) {                                            
        AddTransactionDialog dlg = new AddTransactionDialog(this, true, manager);
        dlg.setVisible(true);
        
        /*Buyer Catalin = new Buyer("Cătălin");
        Buyer Andrei = new Buyer("Andrei");
        Buyer Iustin = new Buyer("Iustin");
        
        Manager.addTransaction(new TransactionBuilder().Store("Pe stradă, în cartier")
            .Receipt("Iarbă, bere, tutun și alte alea").Amount(103f).Buyer(Iustin)
            .addDebt(35f, Andrei).addDebt(39f, Catalin).createTransaction());*/
    }                                           

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info :
                    javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException |
                javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BalanceMakerUI.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new BalanceMakerUI().setVisible(true);
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Form Components">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addTransaction;
    private javax.swing.JComboBox buyerLeft;
    private javax.swing.JComboBox buyerRight;
    private javax.swing.JLabel debtLabel;
    private javax.swing.JButton exportFile;
    private javax.swing.JToggleButton extendDebtsButton;
    private javax.swing.JButton importFile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton removeTransaction;
    private javax.swing.JTable transactionTable;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>   
    
    private void postInit() {
        populateList();
        buyerLeft.addItemListener(this::updateDebtLabel);
        buyerRight.addItemListener(this::updateDebtLabel);
        
        buyerLeft.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED)
                syncBuyerComboBox(buyerRight, (Buyer)e.getItem());
        });
        buyerRight.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED)
                syncBuyerComboBox(buyerLeft, (Buyer)e.getItem());
        });
        manager.buyers.addListener((ListChangeListener.Change<? extends Buyer> c) -> {
            while (c.next()) {
                if (c.getRemoved().contains((Buyer)buyerLeft.getSelectedItem()))
                    deselBuyerComboBox(buyerLeft, false);
                if (c.getRemoved().contains((Buyer)buyerRight.getSelectedItem()))
                    deselBuyerComboBox(buyerRight, false);
            
                syncBuyerComboBox(buyerLeft, (Buyer)buyerRight.getSelectedItem());
                syncBuyerComboBox(buyerRight, (Buyer)buyerLeft.getSelectedItem());
            }
        });
        
        deselBuyerComboBox(buyerLeft, true); deselBuyerComboBox(buyerRight, true);
    }
    
    // Rebuilds the given JComboBox list, removing the given Buyer
    public void syncBuyerComboBox(JComboBox cbx, Buyer otherSelBuyer) {
        DisplayBuyerComboBoxModel model = (DisplayBuyerComboBoxModel)cbx.getModel();
        Object sel = model.getSelectedItem();
        model.removeAllElements();
        model.addElement(Buyer.none, false);
        for (Buyer b : manager.buyers)
            model.addElement(b, false);
        
        // If a Buyer other than Buyer.none was selected, remove it from here.
        if (!otherSelBuyer.equals(Buyer.none)) model.removeElement(otherSelBuyer);
        
        model.setSelectedItem(sel, false);
        updateDebtLabel(null);
    }
    private void deselBuyerComboBox(JComboBox cbx, boolean fireChanges) {
        ((DisplayBuyerComboBoxModel)cbx.getModel())
                .setSelectedItem(Buyer.none, fireChanges);
    }
    private void updateDebtLabel(java.awt.event.ItemEvent e) {
        Buyer l = (Buyer)buyerLeft.getSelectedItem(); if(l==null) return;
        boolean lNone = l.equals(Buyer.none);
        Buyer r = (Buyer)buyerRight.getSelectedItem(); if(r==null) return;
        boolean rNone = r.equals(Buyer.none);

        float debt;

        if (lNone && rNone) {
            debt = manager.buyers.stream()
                    .map((b) -> manager.getDebtsTo(b))
                    .reduce(0f, (x, y) -> x + y);
        }
        else if (lNone && !rNone)
            debt = manager.getDebtsTo(r);
        else if (!lNone && rNone)
            debt = manager.getDebts(l);
        else debt = manager.getDebtsTo(l, r);

        debtLabel.setText('£' + Float.toString(debt));
    }
    
    private void populateList() {
        Buyer Andu    = new Buyer("Andu");
        Buyer Ciupi   = new Buyer("Ciupi");
        Buyer Claudiu = new Buyer("Claudiu");
        
        manager.transactions.add(new TransactionBuilder().Store("Morrisons")
            .Amount(52f).Buyer(Ciupi).addDebt(3f, Andu).addDebt(5f, Claudiu)
            .createTransaction());
        manager.transactions.add(new TransactionBuilder().Store("Sainsbury's")
            .Amount(13f).Buyer(Andu).addDebt(6f, Ciupi).addDebt(3f, Claudiu)
            .Receipt("Ghici").Payback(true).createTransaction());
    }

    private void removeSelectedTransactions() {
        int selRowCount = transactionTable.getSelectedRowCount();
        int selRow      = transactionTable.getSelectedRow();
        if (selRowCount > 0) {
            int n = JOptionPane.showConfirmDialog(this,
                    "Are you bloody sure you want to remove the selected transaction/s?",
                    "Insurance query", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (n == JOptionPane.YES_OPTION)
                ((TransactionTableModel)transactionTable.getModel()).removeRows(
                    selRow, selRow+selRowCount);
        }
    }
    
    // TODO: Try to translate everything to Groovy.
}
