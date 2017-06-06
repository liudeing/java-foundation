package multithreadcore.joinmore;

/**
 * Created by liur on 17-5-26.
 */
public class ThreadA extends Thread {
    private ThreadB b;
    public ThreadA(ThreadB b){
        this.b=b;
    }

    @Override
    public void run() {
        try {
            synchronized (b) {
                System.out.println("bein a ThreadName=" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
                Thread.sleep(5000);
                System.out.println("end a ThreadName=" + Thread.currentThread().getName() + " " + System.currentTimeMillis());

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
