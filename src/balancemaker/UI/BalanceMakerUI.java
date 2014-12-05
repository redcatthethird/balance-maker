/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package balancemaker.ui;

import balancemaker.*;
import ca.odell.glazedlists.SortedList;
import java.awt.event.*;
import ca.odell.glazedlists.swing.GlazedListsSwing;
import ca.odell.glazedlists.swing.TableComparatorChooser;
import java.awt.EventQueue;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Red
 */
public class BalanceMakerUI extends javax.swing.JFrame{
    private final Manager manager = new Manager();
    private final ExclusionSystem<Buyer> exclusion = new ExclusionSystem<>(Buyer.none, manager.buyers);
    public BalanceMakerUI() {
        initComponents();
        postInit();
    }
    private void postInit() {
        populateList();
        
        // Create the list of transactions sorted by id.
        SortedList<Transaction> sortedTransactions;
        sortedTransactions = new SortedList<>(
                manager.transactions,
                (t1, t2) -> t1.getId() - t2.getId());
        
        // Set the table model.
        transactionTable.setModel(
                GlazedListsSwing.eventTableModelWithThreadProxyList(
                        sortedTransactions,
                        new TransactionTableFormat(manager.buyers)));
        
        // Prepare the table column header sorting behaviour.
        TableComparatorChooser.install(
                transactionTable,
                sortedTransactions,
                TableComparatorChooser.MULTIPLE_COLUMN_MOUSE_WITH_UNDO);
        
        // Refresh the table on list changes.
        manager.buyers.addListEventListener(e ->
                ((AbstractTableModel)transactionTable.getModel())
                        .fireTableStructureChanged());
        
        // Refresh the debt label when anything changes.
        buyerLeft.addItemListener(e -> updateDebtLabel.run());
        buyerRight.addItemListener(e -> updateDebtLabel.run());
        manager.transactions.addListEventListener(
                // Asynchronicity is necessary as the buyers are not updated yet.
                e -> EventQueue.invokeLater(updateDebtLabel));
        
        // Make the two combo boxes mutually exclusive.
        exclusion.install(buyerLeft); exclusion.install(buyerRight);
    }
    // TODO: Remove, for god's sake, every unneeded library.
    
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addTransaction = new javax.swing.JButton();
        removeTransaction = new javax.swing.JButton();
        exportFile = new javax.swing.JButton();
        importFile = new javax.swing.JButton();
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        buyerLeft = new javax.swing.JComboBox();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        buyerRight = new javax.swing.JComboBox();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        extendDebtsButton = new javax.swing.JToggleButton();
        debtLabel = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
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

        exportFile.setText("Export to spreadsheet");
        exportFile.setEnabled(false);

        importFile.setText("Import from spreadsheet");
        importFile.setToolTipText("All the importing code is there, but the authentication bit isn't implemented. Lack of motivation, probably.");
        importFile.setEnabled(false);
        importFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                importFileMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Debt from");

        buyerLeft.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("to");

        buyerRight.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

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

        transactionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
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
                        .addGap(0, 440, Short.MAX_VALUE)))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                .addGap(0, 0, 0)
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

    private void importFileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_importFileMouseClicked
        if (!importFile.isEnabled()) return;
        //new balancemaker.ui.SpreadsheetImporter().run(manager);
    }//GEN-LAST:event_importFileMouseClicked
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

    // <editor-fold defaultstate="collapsed" desc="Form Components">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addTransaction;
    private javax.swing.JComboBox buyerLeft;
    private javax.swing.JComboBox buyerRight;
    private javax.swing.JLabel debtLabel;
    private javax.swing.JButton exportFile;
    private javax.swing.JToggleButton extendDebtsButton;
    private javax.swing.JButton importFile;
    private javax.swing.JButton removeTransaction;
    private javax.swing.JTable transactionTable;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>
    private void populateList() {
        Buyer Andu    = new Buyer("Andu");
        Buyer Ciupi   = new Buyer("Ciupi");
        Buyer Claudiu = new Buyer("Claudiu");
        Buyer Catalin = new Buyer("Cătălin");
        Buyer Andrei  = new Buyer("Andrei");
        Buyer Iustin  = new Buyer("Iustin");
        
        manager.transactions.add(new TransactionBuilder().Store("Morrisons")
            .Amount(52f).Buyer(Ciupi).addDebt(3f, Andu).addDebt(5f, Claudiu)
            .createTransaction());
        Transaction t = new TransactionBuilder().Store("Pe stradă, în cartier")
            .Receipt("Iarbă, bere, tutun și alte alea").Amount(103f).Buyer(Iustin)
            .addDebt(35f, Andrei).addDebt(39f, Catalin).createTransaction();
        manager.transactions.add(t);
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
            {
                manager.transactions.beginEvent();
                for (int i = 0; i < selRowCount; i++)
                    manager.transactions.remove(selRow);
                manager.transactions.commitEvent();
            }
        }
    }
    @SuppressWarnings("Convert2Lambda")
    private final Runnable updateDebtLabel = new Runnable() {
        @Override
        public void run() {
            Buyer l = (Buyer)buyerLeft.getSelectedItem(); if(l==null) return;
            boolean lNone = l.equals(Buyer.none);
            Buyer r = (Buyer)buyerRight.getSelectedItem(); if(r==null) return;
            boolean rNone = r.equals(Buyer.none);
            
            float debt = 0;
            
            if (lNone && rNone)
                for (Buyer b : manager.buyers)
                    debt += manager.getDebtsTo(b);
            else if (lNone && !rNone) debt += manager.getDebtsTo(r);
            else if (!lNone && rNone) debt += manager.getDebts(l);
            else debt += manager.getDebtsTo(l, r);
            
            debtLabel.setText(DecimalFormat.getCurrencyInstance().format(debt));
        }
    };
}
