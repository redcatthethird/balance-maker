/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balancemaker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Red
 */
public abstract class IdentifiableInstanceManager {
    protected static int ID = 0;
    protected final int id;
    
    public IdentifiableInstanceManager() {
        this.id = ++ID;
    }
    
    public int getId() { return id; }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IdentifiableInstanceManager other = (IdentifiableInstanceManager) obj;
        return this.id == other.id;
    }
    
    
    
    protected static ArrayList<? extends IdentifiableInstanceManager> list = new ArrayList<>();
    protected static Map<Class, ArrayList<?>> map = new HashMap<>();
    
    //TODO: Implement InstanceManager functionality here and remove it from Manager
}
