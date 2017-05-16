package concurrent.ConcurrentLine;

/**
 * Created by liur on 17-5-2.
 */
public class Test {
    public static void main(String[] args) {
        new Thread(new Plus()).start();
        new Thread(new Multiply()).start();
        new Thread(new Div()).start();

        for (int i=1;i<10;i++){
            for (int j=1;j<10;j++){
                Message message = new Message();
                message.i = i;
                message.j = j;
                message.objStr = "(("+i+"+"+j+")*"+i+")/2";
                Plus.blockingDeque.add(message);
            }
        }
    }
}
