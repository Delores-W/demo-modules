package $_001_demo;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author William
 * @date 2020/8/31 12:20 AM
 * @description
 */
public class Demo {

    private static int age;
    static String name;
    protected static String job;
    public static String comment;

    public static void main(String[] args) {


        List<String> list = new ArrayList<>();
        Collections.addAll(list, "Delores", "William", "Frank", "Jack", "William");

        Optional<String> nn = list.stream().filter(x -> x.startsWith("J")).findFirst();

//        name.ifPresent(Consumer<String>);
        nn.ifPresent(x -> {
            System.out.println("result is: " + x.toLowerCase());
        });

        System.out.println(nn.get());

        Optional<String> oo = nn.map(String::toUpperCase);
        System.out.println(oo.get());


        System.out.println("***************************************************");

        Stream<String> stream = list.stream();
        long count = stream.filter(name -> name.toLowerCase().contains("a")).count();
        System.out.println(count);
//        stream.close();
        System.out.println("***************************************************");

        Stream<String> stringStream = list.parallelStream();

        System.out.println("***************************************************");

        List<String> result = list.stream().distinct().collect(Collectors.toList());
        System.out.println(result);
        System.out.println("***************************************************");

        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> integers = Arrays.asList(1, 2, 13, 4, 15, 6, 17, 8, 19);

        count = strings.stream().filter(string -> string.isEmpty()).count();
        System.out.println("空字符串数量为: " + count);

        count = strings.stream().filter(string -> string.length() == 3).count();
        System.out.println("字符串长度为 3 的数量为: " + count);

        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println("筛选后的列表: " + filtered);

        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);

        List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.println("Squares List: " + squaresList);
        System.out.println("列表: " + integers);

        IntSummaryStatistics stats = integers.stream().mapToInt(x -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
        System.out.println("随机数: ");

        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);

        // 并行处理
        count = strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println("空字符串的数量为: " + count);

        List<KlassGroup> groupList = Arrays.asList(
                new KlassGroup(new Klass(1), new Klass(2), new Klass(3)),
                new KlassGroup(new Klass(4), new Klass(5), new Klass(6)),
                new KlassGroup(new Klass(7), new Klass(8), new Klass(9)),
                new KlassGroup(new Klass(10))
        );

        List<List<Klass>> lists = groupList.stream().map(it -> it.getKlassList()).collect(Collectors.toList());


        List<Klass> list1 = new ArrayList<>();
        for (KlassGroup it : groupList) {
            for (Klass klass : it.getKlassList()) {
                list1.add(klass);
            }
        }

        List<Klass> list2 = groupList.stream().flatMap(it -> it.getKlassList().stream()).collect(Collectors.toList());

        List<Klass> list3 = groupList.stream().flatMap(new Function<KlassGroup, Stream<Klass>>() {
            @Override
            public Stream<Klass> apply(KlassGroup klassGroup) {
                return klassGroup.getKlassList().stream().map(klass -> new Klass(klass.getField() * klass.getField()));
            }
        }).collect(Collectors.toList());
        System.out.println("----------------------------------------------");

        list2.forEach(System.out :: println);

        System.out.println("----------------------------------------------");
        List<String> first= Arrays.asList("one", "two", "three");
        List<String> second= Arrays.asList("A", "B");
        //不使用lambda表达式
        first.stream()
                .flatMap(new Function<String, Stream<String>>() {
                    //f是first发出的元素
                    public Stream<String> apply(String f) {
                        return second.stream().map(new Function<String, String>() {
                            //s是second发出的元素
                            public String apply(String s) {
                                return String.format("%s,%s ", f, s);
                            }
                        });
                    }
                })
                .forEach(System.out::println);

        System.out.println("----------------------------------------------");
        //使用lambda表达式
        first.stream()
                .flatMap(f -> second.stream().map(s -> String.format("%s,%s ", f, s)))
                .forEach(System.out::println);


    }
}

class Klass {
    private int field;

    public Klass(int field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "field=" + field;
    }

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }


}


class KlassGroup {
    private List<Klass> group = new ArrayList<>();

    public KlassGroup(Klass... objList) {
        for (Klass item : objList) {
            this.group.add(item);
        }
    }

    public List<Klass> getKlassList() {
        return group;
    }
}


