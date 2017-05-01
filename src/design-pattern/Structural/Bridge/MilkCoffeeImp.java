package Structural.Bridge;

/**
 * 加奶行为
 * Created by liur on 17-4-30.
 */
public class MilkCoffeeImp extends CoffeeImp {
    MilkCoffeeImp(){

    }
    @Override
    public void pourCoffeeImp() {
        System.out.println("加了美味的牛奶");
    }
}
