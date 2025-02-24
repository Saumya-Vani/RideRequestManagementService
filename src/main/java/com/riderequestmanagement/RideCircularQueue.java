package com.riderequestmanagement;

import java.util.Optional;

public class RideCircularQueue<T> {

    private T[] arr;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    @SuppressWarnings("unchecked")
    public RideCircularQueue(int capacity) {
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
            throw new RuntimeException("CircularQueue Overflow: Queue is full.");
        }
        if (item == null) {
            throw new IllegalArgumentException("Cannot enqueue null element.");
        }
        rear = (rear + 1) % capacity;
        arr[rear] = item;
        size++;
    }


    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("CircularQueue Underflow: cannot dequeue, queue is empty.");
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
            throw new RuntimeException("Cannot get front, CircularQueue is empty.");
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
            	System.out.println("Search Success in Circular Queue: " + target + " found at index " + index);
                return Optional.of(arr[index]);
            }
        }
        System.err.println("Search Failure in Circular Queue: " + target + " not found.");
        return Optional.empty();
    }

    public boolean removeElement(T target) {
        if (target == null || isEmpty()) {
            return false;
        }
        int removeIndex = -1;
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            if (target.equals(arr[index])) {
                removeIndex = index;
                break;
            }
        }
        if (removeIndex == -1) {
            return false;
        }
        for (int i = removeIndex; i != rear; i = (i + 1) % capacity) {
            int nextIndex = (i + 1) % capacity;
            arr[i] = arr[nextIndex];
        }
        arr[rear] = null;
        rear = (rear - 1 + capacity) % capacity;
        size--;
        return true;
    }


	public void clear() {
		if (isEmpty()) {
			System.err.println("WARNING: CircularQueue is already empty.");
			return;
		}

		for (int i = 0; i < capacity; i++) {
			arr[i] = null;
		}
		front = 0;
		rear = -1;
		size = 0;
		System.out.println("CircularQueue cleared.");
	}

    @Override
    public String toString() {
        if (isEmpty()) {
            return "RideCircularQueue is empty.";
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
