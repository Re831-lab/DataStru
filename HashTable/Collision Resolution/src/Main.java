import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        // Example 1: Custom HashMap Usage (from previous images)
        System.out.println("--- Custom HashMap Example ---");
        HashMap customMap = new HashMap();

        customMap.put(123456L, "sara");
        customMap.put(987656L, "shahd");
        customMap.put(654321L, "eman");

        System.out.println(customMap.get(123456L));
        System.out.println(customMap.get(987656L));
        System.out.println(customMap.get(654321L));
        System.out.println(customMap.get(2L)); // Key not found

        // Example 2: Built-in java.util.HashMap
        System.out.println("\n--- Built-in HashMap Examples ---");
        HashMap<Long, String> javaMap = new HashMap<>();

        // Basic Usage
        System.out.println("--- Basic Usage ---");
        javaMap.put(123456L, "shahd");
        javaMap.put(4321L, "sara");
        javaMap.put(987654L, "eman");

        System.out.println(javaMap.get(123456L));
        System.out.println(javaMap.get(4321L));
        System.out.println(javaMap.get(987654L));

        // Overwriting a Key
        System.out.println("--- Overwriting a Key ---");

        // The previous entries are still in javaMap. Let's add a new one with an existing key.
        javaMap.put(123456L, "sara"); // This overwrites "shahd"
        javaMap.put(987654L, "eman"); // This has no effect as the key-value pair already exists

        System.out.println(javaMap.get(123456L)); // Now prints "sara"
        System.out.println(javaMap.get(123456L)); // Still prints "sara"
        System.out.println(javaMap.get(987654L)); // Prints "eman"
    }
}