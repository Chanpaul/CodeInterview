package Interview;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
/**
 * Created by yongyangyu on 12/8/14.
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and set.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache,
 * otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present.
 * When the cache reached its capacity, it should invalidate the least recently used item before
 * inserting a new item.
 */
public class LRUCache {
    myCache<Integer, Integer> cache;
    public LRUCache(int capacity) {
        cache = new myCache<Integer, Integer>(capacity);
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        return cache.get(key);
    }

    public void set(int key, int value) {
        cache.put(key, value);
    }

    private class myCache<K, V> extends LinkedHashMap<K, V> {
        private int capacity;

        public myCache(int capacity) {
            // (initialCapacity, loadFactor, accessOrder)
            // true for access-order, false for insertion-order
            super(capacity+1, 0.75f, true);
            this.capacity = capacity;
        }

        protected boolean removeEldestEntry(Entry entry) {
            return (size() > this.capacity);
        }
    }
}
