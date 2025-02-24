package com.riderequestmanagement;

import java.util.Optional;

public class RideQueue<T> {

    private T[] arr;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    @SuppressWarnings("unchecked")
    public RideQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
        this.capacity = capacity;
        this.arr = (T[]) new Object[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    // Enqueue (Only from rear)
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

    // Dequeue (Only from front)
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue Underflow: cannot dequeue, queue is empty.");
        }
        T item = arr[front];
        arr[front] = null;
        front += 1;
        size--;
        return item;
    }

    public T front() {
        if (isEmpty()) {
            throw new RuntimeException("Cannot get front, queue is empty.");
        }
        return arr[front];
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public boolean isFull() {
        return (size == capacity);
    }

    public int size() {
        return size;
    }

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
        System.err.println("Search Failurein Queue: " + target + " not found.");
        return Optional.empty();
    }

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