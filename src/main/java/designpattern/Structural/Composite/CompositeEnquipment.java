package designpattern.Structural.Composite;

import java.util.*;

/**
 * Created by liur on 17-4-29.
 */
abstract class CompositeEnquipment extends Equipment{
    private int i=0;
    //定义一个Vector来存放‘儿子’
    private Vector equipment  = new Vector();

    public CompositeEnquipment(String name){
        super(name);
    }

    public boolean add(Equipment equipment){
        this.equipment.add(equipment);
        return true;
    }

    @Override
    public double netPrice() {
        double netPrice = 0;
        Iterator iter = equipment.iterator();
        while (iter.hasNext()){
            netPrice+=((Equipment)iter.next()).netPrice();
        }
        return netPrice;
    }

    @Override
    public double discountPrice() {
        double discountPrice = 0;
        Iterator iter = equipment.iterator();
        while (iter.hasNext()){
            discountPrice+=((Equipment)iter.next()).discountPrice();
        }
        return discountPrice;
    }

    /**
     * 这是就是提供访问组合体内部的方法，
     * Diskj里没有，是因为Disk是单独（Primitive）的元素
     * @return
     */
    public Iterator iter(){
        return equipment.iterator();
    }

    //重载Iterator方法
    public boolean hasNext() {
        return i<equipment.size();
    }
    //重载Iterator方法
    public Object next() {
        if(hasNext())
            return equipment.elementAt(i++);
        else
            throw new NoSuchElementException();
    }
}
