package multithreadcore.runstart;

/**
 * Created by liur on 17-5-25.
 */
public class TestThread2 {
    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                System.out.println("thread run"+Thread.currentThread().getName());
            }
        }.run();
    }
}
