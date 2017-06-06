package multithreadcore.produceconsumer;

/**
 * Created by liur on 17-5-24.
 */
public class Produce {
    private String lock;
    public Produce(String lock){
        super();
        this.lock=lock;
    }
    public void setValue(){
        try{
            synchronized (lock){
                if (!ValueObject.value.endsWith("")){
                    lock.wait();
                }
                String value=System.currentTimeMillis()+"_"+System.nanoTime();
                System.out.println("set的值是 "+value);
                ValueObject.value=value;
                lock.notify();
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
