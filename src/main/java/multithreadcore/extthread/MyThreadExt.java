package multithreadcore.extthread;

/**
 * Created by liur on 17-5-21.
 */
public class MyThreadExt extends Thread {
    @Override
    public void run() {
        Sub sub = new Sub();
        sub.operateISubMethod();
    }
}
