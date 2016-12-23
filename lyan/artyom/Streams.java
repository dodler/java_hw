package artyom;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by lyan on 20.12.16.
 */
public class Streams<E> {
    private class FunctionOrPredicate<I,O,E>{
        Predicate<E> predicate;
        Function<I,O> function;
        boolean isPredicate = false;
    }

    FunctionOrPredicate<E,E,E> predicate(Predicate<E> predicate){
        FunctionOrPredicate<E,E,E> result = new FunctionOrPredicate<>();
        result.isPredicate = true;
        result.predicate = predicate;
        return result;
    }

    <I,O,E> FunctionOrPredicate<I,O,E> function(Function<I,O> function){
        FunctionOrPredicate<I,O,E> result = new FunctionOrPredicate<>();
        result.isPredicate = false;
        result.function = function;
        return result;
    }
    private List<E> elements;

    private Streams(List elements) {
        this.elements = elements;
    }

    public static <E> Streams of (List<E> list){
        return new Streams(list);
    }

    public Streams<E> filter(Predicate<E> predicate){
        List<E> result = new ArrayList<E>();
        for(E e:elements){
            if (predicate.test(e)){
                result.add(e);
            }
        }
        return new Streams<E>(result);
    }

    public <O> Streams<O> transform(Function<? super E,? extends O> transformer){
        List<O> result = new ArrayList<>();
        for (E element : elements) {
            result.add(transformer.apply(element));
        }

        return new Streams(result);
    }
    public<K,V> Map<K,V> toMap(Function<? super E,? extends K> key,
                                 Function<? super E,? extends V> value){
        Map<K,V> result = new HashMap<>();
        for(E element:elements){
            K k = key.apply(element);
            V v = value.apply(element);
            result.put(k,v);
        }

        return result;
    }
}
