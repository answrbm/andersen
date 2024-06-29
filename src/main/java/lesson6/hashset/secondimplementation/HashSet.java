package lesson6.hashset.secondimplementation;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class HashSet<T> {

    private HashSet<T>.Node<T>[] keys;
    private final int INITIAL_CAPACITY = 16;
    private int currentCapacity;

    public HashSet() {
        this.keys = (HashSet<T>.Node<T>[]) new HashSet.Node[INITIAL_CAPACITY];
    }

    private HashSet<T>.Node<T>[] resize() {
        return Arrays.copyOf(keys,keys.length * 2);
    }

    public boolean add(T elem) {
        if(currentCapacity == keys.length) {
            keys = resize();
        }
        int index = hash(elem);
        if(keys[index] != null) {
            Node<T> node = keys[index];
            if(node.key.equals(elem)) {
                return false;
            }
            Node<T> lastNode = node.next;
            while((lastNode = lastNode.next) != null) {
                if (lastNode.key.equals(elem)) {
                    return false;
                }
            }
            lastNode = new Node<>(elem);
        } else {
            keys[index] = new Node<>(elem);
            currentCapacity++;
        }
        return true;
    }

    public void remove(T elem) {
        int index = hash(elem);
        if(keys[index] != null) {
            Node<T> node = keys[index];
            if(node.key.equals(elem)) {
                keys[index] = null;
                return;
            }
            Node<T> lastNode;
             while((lastNode = node.next) != null) {
                if(lastNode.key.equals(elem)) {
                    node.next = null;
                }
            }
        }
    }

    public boolean contains(T elem) {
        int index = hash(elem);
        if(keys[index] != null) {
            Node<T> node = keys[index];
            if (node.key.equals(elem)) {
                return true;
            }
            Node<T> lastNode;
            while ((lastNode = node.next) != null) {
                if (lastNode.key.equals(elem)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
    * Generates hash to the element
    * @param elem the element for which to generate a hash
    */
    private int hash(T elem) {
        return elem.hashCode() % keys.length;
    }

    public Iterator<Node<T>> iterate() {
        return Arrays.stream(keys).filter(Objects::nonNull).iterator();
    }

    public class Node<T> {

        final T key;
        Node<T> next;

        public Node(T key) {
            this.key = key;
        }

        public T getKey() {
            return key;
        }

        public Node<T> getNext() {
            return next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", next=" + next +
                    '}';
        }
    }

}
