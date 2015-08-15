package Interview;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
/**
 * Created by yongyangyu on 12/8/14.
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
            super(capacity+1, 1.0f, true);
            this.capacity = capacity;
        }

        protected boolean removeEldestEntry(Entry entry) {
            return (size() > this.capacity);
        }
    }
}
