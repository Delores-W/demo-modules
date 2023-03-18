package $_019_cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Function;

/**
 * @author William
 * @date 12/15/20 1:59 AM
 * @description
 */
public class CacheDemo2<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> c;

    public CacheDemo2(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException, ExecutionException {
        while (true) {
            Future<V> future = cache.get(arg);
            if (future == null) {
                Callable<V> callable = () -> c.compute(arg);
                FutureTask<V> ft = new FutureTask<>(callable);
                // 如果不存在，则添加。
                // 不存在返回null, 存在返回value
                future = cache.putIfAbsent(arg, ft);
                if (future == null) {
                    future = ft;
                    ft.run();
                }
            }
            try {
                return future.get();
            } catch (CancellationException e) {
                System.out.println("任务被取消了");
                cache.remove(arg);
                e.printStackTrace();
                throw e;
            } catch (InterruptedException e) {
                cache.remove(arg);
                e.printStackTrace();
                throw e;
            } catch (ExecutionException e) {
                e.printStackTrace();
                // throw e;
                // 如果不抛出，就需要处理，循环直到成功
                cache.remove(arg);
                System.out.println("计算错误，开始重试");
            }
        }
    }

    private final ScheduledExecutorService service = Executors.newScheduledThreadPool(5);

    public V compute(A arg, long expire) throws ExecutionException, InterruptedException {
        service.schedule(() -> {
            expire(arg);
        }, expire, TimeUnit.MILLISECONDS);
        return compute(arg);
    }

    private synchronized void expire(A arg) {
        Future<V> future = cache.get(arg);
        if (future != null) {
            if (!future.isDone()) {
                System.out.println("任务被取消");
                future.cancel(true);
            }
            System.out.println("过期时间到，缓存被清除");
            cache.remove(arg);
        }
    }

    public V computeRandomExpire(A arg) throws ExecutionException, InterruptedException {
        long expire = (long) (Math.random() * 10000);
        return compute(arg, expire);
    }

    public static void main(String[] args) {

        CacheDemo2<String, Integer> expansiveComputer = new CacheDemo2<>(new MayFailedFunction());
        new Thread(() -> {
            try {
                Integer result = expansiveComputer.compute("123456", 5000);
                System.out.println("123456 第一次计算的结果：" + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Integer result = expansiveComputer.compute("666", 5000);
                System.out.println("666 第一次计算的结果：" + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Integer result = expansiveComputer.compute("123456", 5000);
                System.out.println("123456 第二次计算的结果：" + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            Thread.sleep(6000);
            new Thread(() -> {
                try {
                    Integer result = expansiveComputer.compute("123456", 5000);
                    System.out.println("123456 第三次计算的结果：" + result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        Future<Integer> integerFuture = expansiveComputer.cache.get("666");
//        System.out.println("尝试取消任务666并中断");
//        integerFuture.cancel(true);

    }
}
