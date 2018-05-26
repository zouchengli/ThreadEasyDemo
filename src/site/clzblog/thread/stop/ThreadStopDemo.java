package site.clzblog.thread.stop;

/**
 * @Description Stop thread demo
 * @Author chengli.zou
 * @CreateDate 2018-05-26 19:04
 **/
public class ThreadStopDemo extends Thread{
    private volatile boolean flag = true;

    @Override
    public synchronized void run() {
        System.out.println("Sub thread start execute...");
        while (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                stopThread();
            }
        }
        System.out.println("Sub thread finish execute。。。");
    }

    public void stopThread() {
        System.out.println("Call stopThread method...");
        this.flag = false;
        System.out.println("Updated flag " + flag);
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadStopDemo threadStopDemo = new ThreadStopDemo();
        threadStopDemo.start();
        for (int i = 1; i <= 10; i++) {
            System.out.println("Main thread " + i);
            Thread.sleep(1000);
            if (i == 3) {
                threadStopDemo.interrupt();
            }
        }
    }

}
