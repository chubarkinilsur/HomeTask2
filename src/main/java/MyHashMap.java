import java.util.NoSuchElementException;

/**
 * MyHashMap.class домашнее задание №2
 *
 * @author Чубаркин Ильсур
 */
public class MyHashMap {
    /**
     * Размер мапы постоянный, не динамический
     */
    private static final int LENGTH = Integer.MAX_VALUE / 1000;
    private int size = 0;
    private Node[] entry = new Node[LENGTH];

    /**
     * Добавить новый элеменет в мапу ключ key, значение value. Если уже существует такой ключ key, выбрасываю исключение IllegalArgumentException
     *
     * @param key   ключ, может быть null
     * @param value значение, может быть null
     */
    public void put(Object key, Object value) {

        if (containsKey(key))
            throw new IllegalArgumentException("There is a such key " + key + " in MyHashMap. Use update method.");

        Node currentNode = entry[indexFor(key)];
        Node node = new Node(key, value, currentNode);
        entry[indexFor(key)] = node;
        size++;
    }

    /**
     * Изменить значение по ключу key на value, при отсутствии ключа key  выбрасываю исключение NoSuchElementException
     *
     * @param key   может быть null
     * @param value может быть null
     */
    public void update(Object key, Object value) {
        Node node = get(key);
        if (node != null) {
            node.value = value;
        } else throw new NoSuchElementException("There is no such key " + key);
    }


    private Node get(Object key) {
        Node node = entry[indexFor(key)];
        while (node != null) {
            if (node.key != key) {
                node = node.next;
            } else break;
        }
        return node;
    }

    /**
     * Проверка сущетвует ли в таблице запись с ключем = key.
     *
     * @param key может быть null
     * @return boolean true есть запись существует, false если такой записи нет.
     */
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    /**
     * Удалить из таблицы запись с ключем key. При отсутствии записи с ключем key
     * выбрасываю исключение NoSuchElementException
     *
     * @param key может быть null
     */
    public void remove(Object key) {

        if (get(key) == null) throw new NoSuchElementException("There is no such key " + key);

        int hash = indexFor(key);
        Node node = entry[hash];
        Node prevEntry = null;
        while (node != null) {
            if (node.key.equals(key)) {
                if (prevEntry != null) {
                    prevEntry.next = node.next;
                } else if (node.next == null) {
                    entry[hash] = null;
                } else {
                    entry[hash] = node.next;
                }
                size--;
                return;
            }
            prevEntry = node;
            node = node.next;
        }
    }

    /**
     * Палучить количество записей в таблице.
     *
     * @return int больше или равен 0.
     */
    public int getSize() {
        return size;
    }

    private int indexFor(Object key) {
        return key == null ? 0 : Math.abs(key.hashCode()) % LENGTH;
    }

    /**
     * Получить значение value типа Object по ключу key. При отсутствии в таблице записи с ключем key,
     * выбрасываю исключение NoSuchElementException
     *
     * @param key параметр ключ, может быть null
     * @return Object может быть null
     */
    public Object getValue(Object key) {
        Node node = get(key);
        if (node == null) throw new NoSuchElementException("There is no such key " + key);
        return node.value;
    }

    /*
     * Внутренний класс Node для хранения пар ключ=значение.
     */
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

