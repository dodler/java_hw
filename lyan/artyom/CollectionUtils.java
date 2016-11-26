package lyan.artyom;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionUtils<T> {
    public static <T> void addAll(List<? extends T> source,
                                  List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<? extends T> newArrayList() {
        return new ArrayList<T>();
    }

    public static <T> int indexOf(List<? super T> source,
                                  T o) {
        return source.indexOf(o);
    }

    public static <T> List<? super T> limit(List<? super T> source,
                                            int size) {
        return source.stream().limit(size).
                collect(Collectors.toList());
    }

    public static <T> void add(List<? super T> source, T o) {
        source.add(o);
    }


    public static <T> void removeAll(List<? extends T> removeFrom,
                                     List<? super T> c2) {
        removeFrom.removeAll(c2);
    }


    public static <T> boolean containsAll(List<? extends T> c1,
                                          List<? extends T> c2) {
        return c1.containsAll(c2);
    }


    public static <T> boolean containsAny(List<? extends T> c1,
                                          List<? extends T> c2) {

        for (Object c : c2) {
            if (!c1.contains(c)) {
                return false;
            }
        }

        return true;
    }

    public static <T extends Comparable<T>> List<? super T> range(
            List<T> list,
            T min,
            T max) {
        return list.stream().
                filter(e -> e.compareTo(min) < 0)
                .filter(e -> e.compareTo(max) > 0)
                .collect(Collectors.toList());
    }

    public static <T extends Comparable<T>> List<? super T> range(
            List<T> list,
            T min,
            T max,
            Comparator comparator) {
        return list.stream().
                filter(e -> comparator.compare(e, min) < 0)
                .filter(e -> comparator.compare(e, max) > 0)
                .collect(Collectors.toList());
    }

}