/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balancemaker.ui.xclusion;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.matchers.Matcher;
import ca.odell.glazedlists.swing.DefaultEventComboBoxModel;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;

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
public class XclusionSystem<U> /*implements ItemListener, ListEventListener<U> */{
    private final U defaultValue;
    private final List<U> selectables;
    private final List<DefaultComboBoxModel<U>> selectors = new LinkedList<>();
    
    public XclusionSystem(U defaultValue, List<U> selectables) {
        this.defaultValue = defaultValue;
        this.selectables = selectables;
        
        //selectables.addListEventListener(this);
    }
    
    public DefaultComboBoxModel<U> getModel() {
        DefaultComboBoxModel<U> model = new DefaultComboBoxModel<U>(selectables.toArray());
        selectors.add(model);
        return model;
    }
    /*public DefaultComboBoxModel<U> getModel(Comparator<U> comp) {
        DefaultComboBoxModel<U> model;
        model = new DefaultComboBoxModel(new SortedList<>(selectables, comp));
        selectors.add(model);
        return model;
    }*/
    
    private void onItemSelected(DefaultComboBoxModel<U> model, U value) {
        // Remove the selected item out of every other combobox.
        for (DefaultComboBoxModel<U> m : selectors) {
            if (!m.equals(model))
                m.
                
        }
    }
    
    /*public class XclusionComboBoxModel extends DefaultEventComboBoxModel<U> {

        public XclusionComboBoxModel() {
            super(new FilterList<>(selectables));
            ((FilterList)this.source).setMatcher(new XclusionMatcher(this));
            /*        e -> {
                
            for (XclusionComboBoxModel model : selectors)
                if (this.getSelectedItem().equals(value) && !model.equals(this))
                    return true;
            return false;
            }));
            selectors.add(this);
            Matcher<U> =
                (e) -> e.getValue() == 0 || e.getKey().equals(getSelectedItem());
            isSelectedByAnyExcept(defaultValue, this);
        }
        
    }
    
    private class XclusionMatcher implements Matcher<U> {
        private final XclusionComboBoxModel model;
        
        public XclusionMatcher (XclusionComboBoxModel model) {
            this.model = model;
        }

        @Override
        public boolean matches(U item) {
            for (DefaultEventComboBoxModel<U> currentModel : selectors)
                if (currentModel.getSelectedItem().equals(item) && !model.equals(currentModel))
                    return false;
            return true;
            }
        
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
    public void listChanged(ListEvent<U> listChanges) {
        while (listChanges.next()) {
            if (listChanges.isReordering()) continue;
            if (listChanges.getType() == ListEvent.DELETE) {
                U u = listChanges.getSourceList().get(listChanges.getIndex());
                    if (selectables.get(u) != 0)
                        listModels.get(selectables.get(u)).setSelectedItem();
                    selectables.remove(u);
            }
            if (listChanges.getType() == ListEvent.INSERT)
                selectables.put(listChanges.getSourceList().get(listChanges.getIndex()), (byte) 0);
        }
        for (XclusionListModel lm : listModels.values())
            lm.fireContentsChanged();
    }
    
    public final class XclusionListModel extends DefaultEventComboBoxModel<U>
            implements ItemSelectable {
        private U previouslySelected;
        private U selectedItem = defaultValue;
        
        public XclusionListModel() {
            super(new FilterList<>(selectables, u -> true), false);
            
            //selectors.add(this);
            
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
                    .map(e -> e.getKey()).collect(Collectors.toList()).get(index-1);
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
        public void setSelectedItem() { setSelectedItem(defaultValue); fireContentsChanged(); }
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
    }*/
}
