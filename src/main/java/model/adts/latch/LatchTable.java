package model.adts.latch;

import exceptions.KeyNotFoundException;
import model.adts.dictionary.IDictionary;
import model.adts.dictionary.MyDictionary;

import java.util.HashMap;
import java.util.Map;

public class LatchTable implements ILatchTable{
    private IDictionary<Integer, Integer> latchTable;
    private int freeLocation;

    public LatchTable() {
        this.latchTable = new MyDictionary<>();
        this.freeLocation = 1;
    }

    @Override
    public synchronized int allocate(int value) {
        this.latchTable.put(this.freeLocation++, value);
        return this.freeLocation - 1;
    }

    @Override
    public synchronized int get(int address) throws KeyNotFoundException {
        return this.latchTable.get(address);
    }

    @Override
    public synchronized boolean contains(int address) {
        return this.latchTable.contains(address);
    }

    @Override
    public synchronized int getFreeLocation() {
        return this.freeLocation;
    }

    @Override
    public synchronized void set(int address, int value) {
        this.latchTable.put(address, value);
    }

    @Override
    public synchronized IDictionary getLatchTable() {
        return this.latchTable;
    }

    @Override
    public synchronized Map<Integer, Integer> getContent() throws KeyNotFoundException {
        Map<Integer, Integer> ans = new HashMap<>();
        for (int key : this.latchTable.getKeys()) {
            ans.put(key, this.latchTable.get(key));
        }
        return ans;
    }

    @Override
    public synchronized void setContent(Map<Integer, Integer> map) {
        this.latchTable.clear();
        for (int key : map.keySet()) {
            this.latchTable.put(key, map.get(key));
        }
    }

    @Override
    public synchronized String toString() {
        return this.latchTable.toString();
    }
}
