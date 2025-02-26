package com.riderequestmanagement;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

public class RideList<T> {

    private T[] arr; // Array to store list elements
    private int size; // Current size of the list
    private int capacity; // Maximum capacity of the list

    @SuppressWarnings("unchecked")
    public RideList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
        this.capacity = capacity;
        this.arr = (T[]) new Object[capacity]; // Initialize array with given capacity
        this.size = 0;
    }

    /**
     * Adds an element to the list.
     * @param item The element to be added.
     * If the list is full, it resizes the array to double its capacity.
     * If the item is null, an IllegalArgumentException is thrown.
     */
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null element.");
        }
        if (size == capacity) {
            resizeArray();
        }
        arr[size++] = item;
    }

    /**
     * Removes an element at the specified index.
     * @param index The index of the element to be removed.
     * @return The removed element.
     * Throws NoSuchElementException if the list is empty.
     * Throws IndexOutOfBoundsException if the index is invalid.
     */
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
        return removedElement;
    }

    /**
     * Retrieves an element at the specified index.
     * @param index The index of the element to retrieve.
     * @return The element at the specified index.
     * Throws NoSuchElementException if the list is empty.
     * Throws IndexOutOfBoundsException if the index is invalid.
     */
    public T get(int index) {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot get element from an empty list.");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid Index");
        }
        return arr[index];
    }

    /**
     * Searches for a specific element in the list.
     * @param target The element to search for.
     * @return An Optional containing the element if found, otherwise an empty Optional.
     */
    public Optional<T> search(T target) {
        if (target == null || isEmpty()) {
            return Optional.empty();
        }
        for (int i = 0; i < size; i++) {
            if (target.equals(arr[i])) {
                System.out.println("Search Success in List: " + target + " found at index " + i);
                return Optional.of(arr[i]);
            }
        }
        System.err.println("Search Failure in List: " + target + " not found.");
        return Optional.empty();
    }
    
    /**
     * Removes a specific element from the list.
     * @param target The element to remove.
     * @return true if the element was removed, false otherwise.
     */
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
    
    /**
     * Clears all elements from the list.
     */
    public void clear() {
        if (isEmpty()) {
            System.err.println("WARNING: List is already empty.");
            return;
        }
        Arrays.fill(arr, null);
        size = 0;
        System.out.println("List cleared.");
    }

    /**
     * Resizes the list array when it reaches capacity.
     * The new capacity is doubled from the previous size.
     */
    private void resizeArray() {
        int newCapacity = capacity * 2;
        arr = Arrays.copyOf(arr, newCapacity);
        capacity = newCapacity;
        System.out.println("Resized list to new capacity: " + newCapacity);
    }

    /**
     * Checks if the list is empty.
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the current size of the list.
     * @return The number of elements in the list.
     */
    public int size() {
        return size;
    }

    /**
     * Returns a string representation of the list.
     * @return A formatted string listing all elements in the list.
     */
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
