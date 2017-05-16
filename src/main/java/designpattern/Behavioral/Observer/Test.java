package designpattern.Behavioral.Observer;

/**
 * Created by liur on 17-4-30.
 */
public class Test {
    public static void main(String[] args) {
        Product product = new Product();
        //观察者角色
        NameObserver nameObserver = new NameObserver();
        PriceObserver priceObserver = new PriceObserver();
        //加入观察者
        product.addObserver(nameObserver);
        product.addObserver(priceObserver);

        product.setName("橘子红了");
        product.setPrice(9.22f);
    }
}
