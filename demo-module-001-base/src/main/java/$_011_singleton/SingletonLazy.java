package $_011_singleton;

/**
 * @author William
 * @date 2020/9/3 2:50 PM
 * @description
 */
public class SingletonLazy {

    private static SingletonLazy instance;

    private SingletonLazy(){};

    public static synchronized SingletonLazy getInstance() {
        if (instance == null) {
            try {
                // 模拟类创建的准备工作
                Thread.sleep(3000);
                instance = new SingletonLazy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
