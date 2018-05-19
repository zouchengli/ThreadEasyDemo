package site.clzblog.thread;

/**
 * @Description
 * @Author chengli.zou
 * @CreateDate 2018-05-19 12:05
 **/

abstract class Parent {
    public abstract void add();
}

public class Anonymous {
    public static void main(String[] args) {
        // 什么是匿名内部类
        Parent parent = new Parent() {
            @Override
            public void add() {
                System.out.println("Use custom inner class");
            }
        };
        parent.add();

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 3000; i++) {
                System.out.println("Sub thread: " + i);
            }
        });

        thread.start();
        for (int i = 0; i < 3000; i++) {
            System.out.println("Main thread: " + i);
        }
    }
}
