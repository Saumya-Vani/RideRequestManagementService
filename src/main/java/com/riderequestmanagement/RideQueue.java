package com.riderequestmanagement;

import java.util.Optional;

public class RideQueue<T> {

    private T[] arr; // Array to store queue elements
    private int front; // Index of the front element
    private int rear; // Index of the rear elementSDSD
    private int size; // Current size of the queue
    private int capacity; // Maximum capacity of the queue

    @SuppressWarnings("unchecked")
    public RideQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
        this.capacity = capacity;
        this.arr = (T[]) new Object[capacity]; // Initialize array with given capacity
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    /**
     * Enqueues an element to the rear of the queue.
     * @param item The element to be added to the queue.
     * If the queue is full, a warning message is printed.
     * If the item is null, an IllegalArgumentException is thrown.
     */
    public void enqueue(T item) {
        if (isFull()) {
            System.err.println("WARNING: Queue Overflow: cannot enqueue, queue is full.");
            return;
        }
        if (item == null) {
            throw new IllegalArgumentException("Cannot enqueue null element.");
        }
        rear += 1;
        arr[rear] = item;
        size++;
    }

    /**
     * Dequeues an element from the front of the queue.
     * @return The dequeued element.
     * Throws a RuntimeException if the queue is empty.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue Underflow: cannot dequeue, queue is empty.");
        }
        T item = arr[front]; // Store front element
        arr[front] = null; // Remove front element
        front += 1;
        size--;
        return item;
    }

    /**
     * Retrieves the front element of the queue without removing it.
     * @return The front element of the queue.
     * Throws a RuntimeException if the queue is empty.
     */
    public T front() {
        if (isEmpty()) {
            throw new RuntimeException("Cannot get front, queue is empty.");
        }
        return arr[front];
    }

    /**
     * Checks if the queue is empty.
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Checks if the queue is full.
     * @return true if the queue is full, false otherwise.
     */
    public boolean isFull() {
        return (size == capacity);
    }

    /**
     * Returns the current size of the queue.
     * @return The number of elements in the queue.
     */
    public int size() {
        return size;
    }

    /**
     * Searches for a specific element in the queue.
     * @param target The element to search for.
     * @return An Optional containing the element if found, otherwise an empty Optional.
     */
    public Optional<T> search(T target) {
        if (target == null || isEmpty()) {
            return Optional.empty();
        }
        for (int i = 0; i < size; i++) {
            int index = front + i;
            if (target.equals(arr[index])) {
                System.out.println("Search Success in Queue: " + target + " found at index " + index);
                return Optional.of(arr[index]);
            }
        }
        System.err.println("Search Failure in Queue: " + target + " not found.");
        return Optional.empty();
    }

    /**
     * Removes a specific element from the queue without dequeuing others.
     * @param target The element to remove.
     * @return true if the element is found and removed, false otherwise.
     */
    public boolean removeElement(T target) {
        if (target == null || isEmpty()) {
            return false;
        }
        boolean found = false;
        for (int i = 0; i < size; i++) {
            int index = front + i;
            if (target.equals(arr[index])) {
                arr[index] = null;
                found = true;
                break;
            }
        }
        return found;
    }

    /**
     * Clears all elements from the queue, resetting it to an empty state.
     */
    
     public void clear() {
        if (isEmpty()) {
            System.err.println("WARNING: Queue is already empty.");
            return;
        }

        for (int i = 0; i < capacity; i++) {
            arr[i] = null;
        }
        front = 0;
        rear = -1;
        size = 0;
        System.out.println("Queue cleared.");
    }


    /**
     * Returns a string representation of the queue, displaying its elements in order.
     * @return A string representation of the queue.
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "RideQueue is empty.";
        }
        StringBuilder sb = new StringBuilder("Front -> ");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            sb.append("[").append(arr[index]).append("]");
            if (i < size - 1) {
                sb.append(" -> ");
            }
        }
        sb.append(" <- Rear (size=").append(size).append(")");
        return sb.toString();
    }
}
