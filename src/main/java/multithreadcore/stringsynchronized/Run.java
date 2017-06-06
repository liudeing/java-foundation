package multithreadcore.stringsynchronized;

/**
 * 两个线程持有相同的锁，所在造成B不能执行。
 * Created by liur on 17-5-24.
 */
public class Run {
    public static void main(String[] args) {
        Service service = new Service();
        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();
        ThreadB b = new ThreadB(service);
        b.setName("B");
        b.start();
    }
}
