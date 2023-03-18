import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author William
 * @date 2020/11/13 5:20 PM
 * @description 避免参数传递麻烦
 */
public class Delores {

    private int age;
    String name;
    protected String job;
    public String comment;

    public static void main(String[] args) {
        new Thread(() -> {
            new Service1().process();
        }).start();
    }
}

class Service1 {

    public void process() {
        User user = new User("Delores");
        UserContextHolder.holder.set(user);
        new Service2().process();
        ThreadSafeFormat.dateFormatThreadLocal.get();
    }
}

class Service2 {

    public void process() {
        System.out.println("Service2: " + UserContextHolder.holder.get().getName());
        new Service3().process();
    }
}

class Service3 {

    public void process() {
        System.out.println("Service3: " + UserContextHolder.holder.get().getName());
    }
}

class UserContextHolder {
    public static ThreadLocal<User> holder = new ThreadLocal<>();
}

class ThreadSafeFormat {
    public static ThreadLocal dateFormatThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
}

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}



