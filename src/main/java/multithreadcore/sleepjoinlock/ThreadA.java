package multithreadcore.sleepjoinlock;

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
            synchronized (b){
                b.start();
                //此Thread.sleep不释放锁
                Thread.sleep(8000);
                System.out.println("a end timer="+System.currentTimeMillis());
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
