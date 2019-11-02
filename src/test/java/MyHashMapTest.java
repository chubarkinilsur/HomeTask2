import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {


    @BeforeEach
    void setUp() {


    }

    @org.junit.jupiter.api.Test
    void put() {
        MyHashMap map = new MyHashMap();
        map.put("key1", "value1");
        map.put(1L, "value2");
        map.put(0x00000000L, "value3");
        assertEquals(3, map.getSize());
        assertEquals("value1", map.getValue("key1"));
        assertEquals("value2", map.getValue(1L));
        assertEquals("value3", map.getValue(0x00000000L));


    }

    @org.junit.jupiter.api.Test
    void remove() {
        MyHashMap map = new MyHashMap();
        map.put("key1", "value1");
        map.put(1L, "value2");
        map.put(0x00000000L, "value3");
        assertEquals(3, map.getSize());
        map.remove("key1");
        assertEquals(2, map.getSize());

    }

    @org.junit.jupiter.api.Test
    void getSize() {
        MyHashMap map = new MyHashMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        assertEquals(3, map.getSize());

    }

    @Test
    void containsKey() {
        MyHashMap map = new MyHashMap();
        map.put("key1", "value1");
        map.put(0x00000000L, "value2");
        map.put(1L, "value3");
        assertTrue(map.containsKey(0x00000000L));
        assertFalse(map.containsKey("key"));
    }

    @Test
    void exceptionsTest() {
        MyHashMap map = new MyHashMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        assertThrows(IllegalArgumentException.class, () -> map.put("key1", "newValue"));
        assertThrows(NoSuchElementException.class, () -> map.remove("key"));
        assertThrows(NoSuchElementException.class, () -> map.getValue("key"));

    }
}