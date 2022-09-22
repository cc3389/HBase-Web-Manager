package edu.wit.hbasemanager.model;

import org.apache.hadoop.hbase.client.Connection;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache extends LinkedHashMap<String, Connection> {
    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public Connection get(String key) {
        return super.get(key);
    }

    public Connection put(String key, Connection value) {
        super.put(key, value);
        return value;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<String, Connection> eldest) {
        return size() > capacity;
    }
}
