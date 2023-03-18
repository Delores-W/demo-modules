package test;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author William
 * @date 4/3/21 6:17 PM
 * @description
 */
public class Delores {

    public void test() {
        System.out.println("反射测试");
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
//        Class<Delores> deloresClass = Delores.class;
//        // 通过反射获取类类型，然后通过Class对象，获得所有的方法
//        Method[] methods = deloresClass.getDeclaredMethods();
//        for (Method method: methods) {
//            System.out.println(method.getName());
//            // test
//
//            if ("test".equals(method.getName())) {
//                // 方法的调用需要类的实例对象
//                method.invoke(new Delores(), null);
//                // 反射测试
//            }
//        }

        int[][] nums = new int[][]{{1, 3}, {1, 2}, {4, 5}, {3, 7}, {4, 2}};
        //方法一
        Arrays.sort(nums, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return a[1] - b[1];
                } else {
                    return a[0] - b[0];
                }
            }
        });


        // 方法二，使用匿名表达式
        // (a,b)->a[1]-b[1]会自动转变成上面的形式
        Arrays.sort(nums, (a, b) -> a[0] - b[0]);

        for (int[] num : nums) {
            System.out.println(Arrays.toString(num));
        }
//        [1, 2]
//        [1, 3]
//        [3, 7]
//        [4, 2]
//        [4, 5]

        // 方法三 使用Comparator 内置方法返回所需要的Comparator
        Arrays.sort(nums, Comparator.comparingInt(num -> num[0]));






        Arrays.sort(nums, (a, b) -> b[0] - a[0]);

        for (int[] num : nums) {
            System.out.println(Arrays.toString(num));
        }


        int[] a = {2, 5, 4, 3, 1, 8};
        Arrays.sort(a, 2, 5);
        System.out.println(Arrays.toString(a));


        Cat cat1 = new Cat(5);
        Cat cat2 = new Cat(7);

        System.out.println(cat1.compareTo(cat2));


        System.out.println(new CatComparator().compare(cat1, cat2));
    }

}

class Cat implements Comparable<Cat> {

    public int age;

    public Cat(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(@NotNull Cat o) {
//        if (this.age < o.age) {
//            return -1;
//        } else if (this.age == o.age) {
//            return 0;
//        } else {
//            return 1;
//        }
        return Integer.compare(this.age, o.age);

//        Integer
//        public static int compare(int x, int y) {
//            return (x < y) ? -1 : ((x == y) ? 0 : 1);
//        }
    }
}

class CatComparator implements Comparator<Cat> {

    @Override
    public int compare(Cat o1, Cat o2) {
        return Integer.compare(o1.age, o2.age);
    }
}





