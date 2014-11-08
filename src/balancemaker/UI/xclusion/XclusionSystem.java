/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balancemaker.UI.xclusion;

import java.awt.ItemSelectable;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 * For PREVENTING_INVALIDITY
 Assumptions:
  - a single default value
  - the listModels will not change
  - the initial state is valid
  - every selection is valid
  - the backing list only supports removals and additions
 * 
 * @author Red
 * @param <U>
 */
public class XclusionSystem<U> implements ItemListener, ListChangeListener<U> {
    private final U defaultValue;
    private final Map<U, Byte> selectables;
    private final Map<Byte, XclusionListModel> listModels;
    private Byte count = 0;
    
    public XclusionSystem(U defaultValue) {
        this(defaultValue, FXCollections.emptyObservableList());
    }
    public XclusionSystem(U defaultValue, ObservableList<U> selectableCollection) {
        this.defaultValue = defaultValue;
        this.listModels = new TreeMap<>();
        
        selectables = new LinkedHashMap<>();
        for (U t : selectableCollection) selectables.putIfAbsent(t, (byte)0);
        
        selectableCollection.addListener(this);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.DESELECTED) {
            selectables.put((U) e.getItem(), (byte) 0);
        }
        else if (e.getStateChange() == ItemEvent.SELECTED){
            selectables.put((U) e.getItem(), ((XclusionListModel)e.getSource()).ownCount);
        }
    }

    @Override
    public void onChanged(Change<? extends U> c) {
        while (c.next()) {
            if (c.wasRemoved())
                for (U u : c.getRemoved()) {
                    if (selectables.get(u) != 0)
                        listModels.get(selectables.get(u)).setSelectedItem();
                    selectables.remove(u);
                }
            if (c.wasAdded())
                for (U u : c.getAddedSubList())
                    selectables.put(u, (byte) 0);
        }
        for (XclusionListModel lm : listModels.values())
            lm.fireContentsChanged();
    }
    
    public final class XclusionListModel extends AbstractListModel<U>
            implements ComboBoxModel<U>, ItemSelectable {
        private U previouslySelected;
        private U selectedItem = defaultValue;
        private final Byte ownCount;
        
        public XclusionListModel() {
            ownCount = ++count;
            listModels.put(ownCount, this);
            addItemListener(XclusionSystem.this);
        }

        @Override
        public int getSize() {
            return (int) selectables.entrySet().stream()
                    .filter(e -> e.getValue() == 0 || e.getKey().equals(selectedItem))
                    .count() + 1;
        }
        @Override
        public U getElementAt(int index) {
            if (index == 0) return defaultValue;
            else return selectables.entrySet().stream()
                    .filter(e -> e.getValue() == 0 || e.getKey().equals(selectedItem))
                    .map(e -> e.getKey()).collect(Collectors.toList()).get(index);
        }

        @Override
        public void setSelectedItem(Object anItem) {
            if ((selectedItem != null && !selectedItem.equals( anItem )) ||
                    selectedItem == null && anItem != null ) {
                previouslySelected = selectedItem;
                selectedItem = (U) anItem;
                fireContentsChanged();
                // If we're moving from a non-default value, we're deselecting.
                if (!previouslySelected.equals(defaultValue))
                    fireOnDeselection();
                // If we're moving to a non-default value, we're selecting
                if (!selectedItem.equals(defaultValue))
                    fireOnSelection();
            }
        }
        public void setSelectedItem() { setSelectedItem(defaultValue); }
        @Override
        public Object getSelectedItem() { return selectedItem; }

        @Override
        public Object[] getSelectedObjects() { return new Object[]{ selectedItem }; }
        protected List<ItemListener> itemListenerList = new ArrayList<>();
        @Override
        public void addItemListener(ItemListener l) { itemListenerList.add(l); }
        @Override
        public void removeItemListener(ItemListener l) { itemListenerList.remove(l); }

        private void fireContentsChanged() { fireContentsChanged(this, -1, -1); }
        private void fireOnSelection() {
            for (ItemListener l : itemListenerList)
                l.itemStateChanged(new ItemEvent(this, 0, selectedItem, ItemEvent.SELECTED));
        }
        private void fireOnDeselection() {
            for (ItemListener l : itemListenerList)
                l.itemStateChanged(new ItemEvent(this, 0, previouslySelected, ItemEvent.DESELECTED));
        }
    }
}
