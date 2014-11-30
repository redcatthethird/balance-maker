/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balancemaker.ui;

import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.event.ListEventListener;
import ca.odell.glazedlists.swing.DefaultEventComboBoxModel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 * For RESTORING_VALIDITY
 * @author Red
 * @param <U>
 */
public class ExclusionSystem<U> implements ItemListener, ListEventListener<U> {
    protected final U defaultValue;
    protected final EventList<U> selectables;
    protected final List<JComboBox<U>> selectors = new LinkedList<>();
    
    public ExclusionSystem(U defaultValue, EventList<U> selectables) {
        this.defaultValue = defaultValue;
        this.selectables = GlazedLists.eventList(selectables);
        this.selectables.add(0, defaultValue);
        
        selectables.addListEventListener(this);
    }
    
    public void install(JComboBox<U> cb) {
        cb.setModel(new DefaultEventComboBoxModel<>(selectables));
        selectors.add(cb);
        cb.addItemListener(this);
        cb.setSelectedItem(defaultValue);
    }
    public void uninstall(JComboBox<U> cb) {
        selectors.remove(cb);
        cb.removeItemListener(this);
        cb.setModel(new DefaultComboBoxModel<>());
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        // On selection only,
        if (e.getStateChange() == ItemEvent.SELECTED)
            // If the same item is selected in any other JComboBox
            for (JComboBox cb : selectors)
                if (!cb.equals(e.getSource()) && e.getItem().equals(cb.getSelectedItem()))
                    // Deselect it.
                    cb.setSelectedItem(defaultValue);
    }

    @Override
    public void listChanged(ListEvent<U> listChanges) {
        while (listChanges.next() && !listChanges.isReordering()) {
            if (listChanges.getType() == ListEvent.DELETE)
            {
                U deleted = selectables.get(1 + listChanges.getIndex());
                for (JComboBox<U> cb : selectors)
                    if (cb.getSelectedItem().equals(deleted)) {
                        cb.setSelectedItem(defaultValue);
                        break;
                    }
                selectables.remove(deleted);
            }
        }
        selectables.clear();
        selectables.addAll(listChanges.getSourceList());
        selectables.add(0, defaultValue);
    }
    
    public Predicate<String> getPredicateFor(JComboBox<U> selector) {
        if (selector == null || !selectors.contains(selector))
            throw new IllegalArgumentException();
        return (String t) -> {
            // The default value is not valid
            if (t.equals(defaultValue.toString())) return false;
            // Go through all other selectors
            for (JComboBox<U> cb : selectors) {
                if (selector.equals(cb)) continue;
                // If the string is anywhere else as well, it's invalid here
                if (cb.getSelectedItem().toString().equals(t))
                    return false;
            }
            return true;
        };
    }

}
