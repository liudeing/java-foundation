package multithreadcore.sleepjoinlock;

/**
 * Created by liur on 17-5-26.
 */
public class ThreadC extends Thread {
    private ThreadB b;
    public ThreadC(ThreadB b){
        this.b = b;
    }

    @Override
    public void run() {
        b.bService();
    }
}
