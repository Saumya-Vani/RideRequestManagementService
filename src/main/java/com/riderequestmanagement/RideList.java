package com.riderequestmanagement;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

public class RideList<T> {

	private T[] arr;
    private int size;
    private int capacity;

    @SuppressWarnings("unchecked")
    public RideList(int capacity) {
    	if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
        this.capacity = capacity;
        this.arr = (T[]) new Object[capacity];
        this.size = 0;
    }

    public void add(T item) {
    	if (item == null) {
            throw new IllegalArgumentException("Cannot add null element.");
        }
    	if (size == capacity) {
            resizeArray();
        }
        arr[size++] = item;
        //System.out.println("Added to the Linked List: " + item);
    }

    public T remove(int index) {
    	if (isEmpty()) {
            throw new NoSuchElementException("Cannot remove from an empty list.");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid Index");
        }
        T removedElement = arr[index];
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[--size] = null;
        //System.out.println("Removed from the Linked List: " + removedElement);
        return removedElement;
    }

    public T get(int index) {
    	if (isEmpty()) {
            throw new NoSuchElementException("Cannot get element from an empty list.");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid Index");
        }
        return arr[index];
    }

    public Optional<T> search(T target) {
        if (target == null || isEmpty()) {
            return Optional.empty();
        }
        for (int i = 0; i < size; i++) {
            if (target.equals(arr[i])) {
                return Optional.of(arr[i]);
            }
        }
        return Optional.empty();
    }
    
    public boolean removeElement(T target) {
        if (target == null || isEmpty()) {
            return false;
        }
        int removeIndex = -1;
        for (int i = 0; i < size; i++) {
            if (target.equals(arr[i])) {
                removeIndex = i;
                break;
            }
        }
        if (removeIndex == -1) {
            return false;
        }
        return remove(removeIndex) != null; 
    }
    
    public void clear() {
    	if (isEmpty()) {
    		System.err.println("WARNING: List is already empty.");
            return;
        }
        Arrays.fill(arr, null);
        size = 0;
        System.out.println("List cleared.");
    }

    private void resizeArray() {
        int newCapacity = capacity * 2;
        arr = Arrays.copyOf(arr, newCapacity);
        capacity = newCapacity;
        System.out.println("Resized list to new capacity: " + newCapacity);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "RideList is empty.";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(i + 1).append(". ").append(arr[i]).append("\n");
        }
        return sb.toString();
    }
}