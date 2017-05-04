package concurrent.ConcurrentLine;

/**
 * 计算(B+C)*B/2
 * P1 A = B+C
 * P2 D = A*B
 * P3 D = D/2
 * 并发流水线。定义一个在线程间携带结果进行信息交换的载体
 * Created by liur on 17-5-2.
 */
public class Message {
    public double i;
    public double j;
    public String objStr=null;
}
