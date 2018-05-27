package site.clzblog.thread.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;

/**
 * @Description Use ConcurrentLinkedDeque
 * @Author chengli.zou
 * @CreateDate 2018-05-27 15:25
 **/
public class TestQueue {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedDeque concurrentLinkedDeque = new ConcurrentLinkedDeque();
        concurrentLinkedDeque.offer("JackMa");
        concurrentLinkedDeque.offer("Tony");
        System.out.println(concurrentLinkedDeque.size());
        System.out.println(concurrentLinkedDeque.poll());
        System.out.println(concurrentLinkedDeque.size());
        System.out.println(concurrentLinkedDeque.peek());
        System.out.println(concurrentLinkedDeque.size());

        ArrayBlockingQueue<Object> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        arrayBlockingQueue.add("JackMa");
        arrayBlockingQueue.add("Tony");
        arrayBlockingQueue.add("Nike");
        boolean offer = arrayBlockingQueue.offer("Me", 2, TimeUnit.SECONDS);
        System.out.println(offer);
        System.out.println(arrayBlockingQueue.size());
    }
}
