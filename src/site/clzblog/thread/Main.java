package site.clzblog.thread;

class CreateThreadDemo1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 3000; i++) {
            System.out.println("run:" + i);
        }
    }
}

public class Main {

    /**
     * 什么是进程，进程就是正在运行的应用程序，进程是线程的集合
     * 什么是线程，线程就是一条执行路径，一个独立运行的执行单元
     * 什么是多线程，为了提高程序效率
     * 创建方式有哪些？
     * 1.使用继承Thread类方式，继承Thread类重写run方法
     * 2.使用实现Runnable接口方式
     * 3.使用匿名内部类方式
     * 4.callable
     * 5.使用线程池创建线程
     *
     * @param args
     */
    public static void main(String[] args) {
        // 1.怎么调用线程
        CreateThreadDemo1 createThreadDemo1 = new CreateThreadDemo1();
        // 启动线程不是调用run方法，而是调用start方法
        createThreadDemo1.start();
        for (int i = 0; i < 3000; i++) {
            System.out.println("main:" + i);
        }
    }
}
