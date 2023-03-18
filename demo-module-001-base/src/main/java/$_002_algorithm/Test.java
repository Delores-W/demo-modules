package $_002_algorithm;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * @author William
 * @date 2020/9/22 12:30 AM
 * @description
 */
public class Test {

    public static void main(String[] args) {
        Person[] persons = new Person[]{new Person(), new Person()};
        Arrays.sort(persons);

        List<Person> list = new ArrayList<>();
        Collections.sort(list, (o1, o2) -> 0);

        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            integerList.add(new Random().nextInt(100));
        }
        System.out.println(integerList);

        Collections.sort(integerList, Comparator.reverseOrder());

        System.out.println(integerList);

    }

    static class Person implements Comparable {

        @Override
        public int compareTo(@NotNull Object o) {
            return 0;
        }
    }

}
