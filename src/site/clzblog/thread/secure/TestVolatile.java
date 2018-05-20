package site.clzblog.thread.secure;

/**
 * @Description What is volatile? this keyword how is it use?
 * @Author chengli.zou
 * @CreateDate 2018-05-20 14:07
 **/

class ThreadVolatileDemo extends Thread {
    private volatile boolean flag = true;

    @Override
    public void run() {
        System.out.println("Sub thread start execute...");
        while (flag) {

        }
        System.out.println("Sub thread end execute...");
    }

    public void stopThread(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

public class TestVolatile {
    public static void main(String[] args) throws InterruptedException {
        ThreadVolatileDemo threadVolatileDemo = new ThreadVolatileDemo();
        threadVolatileDemo.start();
        Thread.sleep(3000);
        // Main thread modify share global variable for false
        threadVolatileDemo.setFlag(false);
        System.out.println("flag value is false!");
        Thread.sleep(1000);
        System.out.println(threadVolatileDemo.isFlag());
    }
}
