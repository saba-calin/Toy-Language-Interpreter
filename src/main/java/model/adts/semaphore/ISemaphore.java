package model.adts.semaphore;

import exceptions.KeyNotFoundException;
import model.adts.dictionary.IDictionary;
import model.adts.list.MyList;

import java.util.List;
import java.util.Map;

public interface ISemaphore {
    int allocate(Tuple<Integer, MyList<Integer>, Integer> tuple);
    Tuple<Integer, MyList<Integer>, Integer> get(int address) throws KeyNotFoundException;
    boolean contains(int address);
    int getFreeLocation();
    void set(int address, Tuple<Integer, MyList<Integer>, Integer> tuple);
    IDictionary getSemaphore();
    Map<Integer, Tuple<Integer, List<Integer>, Integer>> getContent() throws KeyNotFoundException;
    void setContent(Map<Integer, Tuple<Integer, MyList<Integer>, Integer>> map);
}
