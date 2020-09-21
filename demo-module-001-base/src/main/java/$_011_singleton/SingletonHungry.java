package $_011_singleton;

/**
 * @author William
 * @date 2020/9/3 2:20 PM
 * @description
 */
public class SingletonHungry {

    // 编译时就已经实例化对象 final 修饰对象不能再被修改
    // public static final SingletonHungry INSTANCE = new SingletonHungry();

    public static SingletonHungry instance = new SingletonHungry();

    // private 构造方法，禁止外部通过new 进行实例化
    private SingletonHungry(){};

    // 封装
    public static SingletonHungry getInstance() {
        return instance;
    }
}
