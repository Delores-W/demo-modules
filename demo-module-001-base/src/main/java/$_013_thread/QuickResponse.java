package $_013_thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author William
 * @date 2020/9/15 1:18 AM
 * @description
 */
public class QuickResponse {

    public static void main(String[] args) {

        MyThread thread = new MyThread();
        FutureTask<String> task1 = new FutureTask<>(thread);
        FutureTask<String> task2 = new FutureTask<>(thread);
        FutureTask<String> task3 = new FutureTask<>(thread);
        new Thread(task2, "player 002").start();
        new Thread(task1, "player 001").start();
        new Thread(task3, "player 003").start();
        try {
            System.out.println(task1.get());
            System.out.println(task2.get());
            System.out.println(task3.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

class MyThread implements Callable<String> {
    private boolean flag = false;
    @Override
    public String call() throws Exception {
        synchronized (this) {
            if (!this.flag) {
                this.flag = true;
                return Thread.currentThread().getName() + " 抢答成功";
            } else {
                return Thread.currentThread().getName() + " 抢答失败";
            }
        }
    }
}
