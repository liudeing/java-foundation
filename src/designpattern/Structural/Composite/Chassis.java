package designpattern.Structural.Composite;

/**
 * 具休类，盘盒，里面可以放许多小工具
 * Created by liur on 17-4-29.
 */
public class Chassis extends CompositeEnquipment {
    public Chassis(String name) {
        super(name);
    }

    public double netPrice(){
        return 1+super.netPrice();
    }

    public double discountPrice(){
        return 0.5+super.discountPrice();
    }
}
