public class HashMap {
    LinkedList[] arr = new LinkedList[10];

    public int hashfun(long key) {
        return (int)(key % arr.length);
    }

    public void put(long key, String value) {
        int index = hashfun(key);
        if(arr[index] == null) {
            arr[index] = new LinkedList();
        }
        arr[index].addLast(value, key);
    }

    public Object get(long key) {
        int index = hashfun(key);
        if(arr[index] != null) {
            for(int i = 0; i < arr[index].size(); i++) {
                if(arr[index].getKey(i).equals(key)) {
                    return arr[index].get(i);
                }
            }
        }
        return "not found";
    }
}