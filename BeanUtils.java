import lyan.artyom.Person;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BeanUtils {

    private int number;

    public void setNumber(int number){
        this.number = number;
    }

    public int getNumber(){
        return this.number;
    }

    public BeanUtils(){
        this.number = 0;
    }

    public BeanUtils(int number){
        this.number = number;
    }



    public static void main(String[] args) {
        BeanUtils bu1 = new BeanUtils(1), bu2 = new BeanUtils();

        assign(bu2, bu1);

        System.out.println(bu2.getNumber());



        List<Person> test = new ArrayList<>();
        for(int i = 0; i<10; i++){
            test.add(new Person(i, i==System.currentTimeMillis()%10, String.valueOf(i*i)));
            test.add(new Person(i, i==System.currentTimeMillis()%10, String.valueOf(i*i)));
        }

        List<Integer> result = test.stream().map(Person::getAge).collect(Collectors.toList());
        System.out.println(result);

        System.out.println(test.stream().map(Person::getName).collect(Collectors.toList()));

        System.out.println(
                test.stream().filter(p -> p.getAge() < 5).collect(Collectors.toList())
        );

        System.out.println(
                test.stream().filter(p -> p.getAge() < 5).collect(Collectors.toSet())
        );

        System.out.println(
                test.stream().distinct().collect(Collectors.toList())
        );

        test.stream().distinct().peek(p -> System.out.println(p)).collect(Collectors.toList());

        System.out.println(
                test.stream().reduce((p1,p2) -> new Person(p1.getAge() + p2.getAge(), p1.isSex() | p2.isSex(), p1.getName()))
        );


    }
    /**
     *      * Scans object "from" for all getters. If object "to"
     *      * contains correspondent setter, it will invoke it
     *      * to set property value for "to" which equals to the property
     *      * of "from".
     *      * <p/>
     *      * The type in setter should be compatible to the value returned
     *      * by getter (if not, no invocation performed).
     *      * Compatible means that parameter type in setter should
     *      * be the same or be superclass of the return type of the getter.
     *      * <p/>
     *      * The method takes care only about public methods.
     *      *
     *      * @param to   Object which properties will be set.
     *      * @param from Object which properties will be used to get values.
     *      
     */
    public static void assign(Object to, Object from) {
        List<Method> fromGetters =
                Arrays.<Method>stream(from.getClass().getMethods()).
                        filter(m -> m.getName().contains("get")).collect(Collectors.toList());

        List<Method> toSetters =
                Arrays.<Method> stream(to.getClass().getMethods()).
                        filter(m -> m.getName().contains("set")).collect(Collectors.toList());

        for (Method f:fromGetters){
            Method[] appropriateMethods = findAppropriateMethods(f, toSetters);

            if (appropriateMethods.length > 0){
                try {
                    appropriateMethods[0].invoke(to, f.invoke(from));
                } catch (IllegalAccessException|InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    /**
     * applies {@link BeanUtils#checkMethod(Method, Method)} to every method
     * in {@code toMethods}.
     * @param fromMethod
     * @param toMethods
     * @return zero lenght array in case if appropriate method was not found
     */
    private static Method[] findAppropriateMethods(Method fromMethod, List<Method> toMethods){
        Method[] result = new Method[0];
        for (Method m:toMethods){
            if (checkMethod(fromMethod, m)){
                return new Method[]{m};
            }
        }
        return result;
    }

    private static boolean checkMethod(Method from, Method to){
        String fromMethodName = from.getName().substring(2, from.getName().length());
        String toMethodName = to.getName().substring(2, to.getName().length());

        if (!fromMethodName.equals(toMethodName)){
            return false;
        }

        List<Parameter> result = Arrays.<Parameter>stream(from.getParameters()).
                filter(p -> Arrays.asList(to.getParameters()).contains(p)).
                collect(Collectors.toList());

        return result.size() == from.getParameters().length;

    }
}