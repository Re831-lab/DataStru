public class HashMap {
    LinkedList[] arr = new LinkedList[10];

    public int hashfun(long key) {
        return (int)(key % arr.length);
    }

    public void put(long key, String value) {
        int hashValue = hashfun(key);
        if(arr[hashValue]==null) {
            arr[hashValue] = new LinkedList();
        }
        arr[hashValue].addFirst(value);
    }

    public void get(long key) {
        int hashValue = hashfun(key);
        if (arr[hashValue] != null) {
            arr[hashValue].printList();
        } else {
            System.out.print("[]");
        }
    }
}
