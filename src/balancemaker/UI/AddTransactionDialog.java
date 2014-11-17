/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balancemaker.ui;

import balancemaker.*;
import balancemaker.ui.xclusion.XclusionSystem;
import java.awt.Color;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.SimpleBooleanProperty;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.jdesktop.xswingx.PromptSupport;

/**
 *
 * @author Andu
 */
public class AddTransactionDialog extends javax.swing.JDialog {
    private final Border defaultTextFieldBorder = new JTextField().getBorder();
    private final Border invalidTextFieldBorder = BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.RED, 2), defaultTextFieldBorder);
    
    private final Map<JTextField, Predicate<String>> fieldValidators = new HashMap<>(3);
    private BooleanPropertyBase fieldsAreValid = new SimpleBooleanProperty(true);
    private final DocumentListener textChangeListener = new DocumentListener() {
        @Override
        public void changedUpdate(DocumentEvent e) { validateFields(); }
        @Override
        public void removeUpdate(DocumentEvent e) { validateFields(); }
        @Override
        public void insertUpdate(DocumentEvent e) { validateFields(); }
    };
    
    private final Manager manager;
    private final XclusionSystem<Buyer> exclusion;

    /**
     * Creates new form AddTransactionDialog
     * @param parent
     * @param modal
     * @param manager
     */
    public AddTransactionDialog(java.awt.Frame parent, boolean modal, Manager manager) {
        super(parent, modal);
        
        this.manager = manager;
        this.exclusion = new XclusionSystem<>(Buyer.none, manager.buyers);
        
        initComponents();
        
        PromptSupport.setPrompt("Enter store name", storeTextBox);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, storeTextBox);
        PromptSupport.setPrompt("Enter receipt or comment", receiptTextBox);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, receiptTextBox);
        PromptSupport.setPrompt("Enter amount", amountTextBox);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, amountTextBox);
        
        fieldValidators.put(storeTextBox, AddTransactionDialog::isNotEmpty);
        fieldValidators.put(amountTextBox, AddTransactionDialog::isValidFloat);
        
        for (JTextField tf : fieldValidators.keySet())
            tf.getDocument().addDocumentListener(textChangeListener);
        
        fieldsAreValid.addListener((o) -> saveButton.setEnabled(fieldsAreValid.get()));
        
        this.fieldsAreValid.set(false);
        
        validateFields();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        storeTextBox = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        receiptTextBox = new javax.swing.JTextField();
        amountTextBox = new javax.swing.JTextField();
        buyerList = new javax.swing.JComboBox();
        paybackCheckBox = new javax.swing.JCheckBox();
        saveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox();
        jComboBox4 = new javax.swing.JComboBox();
        jComboBox6 = new javax.swing.JComboBox();
        jComboBox8 = new javax.swing.JComboBox();
        jComboBox9 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add transaction");
        setMinimumSize(new java.awt.Dimension(300, 316));

        jLabel2.setText("Date :");

        buyerList.setModel(exclusion.getModel(Buyer.idComparator););

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

        jComboBox3.setModel(exclusion.getModel(Buyer.idComparator););

        jComboBox4.setModel(exclusion.getModel(Buyer.idComparator););

        jComboBox6.setModel(exclusion.getModel(Buyer.idComparator););

        jComboBox8.setModel(exclusion.getModel(Buyer.idComparator););

        jComboBox9.setModel(exclusion.getModel(Buyer.idComparator););

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(storeTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(amountTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(receiptTextBox)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(saveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buyerList, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2))
                            .addComponent(paybackCheckBox)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paybackCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(cancelButton))
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseClicked
        dispose();
    }//GEN-LAST:event_cancelButtonMouseClicked

    private void saveButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonMouseClicked
        manager.transactions.add(new TransactionBuilder()
                .Store(storeTextBox.getText())
                .Receipt(receiptTextBox.getText())
                .Date(Date.from(Instant.now()))
                .Amount(Float.valueOf(amountTextBox.getText()))
                .Payback(paybackCheckBox.isSelected())
                .Buyer(manager.buyers.get(0))
                .createTransaction());
        dispose();
    }//GEN-LAST:event_saveButtonMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amountTextBox;
    private javax.swing.JComboBox buyerList;
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox6;
    private javax.swing.JComboBox jComboBox8;
    private javax.swing.JComboBox jComboBox9;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JCheckBox paybackCheckBox;
    private javax.swing.JTextField receiptTextBox;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField storeTextBox;
    // End of variables declaration//GEN-END:variables

    private void validateFields() {
        boolean v = true;
        for (Map.Entry<JTextField, Predicate<String>> e : fieldValidators.entrySet()) {
            Boolean b = e.getValue().test(e.getKey().getText());
            v = v && b;
            Border border = b ? defaultTextFieldBorder : invalidTextFieldBorder;
            e.getKey().setBorder(border);
        }
        fieldsAreValid.set(v);
    }
    
    private static boolean isNotEmpty(String text) { return !text.isEmpty(); }
    private static boolean isValidFloat(String text) {
        try {
            Float.parseFloat(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    // TODO: Implement buyer and debtor selection.
}