package $_007_inner;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author William
 * @date 2020/9/8 5:57 PM
 * @description
 */
public class Method {

    public static void main(String[] args) {

        Function<String, Boolean> function_1 = new Function<String, Boolean>() {
            @Override
            public Boolean apply(String s) {
                return s.startsWith("**");
            }
        };

        // 参数 s startsWith "**"
        Function<String, Boolean> function_2 = s -> s.startsWith("**");

        boolean result = function_2.apply("**Delores");
        System.out.println(result);

        // "**" startsWith 参数 s
        Function<String, Boolean> function_3 = s -> "**".startsWith(s);
        // 参数s 作为方法实现的参数 s 时，可以用方法引用代替
        Function<String, Boolean> function_4 = "**"::startsWith;


        Consumer<String> fun_1 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        Consumer<String> fun_2 = s -> System.out.println(s);

        Consumer<String> fun_3 = System.out::println;

        fun_3.accept("Delores");


        Supplier<String> supplier_1 = new Supplier<String>() {
            @Override
            public String get() {
                return "Delores";
            }
        };

        Supplier<String> supplier_2 = "Delores" :: toUpperCase;

        String str = supplier_2.get();
        System.out.println(str);


        Predicate<String> pre_1 = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.equalsIgnoreCase("delores");
            }
        };

        Predicate<String> pre_2 = "delores" :: equalsIgnoreCase;

        boolean res = pre_2.test("DELOres");

        System.out.println(res);



    }
}






