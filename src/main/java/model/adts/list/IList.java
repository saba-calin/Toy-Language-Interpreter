package model.adts.list;

import java.util.List;

public interface IList<T> {
    void add(T elem);
    List<T> getAll();
}
