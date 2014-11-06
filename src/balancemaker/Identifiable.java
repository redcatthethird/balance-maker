/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balancemaker;

/**
 * 
 * @author Red
 */
public abstract class Identifiable {
    protected static int ID = 0;
    protected final int id;
    
    public Identifiable() {
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
        final Identifiable other = (Identifiable) obj;
        return this.id == other.id;
    }
}
