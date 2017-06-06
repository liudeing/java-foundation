package multithreadcore.sleepjoinlock;

/**
 * Created by liur on 17-5-26.
 */
public class Run {
    public static void main(String[] args) {
        try {
            ThreadB b = new ThreadB();
            ThreadA a = new ThreadA(b);
            //打印b run begin
            a.start();
            //主线程休眠一秒
            Thread.sleep(1000);
            ThreadC c = new ThreadC(b);
            //此时a还持有b锁1秒
            c.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
