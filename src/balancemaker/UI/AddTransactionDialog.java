/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balancemaker.ui;

import balancemaker.*;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import javafx.beans.Observable;
import javafx.beans.property.BooleanPropertyBase;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;
import org.jdesktop.xswingx.PromptSupport;

/**
 *
 * @author Red
 */
public class AddTransactionDialog extends javax.swing.JDialog {
    private final Manager manager;
    private final ExclusionSystem<Buyer> exclusion;
    private final ValidationSystem validation;
    private int debts = 0;

    /**
     * Creates new form AddTransactionDialog
     * @param parent
     * @param modal
     * @param manager
     */
    public AddTransactionDialog(java.awt.Frame parent, boolean modal, Manager manager) {
        super(parent, modal);
        initComponents();
        
        this.manager = manager;
        this.exclusion = new ExclusionSystem<>(Buyer.none, manager.buyers);
        this.validation = new ValidationSystem();
        
        postInit();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        storeTextBox = new javax.swing.JTextField();
        receiptTextBox = new javax.swing.JTextField();
        amountTextBox = new javax.swing.JTextField();
        paybackCheckBox = new javax.swing.JCheckBox();
        saveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        addDebt = new javax.swing.JButton();
        javax.swing.JScrollPane debtsScroller = new javax.swing.JScrollPane();
        debtsPanel = new org.jdesktop.swingx.JXPanel();
        buyerList = new javax.swing.JComboBox();
        date = new org.jdesktop.swingx.JXDatePicker();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add transaction");
        setMinimumSize(new java.awt.Dimension(300, 316));

        paybackCheckBox.setText("Payback");

        saveButton.setText("Save");
        saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveButtonMouseClicked(evt);
            }
        });

        cancelButton.setText("Cancer");
        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelButtonMouseClicked(evt);
            }
        });

        addDebt.setText("Add debt");
        addDebt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addDebtMouseClicked(evt);
            }
        });

        debtsScroller.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        debtsPanel.setScrollableHeightHint(org.jdesktop.swingx.ScrollableSizeHint.NONE);
        debtsPanel.setScrollableWidthHint(org.jdesktop.swingx.ScrollableSizeHint.FIT);

        javax.swing.GroupLayout debtsPanelLayout = new javax.swing.GroupLayout(debtsPanel);
        debtsPanel.setLayout(debtsPanelLayout);
        debtsPanelLayout.setHorizontalGroup(
            debtsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 358, Short.MAX_VALUE)
        );
        debtsPanelLayout.setVerticalGroup(
            debtsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        debtsScroller.setViewportView(debtsPanel);

        buyerList.setEditable(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(paybackCheckBox)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(saveButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addDebt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cancelButton))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(storeTextBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(amountTextBox))
                            .addComponent(receiptTextBox, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(debtsScroller, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buyerList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(10, 10, 10)
                                .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(storeTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(amountTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(receiptTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buyerList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(paybackCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(debtsScroller, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(cancelButton)
                    .addComponent(addDebt))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseClicked
        dispose();
    }//GEN-LAST:event_cancelButtonMouseClicked

    private void saveButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonMouseClicked
        // This should only be called if all fields are valid.
        List<String> bl = new LinkedList<>();
        if (!manager.existsBuyer(buyerList.getSelectedItem().toString()))
            bl.add(buyerList.getSelectedItem().toString());
        forEachDebtor(s -> { if (!manager.existsBuyer(s)) bl.add(s); });
        
        if (bl.size() > 0) {
            StringBuilder sb = new StringBuilder("Are you sure you want to" +
                    " keep track of the following buyers:\n");
            for (String s : bl) sb.append(s).append('\n');
            if (JOptionPane.showConfirmDialog(
                    this,
                    sb.toString(),
                    "Confirm addition",
                    JOptionPane.YES_NO_OPTION)
                == JOptionPane.NO_OPTION)
                return;
        }
        
        TransactionBuilder b = new TransactionBuilder();
        b.Store(storeTextBox.getText());
        b.Receipt(receiptTextBox.getText());
        b.Date(date.getDate());
        b.Amount(Float.valueOf(amountTextBox.getText()));
        b.Payback(paybackCheckBox.isSelected());
        b.Buyer(manager.getBuyer(buyerList.getSelectedItem().toString()));
        
        forEachDebt(dp -> b.addDebt(
                Float.parseFloat(dp.amount.getText()),
                manager.getBuyer(dp.debtor.getSelectedItem().toString())
        ));
        
        manager.transactions.add(b.createTransaction());
        
        dispose();
    }//GEN-LAST:event_saveButtonMouseClicked

    private void addDebtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addDebtMouseClicked
        DebtPanel dp = new DebtPanel(++debts);
        
        validation.install(dp.amount, ValidationSystem::isPositiveFloat);
        
        exclusion.install(dp.debtor);
        JTextComponent debtor = (JTextComponent) dp.debtor.getEditor().getEditorComponent();
        validation.install(debtor, exclusion.getPredicateFor(dp.debtor));
        
        debtsPanel.add(dp);
        
        dp.close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    debtsPanel.remove(dp);
                    exclusion.uninstall(dp.debtor);
                    validation.uninstall(dp.amount);
                    validation.uninstall(debtor);
                    debtsPanel.repaint();
                }
            }
        });
    }//GEN-LAST:event_addDebtMouseClicked

    // <editor-fold defaultstate="collapsed" desc="Form Components">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addDebt;
    private javax.swing.JTextField amountTextBox;
    private javax.swing.JComboBox buyerList;
    private javax.swing.JButton cancelButton;
    private org.jdesktop.swingx.JXDatePicker date;
    private org.jdesktop.swingx.JXPanel debtsPanel;
    private javax.swing.JCheckBox paybackCheckBox;
    private javax.swing.JTextField receiptTextBox;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField storeTextBox;
    // End of variables declaration//GEN-END:variables
            
    // </editor-fold>

    private void postInit() {
        debtsPanel.setLayout(new WrapLayout());
        
        exclusion.install(buyerList);
        
        PromptSupport.setPrompt("Enter store name", storeTextBox);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, storeTextBox);
        PromptSupport.setPrompt("Enter receipt or comment", receiptTextBox);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, receiptTextBox);
        PromptSupport.setPrompt("Enter amount", amountTextBox);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, amountTextBox);
        
        PromptSupport.setPrompt("Enter date", date.getEditor());
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, date.getEditor());
        
        date.setDate(Date.from(Instant.now()));
        
        validation.fieldsValid.addListener(this::validityChanged);
        
        validation.install(storeTextBox, ValidationSystem::isNotEmpty);
        validation.install(amountTextBox, ValidationSystem::isPositiveFloat);
        
        JTextComponent debtor = (JTextComponent) buyerList.getEditor().getEditorComponent();
        validation.install(debtor, exclusion.getPredicateFor(buyerList));
    }
    private void validityChanged(Observable o) {
        BooleanPropertyBase b = (BooleanPropertyBase) o;
        saveButton.setEnabled(b.get());
    }
    
    private void forEachDebt(Consumer<DebtPanel> cons) {
        for (Component debtPanel : debtsPanel.getComponents())
            if (debtPanel instanceof DebtPanel) {
                DebtPanel dp = (DebtPanel) debtPanel;
                cons.accept(dp);
            }
    }
    private void forEachDebtor(Consumer<String> cons) {
        for (Component debtPanel : debtsPanel.getComponents())
            if (debtPanel instanceof DebtPanel) {
                DebtPanel dp = (DebtPanel) debtPanel;
                String debtor = dp.debtor.getSelectedItem().toString();
                cons.accept(debtor);
            }
    }
}