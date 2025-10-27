/**
 * Custom Queue implementation using Array
 * Demonstrates manual implementation of Queue data structure
 */
public class CustomQueue {

    private String[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    // Constructor
    public CustomQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new String[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    /**
     * Add element to rear of queue (Enqueue)
     * @param item element to add
     * @return true if successful, false if queue is full
     */
    public boolean enqueue(String item) {
        if (isFull()) {
            return false;
        }

        rear = (rear + 1) % capacity;
        queue[rear] = item;
        size++;
        return true;
    }

    /**
     * Remove element from front of queue (Dequeue)
     * @return removed element, null if queue is empty
     */
    public String dequeue() {
        if (isEmpty()) {
            return null;
        }

        String item = queue[front];
        queue[front] = null;
        front = (front + 1) % capacity;
        size--;
        return item;
    }

    /**
     * View front element without removing (Peek)
     * @return front element, null if queue is empty
     */
    public String peek() {
        if (isEmpty()) {
            return null;
        }
        return queue[front];
    }

    /**
     * Check if queue is empty
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Check if queue is full
     * @return true if full, false otherwise
     */
    public boolean isFull() {
        return size == capacity;
    }

    /**
     * Get current size of queue
     * @return number of elements in queue
     */
    public int size() {
        return size;
    }

    /**
     * Get maximum capacity of queue
     * @return maximum capacity
     */
    public int capacity() {
        return capacity;
    }

    /**
     * Clear all elements from queue
     */
    public void clear() {
        front = 0;
        rear = -1;
        size = 0;
        for (int i = 0; i < capacity; i++) {
            queue[i] = null;
        }
    }

    /**
     * Convert queue to array for display
     * @return array representation of queue
     */
    public String[] toArray() {
        String[] result = new String[size];
        int index = 0;
        int current = front;

        for (int i = 0; i < size; i++) {
            result[index++] = queue[current];
            current = (current + 1) % capacity;
        }

        return result;
    }
}