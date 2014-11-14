/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balancemaker.UI.xclusion;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.matchers.Matcher;
import ca.odell.glazedlists.swing.DefaultEventComboBoxModel;

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
    private final EventList<U> selectables;
    private final EventList<XclusionComboBoxModel> selectors = new BasicEventList<>();
    
    public XclusionSystem(U defaultValue, EventList<U> selectables) {
        this.defaultValue = defaultValue;
        this.selectables = selectables;
        
        //selectables.addListEventListener(this);
    }
    
    public class XclusionComboBoxModel extends DefaultEventComboBoxModel<U> {

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
            isSelectedByAnyExcept(defaultValue, this);*/
        }
        
    }
    
    private class XclusionMatcher implements Matcher<U> {
        private final XclusionComboBoxModel model;
        
        public XclusionMatcher (XclusionComboBoxModel model) {
            this.model = model;
        }

        @Override
        public boolean matches(U item) {
            for (XclusionComboBoxModel currentModel : selectors)
                if (currentModel.getSelectedItem().equals(item) && !model.equals(currentModel))
                    return false;
            return true;
            }
        
    }

    /*@Override
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
