package Structural.Flyweight;

import java.util.Hashtable;

/**
 * 存放内部状态，当客户请求一个共享Flyweight时，这个factory首先搜索池中是否已有可适用的，如果有，factory只是
 * 简单的送出对象，如果没有，就创建一个对象，加入到池中，再返回送出这个对象
 * Created by liur on 17-4-30.
 */
public class FlyweightFactory {
    //Flyweight pool
    private Hashtable flyweights = new Hashtable();

    public Flyweight getFlyweight(Object key){
        Flyweight flyweight = (Flyweight) flyweights.get(key);

        if (flyweight==null){
            //产生新的ConcreteFlyweight
            flyweight = new ConcreteFlyweight();
            flyweights.put(key,flyweight);
        }

        return flyweight;
    }
}
