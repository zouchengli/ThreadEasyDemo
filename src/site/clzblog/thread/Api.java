package site.clzblog.thread;

/**
 * @Description
 * @Author chengli.zou
 * @CreateDate 2018-05-19 12:15
 **/

class CreateThread extends Thread {
    // getId() Thread id is unique no repeat
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == 5) {
                //stop();// No secure No suggest everyone use
            }
            System.out.println("Thread id:" + getId() + ",Sub thread i:" + i + ",name:" + getName() + ",StateName:" + getState().name());
        }
    }
}

public class Api {
    public static void main(String[] args) {
        // 如何拿到主线程id呢？
        System.out.println("Main thread id:" + Thread.currentThread().getId() + ",name:" + Thread.currentThread().getName());
        for (int i = 0; i < 2; i++) {
            CreateThread createThread = new CreateThread();
            createThread.start();
            new Thread(createThread, "Custom thread name").start();
        }
    }
}
