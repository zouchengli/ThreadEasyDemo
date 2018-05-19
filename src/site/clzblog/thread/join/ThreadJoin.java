package site.clzblog.thread.join;

/**
 * @Description This is thread join demo
 * @Author chengli.zou
 * @CreateDate 2018-05-19 14:18
 **/
public class ThreadJoin {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                System.out.println("Sub thread:" + i);
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 主线程让子线程先执行完毕的话，怎么做呢?在启动子线程后加上join关键字
        for (int i = 0; i < 20; i++) {
            System.out.println("Main thread: " + i);
        }
    }
}
