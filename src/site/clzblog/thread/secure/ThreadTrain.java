package site.clzblog.thread.secure;

/**
 * @Description 多线程模拟抢火车票发生的并发，解决方案就是加上同步代码块synchronized 或者直接在方法前面加上（同步函数使用的是this锁）
 * @Author chengli.zou
 * @CreateDate 2018-05-19 18:28
 **/
public class ThreadTrain implements Runnable {
    // 总共有一百张火车票
    private int trainCount = 100;
    private Object object = new Object();
    public boolean flag = true;// 同步代码块和同步函数切换开关

    @Override
    public void run() {
        if (flag) {
            // 执行同步代码块this锁
            while (trainCount > 0) {
                synchronized (object) {
                    // 如果现在放object会卖出101张票 放入this就不会，放入this的同步代码块同等于下面的同步函数
                    // 由此可以证明下面的同步函数就是用的this锁
                    // 非this是不能够同步的
                    ticket();
                }
            }
        } else {
            // 同步函数
            // 为了能够模拟程序一直在抢票
            while (trainCount > 0) {

                // 出售火车票
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
        if (trainCount > 0) {
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
        ThreadTrain threadTrain = new ThreadTrain();
        Thread thread1 = new Thread(threadTrain, "Window1");
        Thread thread2 = new Thread(threadTrain, "Window2");
        thread1.start();
        Thread.sleep(40);
        threadTrain.flag = false;
        thread2.start();
    }
}
