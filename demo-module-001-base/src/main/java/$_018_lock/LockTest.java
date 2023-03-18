package $_018_lock;

import $_001_demo.Demo;
import $_017_thread.PauseableThreadPool;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author William
 * @date 11/24/20 12:54 AM
 * @description
 */
public class LockTest {
    private static final ExecutorService service = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        new LockTest().printAd();
    }
    static class FetchAdTask implements Callable<Ad> {
        @Override
        public Ad call() throws Exception {
            try {
                Thread.sleep(3000);
                throw new IllegalArgumentException();
//                return new Ad("Dove!!!");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("sleep期间被中断");
                return new Ad("被中断的默认广告");
            }
        }
    }

    static class Ad {
        private String name;

        public Ad(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Ad{ " + name + " }";
        }
    }

    public void printAd() {
        Future<Ad> future = service.submit(new FetchAdTask());
        Ad ad = null;
        try {
            // get 限时，为了用户体验
            ad = future.get(4000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            ad = new Ad("haha");
        } catch (ExecutionException e) {
            e.printStackTrace();
            System.out.println("不管任务中抛出什么异常，都会只抛出ExecutionException...");
            System.out.println("Test Exception...");
        } catch (TimeoutException e) {
            e.printStackTrace();
            // 超时失败，取消任务
            future.cancel(true);
            ad =  new Ad("超时返回的默认广告");
        } catch (IllegalArgumentException e) {
            System.out.println("即使任务抛出IllegalArgumentException, 也不会被抛出");
            System.out.println("Test Argument...");
        }
        service.shutdown();
        System.out.println(ad);
    }
}








