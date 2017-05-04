package Behavioral.Command;

import java.util.Iterator;
import java.util.List;

/**
 * 调用Command模式
 * Created by liur on 17-5-1.
 */
public class Test {
    public static void main(String[] args) {
        List queue = Producer.produceRequest();

        for (Iterator it = queue.iterator();it.hasNext();){
            //客户端直接调用execute方法，无需知道被调用者的其它更多类的方法名
            ((Command)it.next()).execute();
        }
    }
}
