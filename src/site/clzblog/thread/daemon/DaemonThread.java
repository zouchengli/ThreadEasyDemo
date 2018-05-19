package site.clzblog.thread.daemon;

/**
 * @Description This is daemon thread demo
 * @Author chengli.zou
 * @CreateDate 2018-05-19 13:59
 **/
public class DaemonThread {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Sub thread:" + i);
            }
        });
        thread.setDaemon(true);// 该线程为守护线程 和主线程一起销毁
        thread.start();
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Main thread: " + i);
        }
        System.out.println("Main thread execute finish...");
    }
}
