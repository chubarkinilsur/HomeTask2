import java.util.HashMap;
import java.util.NoSuchElementException;


public class MyHashMap {

    public int LENGTH = Integer.MAX_VALUE/1000;
    private int size = 0;
    private Node[] entry = new Node[LENGTH];


    public void put(Object key, Object value) {

        if (containsKey(key)) throw new IllegalArgumentException();

        Node currentNode = entry[indexFor(key)];
        Node node = new Node(key, value, currentNode);
        entry[indexFor(key)] = node;
        size++;
    }

    public void update(Object key, Object value) {
        Node node = get(key);
        if (node != null)
            node.value = value;
        else throw new NoSuchElementException();
    }

    public Node get(Object key) {
        Node node = entry[indexFor(key)];
        while (node != null) {
            if (node.key != key)
                node = node.next;
            else break;
        }
        return node;
    }


    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    public void remove(Object key) {

        if (get(key) == null) throw new NoSuchElementException();

        int hash = indexFor(key);
        Node node = entry[hash];
        if (node == null) return;
        Node prev_entry = null;
        while (node != null) {
            if (node.key.equals(key)) {
                Object value = entry[hash].value;
                if (prev_entry != null)
                    prev_entry.next = node.next;
                else
                    entry[hash] = null;
                size--;
                return;
            }
            prev_entry = node;
            node = node.next;
        }
    }


    public int getSize() {
        return size;
    }

    private int indexFor(Object key) {
        return key == null ? 0 : Math.abs(key.hashCode()) % LENGTH;
    }

    public Object getValue(Object key) {
        Node node = get(key);
        if (node == null) throw new NoSuchElementException();

        return node.value;

    }

    static class Node {

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

