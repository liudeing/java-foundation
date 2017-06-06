package multithreadcore.objectlock;

/**
 * synchronized方法
 * Created by liur on 17-5-20.
 */
public class MyObject {
    /**
     * 在这个方法加synchronized,就会排序执行
     */
    synchronized public void methodA(){
        try{
            System.out.println("begin methodA threadName= "+Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("end");
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
