package $_001_demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @author William
 * @date 5/27/21 5:25 PM
 * @description
 */
public class William {

    public static void main(String[] args) {
//        new Thread(() -> {
//            System.out.println(Thread.currentThread().getName() + " says ...");
//        }).start();

//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        Future<String> future = executorService.submit(new TaskC());
//
//        Runnable runnable = () -> System.out.println(Thread.currentThread().getName() + " runnable");
//
//        executorService.submit(runnable);
//        executorService.submit(new Thread(() -> System.out.println("")));
//        executorService.submit(() -> System.out.println("william"));
//
//
//        try {
//            System.out.println(future.get());
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }


//        new TaskT().start();
//        new Thread(new TaskR()).start();


        ThreadLocal<String> nameThreadLocal = new ThreadLocal<>();
        nameThreadLocal.set("Delores");
        ThreadLocal<String> nameThreadLocal2 = new ThreadLocal<>();
        nameThreadLocal2.set("William");
        System.out.println(nameThreadLocal.get());

        ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
        System.out.println(simpleDateFormatThreadLocal.get().format(new Date()));


        nameThreadLocal.remove();





        // new Thread(new TaskC()).start();
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        FutureTask<String> futureTask = new FutureTask<>(new TaskC());
//        executorService.submit(futureTask);
//        executorService.shutdown();
//        try {
//            System.out.println(futureTask.get());
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
        
    }
}

class TaskT extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " thread run");
    }
}

class TaskR implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " runnable run ...");
    }
}

class TaskC implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " callable call");
        Thread.sleep(3000);
        return "delores";
    }
}
