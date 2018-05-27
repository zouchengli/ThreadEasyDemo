package site.clzblog.thread.concurrent;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @Description Tell you use semaphore(Simulation person use wc)
 * @Author chengli.zou
 * @CreateDate 2018-05-27 14:35
 **/
public class TestSemaphore {
    public static void main(String[] args) throws InterruptedException {
        /*Semaphore semaphore = new Semaphore(5);

        // Get resources authority
        semaphore.acquire();

        // Get resources, subtract 1
        semaphore.availablePermits();

        // Release resources
        semaphore.release();*/

        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 10; i++) {
            new Parent(semaphore, "Person:" + i + ",").start();
        }
    }
}

class Parent extends Thread {
    Semaphore wc;
    String name;

    public Parent(Semaphore wc, String name) {
        this.wc = wc;
        this.name = name;
    }

    @Override
    public void run() {
        // Get resources, subtract 1
        int availablePermits = wc.availablePermits();

        if (availablePermits > 0) {
            System.out.println(name + " I have wc !!");
        } else {
            System.out.println(name + " Why no have wc ...");
        }
        try {
            wc.acquire();

            System.out.println(name + " Finally i can use wc !!!");

            Thread.sleep(new Random().nextInt(10000));

            System.out.println(name + " Wc finally use finish!");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            wc.release();
        }
    }
}