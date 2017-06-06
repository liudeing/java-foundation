package multithreadcore.single;

/**
 * 线程不共享实例对象
 * Created by liur on 17-5-12.
 */
public class MyThread extends Thread {
    private int count = 5;

    public MyThread(String name) {
        super();
        this.setName(name);
    }

    @Override
    public void run() {
        super.run();
        while (count > 0) {
            count--;
            System.out.println("由 " + Thread.currentThread().getName() + "计算，count= " + count);
        }
    }
}
