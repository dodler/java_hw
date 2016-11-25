package lyan.artyom;

import java.util.Objects;

/**
 * Created by lyan on 07.11.16.
 */
public class Person {
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    private int age;
    private boolean sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Person(int age, boolean sex, String name){
        this.age = age;
        this.sex = sex;
        this.name = name;
    }

    public Person(){
        this.age = 0;
        this.sex = false;
        this.name = "";
    }

    @Override
    public String toString(){
        return "[" + age + ":" + sex + ":" + name + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                sex == person.sex &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, sex, name);
    }
}
