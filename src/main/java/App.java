public class App {
    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();
       // MyHashMap.LENGTH=10;
        map.put("key1", "value1");
        map.put(1L, "value1");
        map.put(0x00000000L, "value2");
        map.put("key3", "value3");
        System.out.println(map.getSize());
        System.out.println(map.containsKey("key1"));
        System.out.println(map.containsKey("key5"));
        System.out.println(map.getValue("key1"));
       // System.out.println(map.getValue("key2"));
        System.out.println(map.getValue("key3"));
        map.update("key2", "newValue");
        System.out.println();
        System.out.println(map.getValue("key1"));
        System.out.println(map.getValue("key2"));
        System.out.println(map.getValue("key3"));
        System.out.println(map.getSize());
        map.remove("key3");
        System.out.println(map.getSize());
    }
}
