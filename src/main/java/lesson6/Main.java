package lesson6;

import lesson6.arraylist.ArrayList;
import lesson6.hashset.secondimplementation.HashSet;

import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(11);

        System.out.println(list.getAll());

        list.deleteByIndex(4);

        System.out.println(list.getAll());

        System.out.println(list.getByIndex(4));

        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);
        set.add(6);
        set.add(7);
        set.add(8);
        set.add(9);
        set.add(10);
        set.add(11);
        set.add(12);
        set.add(13);
        set.add(14);
        set.add(15);
        set.add(16);
        set.add(17);
        set.add(18);

        Iterator<HashSet<Integer>.Node<Integer>> iterator = set.iterate();
        while(iterator.hasNext()) {
            System.out.println(iterator.next().getKey());
        }
    }
}
