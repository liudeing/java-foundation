package multithreadcore.objectlock;

/**
 * 针对共享对象的调用
 * Created by liur on 17-5-20.
 */
public class Run {
    public static void main(String[] args) {
        MyObject object = new MyObject();
        ThreadA a = new ThreadA(object);
        a.setName("A");
        ThreadB b = new ThreadB(object);
        b.setName("B");
        a.start();
        b.start();
    }
}
