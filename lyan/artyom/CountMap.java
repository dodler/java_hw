package lyan.artyom;

import java.util.Map;

/**
 * Created by lyan on 26.11.16.
 */
public interface CountMap<E> {
    void add(E e);
    int getCount(E e);
    int remove(E e);
    int size();
    void addAll(CountMap<E> source);
    Map<E, Integer> toMap();
    void toMap(Map<E, Integer> destination);

}
