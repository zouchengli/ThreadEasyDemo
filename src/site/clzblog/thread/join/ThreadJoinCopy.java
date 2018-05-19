package site.clzblog.thread.join;

/**
 * @Description This is thread join copy(Create thread use lambada)
 * @Author chengli.zou
 * @CreateDate 2018-05-19 14:25
 **/
public class ThreadJoinCopy {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                System.out.println("Thread1,i:" + i);
            }
        });
        thread1.start();


        Thread thread2 = new Thread(() -> {

            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < 60; i++) {
                System.out.println("Thread2,i:" + i);
            }
        });
        thread2.start();

        Thread thread3 = new Thread(() -> {

            try {
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < 60; i++) {
                System.out.println("Thread3,i:" + i);
            }
        });
        thread3.start();

    }
}
