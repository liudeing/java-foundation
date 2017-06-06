package multithreadcore.produceconsumer;

/**
 * Created by liur on 17-5-24.
 */
public class Consumer {
    private String lock;
    public Consumer(String lock){
        super();
        this.lock=lock;
    }
    public void getValue(){
        try{
            synchronized (lock){
                if (ValueObject.value.equals("")){
                    lock.wait();
                }
                System.out.println("get的值是 "+ValueObject.value);
                ValueObject.value="";
                lock.notify();
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
