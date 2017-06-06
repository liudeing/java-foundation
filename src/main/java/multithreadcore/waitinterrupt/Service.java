package multithreadcore.waitinterrupt;

/**
 * 等待锁的情况
 * Created by liur on 17-5-24.
 */
public class Service {
    public void testMethod(Object lock){
        try {
            synchronized (lock){
                System.out.println("begin wait()");
                lock.wait();
                System.out.println("end wait()");
            }
        } catch (InterruptedException e){
            e.printStackTrace();
            System.out.println("出现异常了，因为呈wait状态的线程被interrupte了！");
        }
    }
}
