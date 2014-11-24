/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balancemaker.ui;

import balancemaker.*;
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
import javax.swing.text.JTextComponent;
import org.jdesktop.xswingx.PromptSupport;

/**
 *
 * @author Andu
 */
public class AddTransactionDialog extends javax.swing.JDialog {
    // TODO: Use borders for different kinds of components.
    private final Border defaultTextFieldBorder = new JTextField().getBorder();
    private final Border invalidTextFieldBorder = BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.RED, 2), defaultTextFieldBorder);
    // NOTE: Consider buddy icons as an alternative invalidity notifier.
    
    private final Map<JTextComponent, Predicate<String>> fieldValidators = new HashMap<>(3);
    
    private final SimpleBooleanProperty fieldsValid = new SimpleBooleanProperty(true);
    
    private final DocumentListener textChangeListener = new DocumentListener() {
        @Override
        public void changedUpdate(DocumentEvent e) { validateFields(); }
        @Override
        public void removeUpdate(DocumentEvent e) { validateFields(); }
        @Override
        public void insertUpdate(DocumentEvent e) { validateFields(); }
    };
    
    private final Manager manager;
    private final ExclusionSystem<Buyer> exclusion;
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
        
        postInit();
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
        addDebt = new javax.swing.JButton();
        javax.swing.JScrollPane debtsScroller = new javax.swing.JScrollPane();
        debtsPanel = new org.jdesktop.swingx.JXPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add transaction");
        setMinimumSize(new java.awt.Dimension(300, 316));

        jLabel2.setText("Date :");

        buyerList.setEditable(true);

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
            .addGap(0, 211, Short.MAX_VALUE)
        );
        debtsPanelLayout.setVerticalGroup(
            debtsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        debtsScroller.setViewportView(debtsPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(storeTextBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(amountTextBox))
                    .addComponent(receiptTextBox)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(saveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addDebt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buyerList, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2))
                    .addComponent(paybackCheckBox)
                    .addComponent(debtsScroller))
                .addContainerGap())
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(debtsScroller, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
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

    private void addDebtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addDebtMouseClicked
        DebtPanel dp = new DebtPanel(++debts);
        exclusion.install(dp.buyer);
        
        fieldValidators.put(dp.amount, AddTransactionDialog::isPositiveFloat);
        dp.amount.getDocument().addDocumentListener(textChangeListener);
        
        JTextComponent debtor = (JTextComponent) dp.buyer.getEditor().getEditorComponent();
        fieldValidators.put(debtor, exclusion.getPredicateFor(dp.buyer));
        debtor.getDocument().addDocumentListener(textChangeListener);
        
        debtsPanel.add(dp);
        
        validateFields();
        
        // TODO: Implement new Buyer addition.
    }//GEN-LAST:event_addDebtMouseClicked

    // <editor-fold defaultstate="collapsed" desc="Form Components">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addDebt;
    private javax.swing.JTextField amountTextBox;
    private javax.swing.JComboBox buyerList;
    private javax.swing.JButton cancelButton;
    private org.jdesktop.swingx.JXPanel debtsPanel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JCheckBox paybackCheckBox;
    private javax.swing.JTextField receiptTextBox;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField storeTextBox;
    // End of variables declaration//GEN-END:variables
            
    // </editor-fold>
    
    private void validateFields() {
        // FIXME: Separate input validation.
        boolean v = true;
        for (Map.Entry<JTextComponent, Predicate<String>> e : fieldValidators.entrySet()) {
            Boolean b = e.getValue().test(e.getKey().getText());
            v = v && b;
            Border border = b ? defaultTextFieldBorder : invalidTextFieldBorder;
            e.getKey().setBorder(border);
        }
        fieldsValid.set(v);
    }
    
    // TODO: Date selectioon!!
    
    private static boolean isNotEmpty(String text) { return !text.isEmpty(); }
    private static boolean isPositiveFloat(String text) {
        try {
            float f = Float.parseFloat(text);
            return f >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    // TODO: Implement buyer and debtor selection.

    private void postInit() {
        debtsPanel.setLayout(new WrapLayout());
        
        exclusion.install(buyerList);
        
        PromptSupport.setPrompt("Enter store name", storeTextBox);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, storeTextBox);
        PromptSupport.setPrompt("Enter receipt or comment", receiptTextBox);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, receiptTextBox);
        PromptSupport.setPrompt("Enter amount", amountTextBox);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, amountTextBox);
        
        fieldValidators.put(storeTextBox, AddTransactionDialog::isNotEmpty);
        fieldValidators.put(amountTextBox, AddTransactionDialog::isPositiveFloat);
        
        JTextComponent debtor = (JTextComponent) buyerList.getEditor().getEditorComponent();
        fieldValidators.put(debtor, exclusion.getPredicateFor(buyerList));
        debtor.getDocument().addDocumentListener(textChangeListener);
        
        for (JTextComponent tf : fieldValidators.keySet())
            tf.getDocument().addDocumentListener(textChangeListener);
        
        fieldsValid.addListener((o) -> saveButton.setEnabled(fieldsValid.get()));
        
        validateFields();
    }
}