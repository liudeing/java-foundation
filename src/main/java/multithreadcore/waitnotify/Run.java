package multithreadcore.waitnotify;

/**
 * Created by liur on 17-5-22.
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        ThreadA a = new ThreadA(lock);
        a.start();
        Thread.sleep(50);
        ThreadB b = new ThreadB(lock);
        b.start();
    }
}
