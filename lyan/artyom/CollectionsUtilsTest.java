package lyan.artyom;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import static lyan.artyom.CollectionUtils.range;
import static org.junit.Assert.assertEquals;

/**
 * Created by lyan on 26.11.16.
 */
public class CollectionsUtilsTest {

    @Test
    public void test_rangeComparable_shouldReturnDefinedValue(){
        List<Integer> result = CollectionUtils.<Integer>range(
                Arrays.asList(8,1,3,5,6,4), 3, 6);
       assertEquals(new HashSet(Arrays.asList(3,4,5,6)),
               new HashSet(result));
    }

    @Test
    public void test_rangeComparator_shouldReturnDefinedValue(){
        List<Integer> result = CollectionUtils.<Integer>range(
                Arrays.asList(8, 1, 3, 5, 6, 4), 3, 6,
                (e1,e2)->{
                    int value = (int)e1,
                            value2 = (int)e2;
                    if (value > value2) return 1;
                    if (value < value2)  return -1;
                    return 0;
                });
        assertEquals(new HashSet(Arrays.asList(3,4,5,6)),
                new HashSet(result));
    }
}
