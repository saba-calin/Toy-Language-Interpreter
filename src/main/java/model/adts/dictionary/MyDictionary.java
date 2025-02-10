package model.adts.dictionary;

import exceptions.KeyNotFoundException;
import model.values.StringValue;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyDictionary<K, V> implements IDictionary<K, V> {
    Map<K, V> map;

    public MyDictionary() {
        this.map = new HashMap<>();
    }

    @Override
    public void put(K key, V value) {
        this.map.put(key, value);
    }

    @Override
    public void remove(K key) throws KeyNotFoundException {
        if (this.map.containsKey(key)) {
            this.map.remove(key);
        }
        else {
            throw new KeyNotFoundException("Key not found");
        }
    }

    @Override
    public boolean contains(K key) {
        return this.map.containsKey(key);
    }

    @Override
    public V get(K key) throws KeyNotFoundException {
        if (this.map.containsKey(key)) {
            return this.map.get(key);
        }
        else {
            throw new KeyNotFoundException("Key not found");
        }
    }

    @Override
    public Set<K> getKeys() {
        return this.map.keySet();
    }

    @Override
    public Collection<V> getValues() {
        return this.map.values();
    }

    @Override
    public void clear() {
        this.map.clear();
    }

    public MyDictionary<K, V> copy() {
        MyDictionary<K, V> ans = new MyDictionary<>();
        for (K key : this.map.keySet()) {
            ans.put(key, this.map.get(key));
        }
        return ans;
    }

    @Override
    public Map<K, V> getContent() {
        return this.map;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        for (K key : this.map.keySet()) {
            string.append(key);
            string.append(" -> ");

            if (this.map.get(key) instanceof StringValue && ((StringValue) this.map.get(key)).getValue() == "") {
                string.append("\"\"");
            }
            else {
                string.append(this.map.get(key));
            }

            string.append("\n");
        }
        if (string.length() > 0) {  // remove the last new line
            string.deleteCharAt(string.length() - 1);
        }

        return string.toString();
    }
}
