package site.clzblog.thread.secure;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description What is atomic class?How is it use?
 * @Author chengli.zou
 * @CreateDate 2018-05-20 14:34
 **/
public class VolatileNoAtomic extends Thread {

    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            count.incrementAndGet();
        }
        System.out.println(getName() + "," + count);
    }

    public static void main(String[] args) {
        VolatileNoAtomic[] volatileNoAtomics = new VolatileNoAtomic[10];
        for (int i = 0; i < volatileNoAtomics.length; i++) {
            volatileNoAtomics[i] = new VolatileNoAtomic();
        }
        for (VolatileNoAtomic volatileNoAtomic : volatileNoAtomics) {
            volatileNoAtomic.start();
        }
    }
}
