package $_013_thread;

import java.io.FileReader;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author William
 * @date 2020/9/14 10:47 AM
 * @description
 */
public class Demo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MyThread_1 t1 = new MyThread_1();
        new Thread(t1).start();
        new Thread(t1).start();
        new Thread(t1).start();
        // Thread-2 sell ticket: 5
        // Thread-2 sell ticket: 2
        // Thread-1 sell ticket: 3
        // Thread-0 sell ticket: 4
        // Thread-2 sell ticket: 1


//        FutureTask<String> task = new FutureTask<>(new MyThread_2());
//        new Thread(task).start();
//        String result = task.get();
//        System.out.println(result);
    }
}

class MyThread_2 implements Callable<String> {

    private int tickets = 5;

    @Override
    public String call() {
        for (int i = 0; i < 100; i++) {
            if (this.tickets > 0)
                System.out.println(Thread.currentThread().getName() + " sell ticket: " + this.tickets--);
        }

        return "Finished";
    }
}

class MyThread_1 implements Runnable { // 线程主体类

    private volatile int tickets = 5;

    @Override
    public void run() {
        synchronized (this) {
            while (this.tickets > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " sell ticket: " + this.tickets--);
            }

        }
    }
}


