package lesson6.hashset.firstimplementation;

import java.util.HashMap;
import java.util.Iterator;

public class HashSet<T> {

    private HashMap<T,Object> map;

    public HashSet() {
        this.map = new HashMap<>();
    }

    public T add(T elem) {
        map.put(elem,null);
        return elem;
    }

    public boolean contains(T elem) {
        return map.containsKey(elem);
    }

    public Iterator<T> iterate() {
        return map.keySet().iterator();
    }

    public T remove(T elem) {
        map.remove(elem);
        return elem;
    }
}
