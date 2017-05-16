package designpattern.Structural.Composite;

/**
 * CompositEnquipment的具体类，箱子，可以放许多盘盒，底板。。
 * Created by liur on 17-4-29.
 */
public class Cabinet extends CompositeEnquipment{
    public Cabinet(String name) {
        super(name);
    }
    public double netPrice(){
        return 1+super.netPrice();
    }

    public double discountPrice(){
        return 0.5+super.discountPrice();
    }
}
