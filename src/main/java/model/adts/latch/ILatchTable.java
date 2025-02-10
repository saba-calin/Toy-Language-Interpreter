package model.adts.latch;

import exceptions.KeyNotFoundException;
import model.adts.dictionary.IDictionary;

import java.util.Map;

public interface ILatchTable {
    int allocate(int value);
    int get(int address) throws KeyNotFoundException;
    boolean contains(int address);
    int getFreeLocation();
    void set(int address, int value);
    IDictionary getLatchTable();
    Map<Integer, Integer> getContent() throws KeyNotFoundException;
    void setContent(Map<Integer, Integer> map);
}
