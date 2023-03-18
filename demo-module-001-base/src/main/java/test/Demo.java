package test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author William
 * @date 3/10/21 1:06 AM
 * @description
 */
public class Demo {

    public static void main(String[] args) throws Exception {
        Map<String, String> map = new HashMap<>(11);
        Class<?> type = map.getClass();
        Method capacity = type.getDeclaredMethod("capacity");
        capacity.setAccessible(true);
        System.out.println(capacity.invoke(map));
        // 4


    }
}

interface IMessage {
    void message();
}

abstract class Msg implements IMessage {
//   public abstract void message();

    @Override
    public void message() {

    }
}

class NetMessage implements IMessage {
    @Override
    public void message() {

    }
}

class WebMessage extends Msg {
    @Override
    public void message() {
        super.message();
    }
}

