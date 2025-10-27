public class HashMap {
    String[] arr = new String[10];

    public int hashfun(long key) {
        return (int) (key % arr.length);
    }

    public void put(long key, String value) {
        int hashValue = hashfun(key);
        System.out.println("The hashValue: " + hashValue);
        arr[hashValue] = value;
    }

    public String get(long key) {
        int hashValue = hashfun(key);
        return arr[hashValue];
    }

}
