package lyan.artyom;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lyan on 26.11.16.
 */
public class CountMapImpl<E> implements CountMap<E> {

    private final Map<E, Integer> storage;

    public CountMapImpl(Map<E, Integer> storage) {
        this.storage = storage;
    }

    public CountMapImpl() {
        this.storage = new HashMap<>();
    }

    @Override
    public void add(E e) {
        Integer value = 1;
        if (storage.containsKey(e)) {
            value = storage.get(e);
            storage.put(e, value+1);
        } else {
            storage.put(e, value);
        }
    }

    @Override
    public int getCount(E e) {
        Integer value = 0;
        if (storage.containsKey(e)) {
            return storage.get(e);
        }
        return value;
    }

    @Override
    public int remove(E e) {
        Integer value = 0;
        if (storage.containsKey(e)) {
            value = storage.get(e);
        }
        storage.remove(e);
        return value;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void addAll(CountMap<E> source) {
        assert source != null;

        storage.putAll(source.toMap());
    }

    @Override
    public Map<E, Integer> toMap() {
        return storage;
    }

    @Override
    public void toMap(Map<E, Integer> destination) {
        assert destination != null;

        destination.putAll(storage);
    }
}
