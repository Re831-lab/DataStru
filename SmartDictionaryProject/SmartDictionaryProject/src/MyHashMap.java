public class MyHashMap {
    private EntryNode[] buckets;
    private int capacity = 10;

    public MyHashMap() {
        buckets = new EntryNode[capacity];
    }

    private int getIndex(String key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public void put(String key, String value) {
        int index = getIndex(key);
        EntryNode head = buckets[index];


        EntryNode current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }


        EntryNode newNode = new EntryNode(key, value);
        newNode.next = head;
        buckets[index] = newNode;
    }

    public String get(String key) {
        int index = getIndex(key);
        EntryNode current = buckets[index];
        while (current != null) {
            if (current.key.equals(key))
                return current.value;
            current = current.next;
        }
        return null;
    }

    public boolean remove(String key) {
        int index = getIndex(key);
        EntryNode current = buckets[index];
        EntryNode prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null)
                    buckets[index] = current.next;
                else
                    prev.next = current.next;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    public boolean isEmpty() {
        for (EntryNode node : buckets) {
            if (node != null) return false;
        }
        return true;
    }

    public String showAll() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            EntryNode current = buckets[i];
            while (current != null) {
                sb.append(current.key).append(" = ").append(current.value).append("\n");
                current = current.next;
            }
        }
        return sb.length() == 0 ? " Dictionary is empty." : sb.toString();
    }
}
