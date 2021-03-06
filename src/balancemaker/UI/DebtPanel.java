/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balancemaker.ui;

import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;
import org.jdesktop.xswingx.PromptSupport;

/**
 *
 * @author Red
 */
public class DebtPanel extends javax.swing.JPanel {
    private final int id;

    /**
     * Creates new form DebtPanel
     * @param id
     */
    public DebtPanel(int id) {
        initComponents();
        
        this.id = id;
        
        TitledBorder tb = (TitledBorder)getBorder();
        tb.setTitle(tb.getTitle() + id);
        
        setName(getName() + id);
        
        JTextComponent debtorEditor = ((JTextComponent)debtor.getEditor().getEditorComponent());
        PromptSupport.setPrompt("Debtor #" + id, debtorEditor);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, debtorEditor);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        debtor = new javax.swing.JComboBox<balancemaker.Buyer>();
        amount = new javax.swing.JTextField();
        close = new org.jdesktop.swingx.JXButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Debt #"));
        setName("Debt #"); // NOI18N

        debtor.setEditable(true);

        close.setBackground(new java.awt.Color(153, 0, 0));
        close.setBorder(null);
        close.setForeground(new java.awt.Color(204, 204, 204));
        close.setText("x");
        close.setMargin(new java.awt.Insets(1, 5, 1, 5));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(amount)
                        .addGap(0, 0, 0)
                        .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(debtor, 0, 87, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(debtor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField amount;
    public org.jdesktop.swingx.JXButton close;
    public javax.swing.JComboBox<balancemaker.Buyer> debtor;
    // End of variables declaration//GEN-END:variables
}
