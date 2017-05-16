package concurrent.ConcurrentLine;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by liur on 17-5-2.
 */
public class Plus implements Runnable {
    public static BlockingDeque<Message> blockingDeque = new LinkedBlockingDeque<Message>();
    @Override
    public void run() {
        while (true){
            try {
                Message message=blockingDeque.take();
                message.j=message.i+message.j;
                Multiply.blockingDeque.add(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
