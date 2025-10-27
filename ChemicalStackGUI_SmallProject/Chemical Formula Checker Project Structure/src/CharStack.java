public class CharStack {
    private char[] stack;
    private int top;
    private int capacity;

    public CharStack() {
        this.capacity = 100;
        this.stack = new char[capacity];
        this.top = -1;
    }

    public void push(char item) {
        if (top >= capacity - 1) {
            throw new RuntimeException("Stack is full!");
        }
        stack[++top] = item;
    }

    public char pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }
        return stack[top--];
    }

    public char peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }
        return stack[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }

    public void clear() {
        top = -1;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Stack is empty";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i <= top; i++) {
            sb.append(stack[i]);
            if (i < top) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}