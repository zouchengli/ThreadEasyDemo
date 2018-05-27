package site.clzblog.thread.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description This is test use CyclicBarrier
 * @Author chengli.zou
 * @CreateDate 2018-05-27 14:12
 **/
public class TestCyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        for (int i = 0; i < 5; i++) {
            new Writer(cyclicBarrier).start();
        }
    }
}

class Writer extends Thread {
    CyclicBarrier cyclicBarrier;

    public Writer(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println(getName() + " start write data...");
        try {
            // Simulation consume time
            Thread.sleep(30);
            System.out.println(getName() + " write data success...");
            cyclicBarrier.await();
            System.out.println(getName() + " all date execute finish...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}
