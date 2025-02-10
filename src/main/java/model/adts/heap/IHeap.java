package model.adts.heap;

import exceptions.KeyNotFoundException;
import model.adts.dictionary.IDictionary;
import model.values.IValue;

import java.util.Collection;
import java.util.Map;

public interface IHeap {
    int allocate(IValue value);
    IValue get(int address) throws KeyNotFoundException;
    boolean contains(int address);
    int getFreeLocation();
    void set(int address, IValue value);
    IDictionary getHeap();
    Map<Integer, IValue> getContent() throws KeyNotFoundException;
    void setContent(Map<Integer, IValue> map);
    Collection<IValue> getValues();

}
