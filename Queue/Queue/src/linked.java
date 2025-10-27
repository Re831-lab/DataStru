public class linked {

    Node head;
    int num = 0;
    Node tail;

    public linked() {
    }

    public boolean addFirst(Object data) {
        if (head == null) {
            head = new Node(data);
            tail = head;
        } else {
            Node newNode = new Node(data);
            newNode.setNext(head);
            head = newNode;
        }
        num++;
        return true;
    }

    public boolean addLast(Object data) {
        if (head == null) {
            head = new Node(data);
            tail = head;
            num++;
            return true;
        }

        Node w = new Node(data);
        tail.setNext(w);
        tail = w;
        num++;
        return true;
    }

    public Object removeLast() {
        if (head == null) {
            return null;
        }

        // Handles the single node case
        if (head.getNext() == null) {
            Object data = head.getData();
            head = null;
            tail = null;
            num--;
            return data;
        }

        Node temp = head;
        while (temp.getNext().getNext() != null) {
            temp = temp.getNext();
        }

        Node tD = temp.getNext();
        temp.setNext(null);
        tail = temp;
        num--;
        return tD.getData();
    }

    // A simple Node class for demonstration.
    private class Node {
        Object data;
        Node next;

        public Node(Object data) {
            this.data = data;
            this.next = null;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Object getData() {
            return data;
        }
    }
}