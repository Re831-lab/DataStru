public class Main {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put(123456L, "sara");
        map.put(987656L, "shahd");
        map.put(654321L, "eman");

        map.get(123456L);
        System.out.println();
        map.get(987656L);
        System.out.println();
        map.get(654321L);
    }
}