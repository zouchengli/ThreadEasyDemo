package site.clzblog.thread.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Use reentrant read write lock demo
 */
public class Cache {

    private volatile static Map<String, Object> map = new HashMap<>();

    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static Lock readLock = readWriteLock.readLock();

    private static Lock writeLock = readWriteLock.writeLock();

    /**
     * Write
     */
    public static Object put(String key, Object value) {
        try {
            writeLock.lock();

            System.out.println("Writing key:" + key + ",value:" + value + " begin...");

            Thread.sleep(100);

            Object object = map.put(key, value);
            System.out.println("Writing key:" + key + ",value:" + value + " end...\n");

            return object;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
        return null;
    }

    /**
     * Read
     */
    public static void get(String key) {
        try {
            readLock.lock();

            System.out.println("Reading key:" + key + " begin...");

            Thread.sleep(100);

            Object value = map.get(key);

            System.out.println("Reading key:" + key + ",value:" + value + " end...\n");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                Cache.put(i + "", i + "");
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                Cache.get(i + "");
            }
        }).start();
    }
}
