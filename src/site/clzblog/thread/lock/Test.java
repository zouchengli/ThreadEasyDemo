package site.clzblog.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test implements Runnable {
    private Lock lock = new ReentrantLock();

    public void get() {
        lock.lock();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getId() + ",get");
        set();
        lock.unlock();
    }

    public void set() {
        //lock.lock();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getId() + ",set");
        //lock.unlock();
    }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        Test test = new Test();
        new Thread(test).start();
        new Thread(test).start();
        new Thread(test).start();
        new Thread(test).start();
    }

}
