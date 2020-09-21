package $_007_inner;

/**
 * @author William
 * @date 2020/9/8 11:07 AM
 * @description
 */
public class TestInner {

    public static void main(String[] args) {
        Outer.Inner inner = new Outer.Inner();
        inner.test();
    }
}

class Outer {

    private static String name = "Delores";

    static class Inner {
        public void test() {
            // 静态内部类只能访问静态属性
            System.out.println(name);
        }
    }

}
