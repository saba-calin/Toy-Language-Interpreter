package model.adts.dictionary;

import exceptions.KeyNotFoundException;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface IDictionary<K, V> {
    void put(K key, V value);
    void remove(K key) throws KeyNotFoundException;
    boolean contains(K key);
    V get(K key) throws KeyNotFoundException;
    Set<K> getKeys();
    Collection<V> getValues();
    Map<K, V> getContent();
    void clear();
}
