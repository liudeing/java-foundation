package Behavioral.Observer;

import java.util.Observable;

/**
 * 产品类，该类主要执行产品数据库的插入，更新
 * Created by liur on 17-4-30.
 */
public class Product extends Observable {
    private String name;
    private float price;

    public String getName(){
            return name;
    }

    public void setName(String name){
        this.name=name;
        //设置变化点
        setChanged();
        notifyObservers(name);
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
        //设置变化点
        setChanged();
        notifyObservers(new Float(price));
    }

    //保存数据库，更新等
    public void saveToDb(){
        System.out.println("save Db");
    }
}
