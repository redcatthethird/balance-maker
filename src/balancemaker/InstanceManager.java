/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balancemaker;

import java.lang.ref.*;
import java.util.*;

/**
 *
 * @author Red
 */
public final class InstanceManager<T> {
    private final Map<Class<?>, List<Reference<T>>> refMap = new HashMap<>();
    
    public synchronized <U extends T> U manage(U instance) {
        Class<?> cls = instance.getClass();
        List<Reference<T>> refList = refMap.get(cls);
        
        if (refList == null) {
            refList = new LinkedList<>();
            refMap.put(cls, refList);
        }
        
        refList.add(new WeakReference<>(instance));
        return instance;
    }
    
    public synchronized <U extends T> List<U> getAll(Class<U> cls) {
        List<U> returnList = new LinkedList<>();
        
        List<Reference<T>> refList = refMap.get(cls);
        if (refList != null) {
            Iterator<Reference<T>> it = refList.iterator();
            while (it.hasNext()) {
                T instance = it.next().get();
                
                if (instance == null) it.remove();
                else returnList.add(cls.cast(instance));
            }
        }
        
        return returnList;
    }
}
