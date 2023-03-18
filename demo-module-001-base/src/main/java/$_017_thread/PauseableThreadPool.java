package $_017_thread;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author William
 * @date 11/20/20 10:48 PM
 * @description 每个任务前后放置钩子函数
 */
public class  PauseableThreadPool extends ThreadPoolExecutor {
    private boolean isPaused;
    private Lock lock = new ReentrantLock();
    private Condition unpaused = lock.newCondition();

    public PauseableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public PauseableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public PauseableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public PauseableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        lock.lock();
        try {
            while (isPaused) {
                unpaused.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void pause() {
        lock.lock();
        try {
            isPaused = true;
            System.out.println("线程池被暂停了");
        } finally {
            lock.unlock();
        }
    }

    public void resume() {
        lock.lock();
        try {
            isPaused = false;
            System.out.println("线程池被恢复了");
            unpaused.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        PauseableThreadPool threadPool = new PauseableThreadPool(10, 20, 10L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + "线程被执行");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 10000; i ++) {
            threadPool.execute(runnable);
        }
        try {
//          1.5s之后 暂停线程池
            Thread.sleep(5000);
            threadPool.pause();
//          1.5s之后恢复线程池
//            Thread.sleep(500);
//            threadPool.resume();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        线程被执行
//        ...
//        线程被执行
//        线程池被暂停了
//        线程池被恢复了
//        线程被执行
//        线程被执行
//        线程被执行
//        线程被执行
//        ...
    }
}
