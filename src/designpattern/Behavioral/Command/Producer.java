package designpattern.Behavioral.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * 按照Command模式，把命令封装起来扔到盒子（List）里
 * 封装进List后，就失去了外表特征，取出后也难以识别。
 * Created by liur on 17-5-1.
 */
public class Producer {
    public static List produceRequest(){
        List queue = new ArrayList();
        queue.add(new Engineer());
        queue.add(new Programmer());
        queue.add(new Politician());
        return queue;
    }
}
