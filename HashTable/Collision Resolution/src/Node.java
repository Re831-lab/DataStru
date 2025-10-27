public class Node {
    private Object data;
    private Object key;
    private Node next;
    public Node prev;

    public Node(Object data, Object key) {
        this.data = data;
        this.key = key;
        this.next = null;
        this.prev = null;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Object getKey() {
        return key;
    }

    public Object getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }
}