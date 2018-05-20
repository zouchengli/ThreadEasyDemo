package site.clzblog.thread.secure;

/**
 * @Description 多线程模拟抢火车票发生的并发（模拟死锁）
 *
 * @Author chengli.zou
 * @CreateDate 2018-05-19 18:28
 **/
public class ThreadTrain1 implements Runnable {
    // 总共有一百张火车票
    private int trainCount = 100;
    private Object object = new Object();
    public boolean flag = true;// 同步代码块和同步函数切换开关

    @Override
    public void run() {
        if (flag) {
            // 执行同步代码块this锁

            while (true) {
                // (该线程先拿到同步代码块object这把锁，然后再拿到同步函数this锁, 然后再拿到object锁)
                synchronized (object) {
                    sale();
                }
            }
        } else {
            // 同步函数
            // 为了能够模 拟程序一直在抢票

            while (true) {
                //（该线程先拿到同步函数this锁，再拿到同步代码块object锁，这样就互不让锁，会导致线程一直处于等待）
                sale();
            }
        }

    }

    /**
     * 出售(该方法使用同步函数)
     */
    private synchronized void sale() {
        ticket();
    }

    /**
     * 票
     */
    private void ticket() {
        synchronized (object) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "出售第" + (100 - trainCount + 1) + "票！");
                trainCount--;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadTrain1 threadTrain = new ThreadTrain1();
        Thread thread1 = new Thread(threadTrain, "Window1");
        Thread thread2 = new Thread(threadTrain, "Window2");
        thread1.start();
        Thread.sleep(40);
        threadTrain.flag = true;
        thread2.start();
    }
}
