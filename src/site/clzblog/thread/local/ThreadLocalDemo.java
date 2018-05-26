package site.clzblog.thread.local;

/**
 * @Description This is thread local demo
 * @Author chengli.zou
 * @CreateDate 2018-05-26 20:06
 **/
public class ThreadLocalDemo {
    public static void main(String[] args) {
        ResultNumber resultNumber = new ResultNumber();
        ThreadLocal threadLocal1 = new ThreadLocal(resultNumber);
        ThreadLocal threadLocal2 = new ThreadLocal(resultNumber);
        ThreadLocal threadLocal3 = new ThreadLocal(resultNumber);
        threadLocal1.start();
        threadLocal2.start();
        threadLocal3.start();
    }
}

class ResultNumber {
    public int count = 0;

    public static java.lang.ThreadLocal<Integer> threadLocal = java.lang.ThreadLocal.withInitial(() -> 0);

    public String getNumber() {
        count = threadLocal.get() + 1;
        threadLocal.set(count);
        return count + "";
    }
}

class ThreadLocal extends Thread {
    private ResultNumber resultNumber;

    public ThreadLocal(ResultNumber resultNumber) {
        this.resultNumber = resultNumber;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(getName() + ",Result number:" + resultNumber.getNumber());
        }
    }
}
