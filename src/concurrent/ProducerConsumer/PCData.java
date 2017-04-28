package concurrent.ProducerConsumer;

/**
 * Created by liur on 17-4-26.
 */
public class PCData {
    private final int intData;
    public PCData(int d) {
        this.intData=d;
    }

    public PCData(String d){
        intData = Integer.valueOf(d);
    }

    public int getData() {
        return intData;
    }

    public String toString(){
        return "data:"+intData;
    }
}
