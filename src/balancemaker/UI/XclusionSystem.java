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
import javax.swing.JComboBox;

/**
 * For RESTORING_VALIDITY
 * @author Red
 * @param <U>
 */
public class XclusionSystem<U> implements ItemListener, ListEventListener<U> {
    private final U defaultValue;
    private final EventList<U> selectables;
    private final List<JComboBox<U>> selectors = new LinkedList<>();
    
    public XclusionSystem(U defaultValue, EventList<U> selectables) {
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
    }
}
