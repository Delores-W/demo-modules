package $_019_cache;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author William
 * @date 12/16/20 4:07 PM
 * @description 性能测试
 */
public class Test {

    public static void main(String[] args) {

        CacheDemo2<String, Integer> expensiveComputer = new CacheDemo2<>(new ExpensiveFunction());

        CountDownLatch countDownLatch = new CountDownLatch(1);

        ExecutorService service = Executors.newFixedThreadPool(100);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            service.submit(() -> {
                Integer result = null;
                try {
                    System.out.println(Thread.currentThread().getName() + "ready");
                    countDownLatch.await();
                    System.out.println(SafeDateFormat.dateFormatThreadLocal.get().format(new Date()));
                    System.out.println(Thread.currentThread().getName() + "go go go");
                    result = expensiveComputer.compute("777");
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                System.out.println(result);
            });
        }
        service.shutdown();
        try {
            Thread.sleep(3000);
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        while (!service.isTerminated()) {
//
//        }
//        System.out.println("总耗时：" + (System.currentTimeMillis() - startTime));
    }

    static class SafeDateFormat {
        private final static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("mm:ss:SSS"));
    }
}
