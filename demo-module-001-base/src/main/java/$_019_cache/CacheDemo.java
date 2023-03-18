package $_019_cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author William
 * @date 12/11/20 3:12 PM
 * @description
 */
public class CacheDemo {

    private final Map<String, Integer> cache = new HashMap<>();

    public Integer calculate(String userId) {
        // 先检查HashMap中有没有缓存，
        Integer result = cache.get(userId);
        // 如果缓存中没有，计算或去库中查询保存到缓存中
        if (result == null) {
            result = doCalculate(userId);
            cache.put(userId, result);
        }
        return result;
    }

    private Integer doCalculate(String userId) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(userId);
    }

    public static void main(String[] args) {
        CacheDemo cacheDemo = new CacheDemo();
        Integer result = cacheDemo.calculate("12345");
        System.out.println("第一次计算的结果：" + result);
        System.out.println("第二次计算的结果：" + cacheDemo.calculate("12345"));

    }

}
