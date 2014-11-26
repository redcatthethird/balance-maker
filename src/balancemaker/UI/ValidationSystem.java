/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balancemaker.ui;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Red
 */
public class ValidationSystem {
    public Border attentionBorder = BorderFactory.createLineBorder(Color.RED, 2);
    public Border getDefaultBorder(JComponent c)
            throws InstantiationException, IllegalAccessException {
        return c.getClass().newInstance().getBorder();
    }
    public Border getInvalidBorder(JComponent c)
            throws InstantiationException, IllegalAccessException {
        return BorderFactory.createCompoundBorder(attentionBorder, getDefaultBorder(c));
    }
    
    private final DocumentListener textChangeListener = new DocumentListener() {
        @Override
        public void changedUpdate(DocumentEvent e) { validateFields(); }
        @Override
        public void removeUpdate(DocumentEvent e) { validateFields(); }
        @Override
        public void insertUpdate(DocumentEvent e) { validateFields(); }
    };
    
    private final Map<JTextComponent, Predicate<String>> validators = new HashMap<>();
    
    public final SimpleBooleanProperty fieldsValid = new SimpleBooleanProperty(true);
    
    public final static boolean isNotEmpty(String text) { return !text.isEmpty(); }
    public final static boolean isPositiveFloat(String text) {
        try {
            float f = Float.parseFloat(text);
            return f >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public boolean isValid(JTextComponent tc) { return validators.get(tc).test(tc.getText()); }
    
    public boolean contains(JTextComponent tc) { return validators.containsKey(tc); }
    public void install(JTextComponent tc, Predicate<String> pred) {
        validators.put(tc, pred);
        tc.getDocument().addDocumentListener(textChangeListener);
        validateFields();
    }
    public void uninstall(JTextComponent tc) {
        validators.remove(tc);
        tc.getDocument().removeDocumentListener(textChangeListener);
        validateFields();
    }
    
    public void validateFields() {
        boolean v = true;
        for (Map.Entry<JTextComponent, Predicate<String>> e : validators.entrySet()) {
            JTextComponent t = e.getKey();
            Boolean b = e.getValue().test(t.getText());
            v = v && b;
            try {
                t.setBorder(b ? getDefaultBorder(t) : getInvalidBorder(t));
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(AddTransactionDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        fieldsValid.set(v);
    }
}
