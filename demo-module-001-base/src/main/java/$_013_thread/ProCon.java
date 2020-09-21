package $_013_thread;

/**
 * @author William
 * @date 2020/9/14 4:46 PM
 * @description
 */
public class ProCon {

    public static void main(String[] args) {
        Message msg = new Message();

        new Thread(new Producer(msg)).start();
        new Thread(new Consumer(msg)).start();
    }
}

class Message {
    private String title;
    private String content;
    private boolean flag = true; // true 表示生产，false 表示消费

    // 线程持有当前对象的锁 wait()/this.wait()/super.wait() 都可以
    public synchronized void set(String title, String content) {
        if (!this.flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.title = title;
        this.content = content;
        this.flag = false;
        this.notify();
    }

    public synchronized String get() {
        if (this.flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String msg = this.title + "  -  " + this.content;
        this.flag = true;
        super.notify();
        return msg;
    }
}

class Producer implements Runnable {
    private Message message;

    public Producer(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        for (int i=0; i < 100; i++) {
            if (i % 2 == 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.message.set("Delores", "-- China");
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.message.set("William","----------------- People");
            }
        }
    }
}

class Consumer implements Runnable {
    private Message message;

    public Consumer(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        for (int i=0; i < 100; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.message.get());
        }
    }
}
