package site.clzblog.thread;

/**
 * @Description
 * @Author chengli.zou
 * @CreateDate 2018-05-19 11:59
 **/

class CreateThreadDemo2 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("run:" + i);
        }
    }
}

public class MainCopy {

    public static void main(String[] args) {
        CreateThreadDemo2 createThreadDemo2 = new CreateThreadDemo2();
        Thread thread = new Thread(createThreadDemo2);
        thread.start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("main:" + i);
        }
    }

}
