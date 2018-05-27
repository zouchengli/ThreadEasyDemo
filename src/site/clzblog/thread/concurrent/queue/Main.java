package site.clzblog.thread.concurrent.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description Use BlockingQueue
 * @Author chengli.zou
 * @CreateDate 2018-05-27 15:58
 **/
public class Main {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(10);

        ProducerThread producerThread1 = new ProducerThread(queue);
        ProducerThread producerThread2 = new ProducerThread(queue);

        ConsumerThread consumerThread = new ConsumerThread(queue);

        producerThread1.start();
        //producerThread2.start();

        consumerThread.start();

        Thread.sleep(10 * 1000);

        producerThread1.stopThread();

        //producerThread2.stopThread();
    }

}

class ProducerThread extends Thread {
    private BlockingQueue queue;

    private volatile boolean flag = true;

    private static AtomicInteger count = new AtomicInteger();

    ProducerThread(BlockingQueue blockingQueue) {
        this.queue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            System.out.println("Producer thread start ...");
            while (flag) {
                String data = count.incrementAndGet() + "";
                boolean offer = queue.offer(data);
                if (offer) {
                    System.out.println("Producer add queue " + data + " success!");
                } else {
                    System.out.println("Producer add queue " + data + " failure!");
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Producer thread stop ...");
        }
    }

    public void stopThread() {
        this.flag = false;
    }
}

class ConsumerThread extends Thread {
    private BlockingQueue queue;
    private volatile boolean flag = true;

    ConsumerThread(BlockingQueue blockingQueue) {
        this.queue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            System.out.println("Consumer thread start ...");
            while (flag) {
                // Get finish queue deleted
                String data = (String) queue.poll(2, TimeUnit.SECONDS);

                if (null != data) {
                    System.out.println("Consumer get data:" + data + " success ...");
                } else {
                    System.out.println("Consumer get data:" + data + " failure ...");
                    this.flag = false;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Consumer thread stop ...");
        }
    }
}
