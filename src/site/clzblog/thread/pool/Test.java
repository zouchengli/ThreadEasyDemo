package site.clzblog.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {
        cachedThreadDemo();

        fixedThreadDemo();

        scheduledThreadDemo();

        singleThreadDemo();
    }

    public static void cachedThreadDemo() {
        // 1.Could cached thread pool
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            int temp = i;
            newCachedThreadPool.execute(() -> System.out.println("ThreadName:" + Thread.currentThread().getName() + ",i:" + temp));
        }
        if (!newCachedThreadPool.isShutdown() && !newCachedThreadPool.isTerminated())
            newCachedThreadPool.shutdown();
    }

    public static void fixedThreadDemo() {
        // 2.Could fixed thread pool
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            int temp = i;
            newFixedThreadPool.execute(() -> System.out.println("ThreadName:" + Thread.currentThread().getName() + ",i:" + temp));
        }
        if (!newFixedThreadPool.isTerminated() && !newFixedThreadPool.isShutdown())
            newFixedThreadPool.shutdown();
    }

    public static void scheduledThreadDemo() {
        // 3.Could scheduled thread pool
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 10; i++) {
            int temp = i;
            newScheduledThreadPool.schedule(() -> System.out.println("ThreadName:" + Thread.currentThread().getName() + ",i:" + temp), 3, TimeUnit.SECONDS);
        }
        if (!newScheduledThreadPool.isShutdown() && !newScheduledThreadPool.isTerminated())
            newScheduledThreadPool.shutdown();
    }

    public static void singleThreadDemo() {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            int temp = i;
            newSingleThreadExecutor.execute(() -> System.out.println("ThreadName:" + Thread.currentThread().getName() + ",i:" + temp));
        }
        if (!newSingleThreadExecutor.isShutdown() && !newSingleThreadExecutor.isTerminated())
            newSingleThreadExecutor.shutdown();
    }
}
