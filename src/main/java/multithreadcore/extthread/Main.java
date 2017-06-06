package multithreadcore.extthread;

/**
 * Created by liur on 17-5-21.
 */
public class Main {
    public int i=10;
    synchronized public void operateIMainMethod(){
        try{
            i--;
            System.out.println("main print i= "+i);
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
