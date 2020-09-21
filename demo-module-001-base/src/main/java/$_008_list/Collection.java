package $_008_list;

import java.io.*;
import java.util.*;

/**
 * @author William
 * @date 2020/9/9 3:04 PM
 * @description
 */
public class Collection {

    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList<>(15);

        list.add("delores");
        list.add("william");
        list.add("Frank");
        String str = list.get(0);

        list.forEach(System.out :: println);
        // delores
        // william
        // Frank


        List<Person> personList = new ArrayList<>();
        personList.add(new Person("delores", 20));
        personList.add(new Person("william", 20));
        personList.add(new Person("frank", 20));

        personList.forEach(System.out :: println);
        // Name: delores, Age: 20
        // Name: william, Age: 20
        // Name: frank, Age: 20
        personList.remove(new Person("frank", 20));
        personList.forEach(System.out :: println);
        // 结果并没有删除对象 原因是自定义对象没有复写 equals() 方法



        List<String> list1 = new LinkedList<>();
        list1.add("Delores");
        list1.get(0);

        LinkedList<String> linkedList = (LinkedList) list1;
        linkedList.addFirst("Delores");
        linkedList.addLast("Delores");
        linkedList.add(0,"Delores");

        List<String> list2 = new Vector<>();
        list.add("Delores");

        System.out.println("*********************************************");

        Set<String> set = new HashSet<>();
        set.add("Delores");
        set.add("William");
        set.add("Frank");

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            System.out.println(name);

//            if (name.equals("Delores"))
//                set.remove(name);

            if (name.equals("Delores"))
                iterator.remove();
        }

        for (String name: set) {
            System.out.println(name);
        }


        Map<String,Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put(null, 3);
        map.put("3", null);

        System.out.println(map.get(null));

        System.out.println(map.keySet());
        // [null, 3, one]
        System.out.println(map.values());
        // [3, null, 1]
        Set<Map.Entry<String, Integer>> sett = map.entrySet();

        System.out.println("----------------------------------------------------------------------");

        Stack<String> stack = new Stack<>();
        stack.push("Delores");
        stack.push("William");
        stack.push("Jack");

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
//        System.out.println(stack.pop());

        Queue<String> queue = new LinkedList<>();
        queue.offer("A");
        queue.offer("B");
        queue.offer("C");
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());



        Properties properties = new Properties();
        properties.setProperty("name", "delores");
        properties.setProperty("Age", "20");
        properties.store(new FileOutputStream(new File("/Users/delores/Hubs/Art/A_Projects/demo-modules/demo-module-001-base/src/main/resources/demo.properties"))
                , "中文的注释--English Comment");

        Properties prop = new Properties();
        prop.load(new FileInputStream(new File("/Users/delores/Hubs/Art/A_Projects/demo-modules/demo-module-001-base/src/main/resources/demo.properties")));
        String name = prop.getProperty("name");
        System.out.println(name);

        List<String> ll = new ArrayList<>();
        Collections.addAll(ll, "Zoo", "Jack", "William");

        // 二分查找前 先要排序
        Collections.sort(ll);
        System.out.println(ll);

        System.out.println(Collections.binarySearch(ll, "William"));





//        Collections


    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Age: %d", this.name, this.age);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
