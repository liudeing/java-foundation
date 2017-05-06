package designpattern.Structural.Composite;

import java.util.Iterator;

/**
 * 一般用接口定义,有Component,代表着组合体内的对象们
 * Created by liur on 17-4-28.
 */
public abstract class Equipment {
    private String name;
    public Equipment(final String name){
        this.name = name;
    }
    //实价
    public abstract double netPrice();
    //折扣价格
    public abstract double discountPrice();
    //增加部件方法
    public boolean add(Equipment equipment){
        return false;
    }
    //删除部件方法
    public boolean remove(Equipment equipment){
        return false;
    }
    //这是里提供的一个用于访问组合体类的部件方法
    public Iterator iter(){
        return null;
    }
}
