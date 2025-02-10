package model.adts.list;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements IList<T> {
    private List<T> list;

    public MyList() {
        this.list = new ArrayList<>();
    }

    @Override
    public void add(T elem) {
        this.list.add(elem);
    }

    @Override
    public List<T> getAll() {
        return this.list;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        for (T elem : this.list) {
            string.append(elem);
            string.append("\n");
        }
        if (string.length() > 0) {  // remove the last new line
            string.deleteCharAt(string.length() - 1);
        }

        return string.toString();
    }
}
