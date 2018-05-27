package site.clzblog.thread.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @Description
 * @Author chengli.zou
 * @CreateDate 2018-05-27 13:49
 **/
public class TestCountDownLatch {
    static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {

        thread();

        thread();

        countDownLatch.await();

        System.out.println("Main thread start execute task");

        for (int i = 0; i < 6; i++) {
            System.out.println(i);
        }
    }

    private static void thread() {
        new Thread(() -> {
            System.out.println("I'm sub thread start execute task ...");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            System.out.println("I'm sub thread start execute task ...");
        }).start();
    }
}
