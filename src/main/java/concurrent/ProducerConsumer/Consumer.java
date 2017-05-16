package concurrent.ProducerConsumer;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingDeque;

/**
 * Created by liur on 17-4-26.
 */
public class Consumer implements Runnable {
    private BlockingDeque<PCData> queue;    //缓冲区
    private static final int SLEEPING = 1000;

    public Consumer(BlockingDeque<PCData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("start consumer id=" + Thread.currentThread().getId());
        Random r = new Random();    //随机等待时间


        try {
            while (true) {
                PCData data = queue.take();     //提取任务
                if(null!=data){
                    int re = data.getData() * data.getData();   //计算平方
                    System.out.println(MessageFormat.format("{0}*{1}={2}",data.getData(),data.getData(),re));
                    Thread.sleep(r.nextInt(SLEEPING));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

    }
}
