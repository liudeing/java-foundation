package multithreadcore.stringsynchronized;

/**
 * String的常量池特性
 * Created by liur on 17-5-24.
 */
public class Service {
    public static void print(String stingParam){
        try {
            synchronized (stingParam){
                while (true){
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
