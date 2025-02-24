package com.riderequestmanagement;

import java.util.Optional;

public class RideDeQueue<T> {

    private T[] arr;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    @SuppressWarnings("unchecked")
    public RideDeQueue(int capacity) {
    	if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
        this.capacity = capacity;
        this.arr = (T[]) new Object[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public void enqueue(T item) {
        if (isFull()) {
        	System.err.println("WARNING: DeQueue Overflow: cannot enqueue, queue is full.");
            return;
        }
        if (item == null) {
            throw new IllegalArgumentException("Cannot enqueue null element.");
        }
        rear = (rear + 1) % capacity;
        arr[rear] = item;
        size++;
        //System.out.println("Added To the Queue: " + item);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("DeQueue Underflow: cannot dequeue, queue is empty.");
        }
        T item = arr[front];
        arr[front] = null;
        front = (front + 1) % capacity;
        size--;
        //System.out.println("Removed From the Queue: " + item);
        return item;
    }

    public T front() {
        if (isEmpty()) {
            throw new RuntimeException("Cannot get front, dequeue is empty.");
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
            int index = (front + i) % capacity;
            if (target.equals(arr[index])) { 
            	System.out.println("Search Success: " + target + " found at index " + index);
                return Optional.of(arr[index]);
            }
        }
        System.err.println("Search Failure: " + target + " not found.");
        return Optional.empty();
    }

    public boolean removeElement(T target) {
        if (target == null || isEmpty()) {
            return false;
        }
        boolean found = false;
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            if (target.equals(arr[index])) {
                arr[index] = null; // Mark as removed
                found = true;
                break;
            }
        }
        return found;
    }

	public void clear() {
		if (isEmpty()) {
			System.err.println("WARNING: DeQueue is already empty.");
			return;
		}

		for (int i = 0; i < capacity; i++) {
			arr[i] = null;
		}
		front = 0;
		rear = -1;
		size = 0;
		System.out.println("DeQueue cleared.");
	}

    @Override
    public String toString() {
        if (isEmpty()) {
            return "RideDeQueue is empty.";
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
