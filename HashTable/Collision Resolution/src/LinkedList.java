public class LinkedList {
    private Node head;
    private Node tail;
    private int num;

    public boolean addLast(Object data, Object key) {
        Node newNode = new Node(data, key);
        if (head == null) {
            head = newNode;
            tail = head;
            num++;
            return true;
        }

        tail.setNext(newNode);
        newNode.prev = tail;
        tail = newNode;
        num++;
        return true;
    }

    public int size() {
        return num;
    }

    public Object getKey(int index) {
        if (head == null) {
            return null;
        }

        Node temp = head;
        while (index > 0 && temp != null) {
            temp = temp.getNext();
            index--;
        }

        if (index != 0) {
            return null;
        }

        return temp.getKey();
    }

    public Object get(int index) {
        if (head == null) {
            return null;
        }

        Node temp = head;
        while (index > 0 && temp != null) {
            temp = temp.getNext();
            index--;
        }

        if (index != 0) {
            return null;
        }

        return temp.getData();
    }
}