package designpattern.Behavioral.Observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 主要对产品name进行观察
 * Created by liur on 17-4-30.
 */
public class NameObserver implements Observer {
    private String name=null;
    public void update(Observable o,Object arg){
        if (arg instanceof String){
            name = (String) arg;
            //产品名称改变 值在name中
            System.out.println("NameObserver:name change to "+name);
        }
    }
}
