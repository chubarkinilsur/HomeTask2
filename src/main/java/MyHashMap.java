import java.util.NoSuchElementException;


public class MyHashMap {

    public static int LENGTH = Integer.MAX_VALUE/100;
    private int size = 0;
    private Node[] entry = new Node[LENGTH];


    public void put(Object key, Object value) {
        if (containsKey(key) != null) throw new IllegalArgumentException();

        Node currentNode = entry[indexFor(key)];
        entry[indexFor(key)] = new Node(key, value, currentNode);
        size++;
    }

    public void update(Object key, Object value) {
        Node node = containsKey(key);
        if (node != null)
            node.value = value;
        else throw new NoSuchElementException();
    }

    public Object getValue(Object key) {
        if (containsKey(key) == null) throw new NoSuchElementException();
        return entry[indexFor(key)].value;
    }


    public Node containsKey(Object key) {
        int hash = indexFor(key);
        Node node = entry[hash];
        if (node == null)
            return null;
        while (node != null) {
            if (node.key.equals(key))
                return node;
            node = node.next;
        }
        return null;
    }

    public void remove(Object key) {
        Node node = containsKey(key);
        if (node == null) throw new NoSuchElementException();
        node = null;
        size--;
    }


    public int getSize() {
        return size;
    }

    private int indexFor(Object key) {
        return key == null ? 0 : Math.abs(key.hashCode()) % LENGTH;

    }

    public static void main(String[] args) {
        int _;
        MyHashMap map = new MyHashMap();
        MyHashMap.LENGTH=100;
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        System.out.println(map.getSize());
        System.out.println(map.containsKey("key1"));
        System.out.println(map.containsKey("key5"));
        System.out.println(map.getValue("key1"));
        System.out.println(map.getValue("key2"));
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

    class Node {

        private Object key;
        private Object value;
        private Node next;

        Node(Object key, Object value, Node node) {
            this.key = key;
            this.value = value;
            this.next = node;
        }
    }
}

