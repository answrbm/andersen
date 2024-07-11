package lesson6.arraylist;

import java.util.Arrays;
import java.util.Objects;

public class ArrayList<T> {

    private Object[] arr;
    private int currentIndex;

    public ArrayList() {
        this.arr = new Object[10];
    }

    public ArrayList(int capacity) {
        this.arr = new Object[capacity];
    }

    public T add(T elem) {
        if(currentIndex == arr.length) {
            arr = Arrays.copyOf(arr, (int) ((arr.length * 1.5) + 1));
        }
        arr[currentIndex] = elem;
        currentIndex++;
        return elem;
    }

    public T getByIndex(int index) {
        return (T) arr[index];
    }

    public void deleteByIndex(int index) {
        for(int i = index; i < arr.length - 1; i++) {
            arr[i] = arr[i+1];
            arr[i+1] = null;
        }
    }

    public String getAll() {
        return Arrays.toString(
                Arrays.stream(arr).filter(Objects::nonNull).toArray());
    }
}
