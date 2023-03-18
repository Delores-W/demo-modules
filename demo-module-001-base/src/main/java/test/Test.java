package test;

import java.nio.ByteBuffer;
import java.util.*;

/**
 * @author William
 * @date 3/15/21 8:28 PM
 * @description IO                       NIO
 * byte[] / char[]          Buffer
 * Stream                   Channel
 * 直接内存的占用与释放
 */
public class Test {

    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        Date birth = new Date();
        list.add("Delores");

        list.removeAll(Arrays.asList(1, 2, 3));
//
//        new Scanner(System.in).next();
//
//        System.gc();
//        System.out.println("垃圾回收完毕");
//        new Scanner(System.in).next();

//        String value = null;
        String value = "Delores";
        System.out.println(value != null ? value : test());
    }

    public static String test() {
        System.out.println("test method");
        return "test";
    }


    /**
     * 要求：
     * 不使用现成集合方法
     * 空间限制，额外空间超过 m + n
     */
    public static class ListUnion {
        static List<Integer> union(List<Integer> a, List<Integer> b) {

            List<Integer> result = new ArrayList<>();

        // 方法一
            // 超过额外空间 passset
             Set<Integer> set = new HashSet<>(a);
            // set.addAll(b);
            for (Integer i : b) {
                 set.add(i);
             }
             result = new ArrayList<>(set);
            // 时间复杂度 O(n)

        // 方法二
            // Arrays.asList() 返回的是java.util.Arrays类中的一个内部类
            //
            // 对列表进行add,remove 操作时直接抛出异常
            //      public void add(int index, E element) {
            //          throw new UnsupportedOperationException();
            //      }
            //
            // 所以先转为ArrayList进行操作，此处对新空间的增加先不考虑, 时间原因没有对直接操作作为内部类的ArrayList探究

            List<Integer> aa = new ArrayList<>(a);
            List<Integer> bb = new ArrayList<>(b);

            // 合并
            // aa.addAll(bb);
            for (Integer i : bb) {
                aa.add(i);
            }

            // 去重
            for (Integer i : aa) {
                if (!result.contains(i))
                    result.add(i);
            }

            // 时间复杂度 O(n)

            return result;
        }

        public static void main(String[] args) {
            // union([2, 1, 3], [2, 3, 4]) = [1,2,3,4]
            System.out.println(union(Arrays.asList(2, 2, 1, 3), Arrays.asList(2, 3, 4)));
            // [2, 1, 3, 4]
        }
    }


}


















