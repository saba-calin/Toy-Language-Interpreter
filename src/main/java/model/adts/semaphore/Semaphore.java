package model.adts.semaphore;

import exceptions.KeyNotFoundException;
import model.adts.dictionary.IDictionary;
import model.adts.dictionary.MyDictionary;
import model.adts.list.MyList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Semaphore implements ISemaphore {
    private IDictionary<Integer, Tuple<Integer, MyList<Integer>, Integer>> semaphore;
    private int freeLocation;

    public Semaphore() {
        this.semaphore = new MyDictionary<>();
        this.freeLocation = 1;
    }

    @Override
    public int allocate(Tuple<Integer, MyList<Integer>, Integer> tuple) {
        this.semaphore.put(this.freeLocation++, tuple);
        return this.freeLocation - 1;
    }

    @Override
    public Tuple<Integer, MyList<Integer>, Integer> get(int address) throws KeyNotFoundException {
        return this.semaphore.get(address);
    }

    @Override
    public boolean contains(int address) {
        return this.semaphore.contains(address);
    }

    @Override
    public int getFreeLocation() {
        return this.freeLocation;
    }

    @Override
    public void set(int address, Tuple<Integer, MyList<Integer>, Integer> tuple) {
        this.semaphore.put(address, tuple);
    }

    @Override
    public IDictionary getSemaphore() {
        return this.semaphore;
    }

    @Override
    public Map<Integer, Tuple<Integer, List<Integer>, Integer>> getContent() throws KeyNotFoundException {
        Map<Integer, Tuple<Integer, List<Integer>, Integer>> ans = new HashMap<>();
        for (int key : this.semaphore.getKeys()) {
            ans.put(key, new Tuple<>(this.semaphore.get(key).getFirst(), this.semaphore.get(key).getSecond().getAll(), this.semaphore.get(key).getThird()));
        }
        return ans;
    }

    @Override
    public void setContent(Map<Integer, Tuple<Integer, MyList<Integer>, Integer>> map) {
        this.semaphore.clear();
        for (int key : map.keySet()) {
            this.semaphore.put(key, map.get(key));
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        try {
            for (int key : this.semaphore.getKeys()) {
                string.append(key).append(" -> ").append(this.semaphore.get(key)).append("\n");
            }
        }
        catch (KeyNotFoundException e) {
            e.printStackTrace();
        }

        return string.toString();
    }
}
