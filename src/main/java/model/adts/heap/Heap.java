package model.adts.heap;

import exceptions.KeyNotFoundException;
import model.adts.dictionary.IDictionary;
import model.adts.dictionary.MyDictionary;
import model.values.IValue;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Heap implements IHeap {
    private IDictionary<Integer, IValue> heap;
    private int freeLocation;

    public Heap() {
        this.heap = new MyDictionary<>();
        this.freeLocation = 1;
    }

    @Override
    public int allocate(IValue value) {
        this.heap.put(this.freeLocation++, value);
        return this.freeLocation - 1;
    }

    @Override
    public IValue get(int address) throws KeyNotFoundException {
        return this.heap.get(address);
    }

    @Override
    public boolean contains(int address) {
        return this.heap.contains(address);
    }

    @Override
    public int getFreeLocation() {
        return this.freeLocation;
    }

    @Override
    public void set(int address, IValue value) {
        this.heap.put(address, value);
    }

    @Override
    public IDictionary getHeap() {
        return this.heap;
    }

    @Override
    public Map<Integer, IValue> getContent() throws KeyNotFoundException {
        Map<Integer, IValue> ans = new HashMap<>();
        for (int key : this.heap.getKeys()) {
            ans.put(key, this.heap.get(key));
        }
        return ans;
    }

    @Override
    public void setContent(Map<Integer, IValue> map) {
        this.heap.clear();
        for (int k : map.keySet()) {
            this.heap.put(k, map.get(k));
        }
    }

    @Override
    public Collection<IValue> getValues() {
        return this.heap.getValues();
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        try {
            for (Integer key : this.heap.getKeys()) {
                string.append(key.toString() + " -> " + this.heap.get(key).toString() + "\n");
            }
        }
        catch (KeyNotFoundException e) {
            string.append("");
        }
        if (string.length() > 0) {
            string.deleteCharAt(string.length() - 1);
        }

        return string.toString();
    }
}
