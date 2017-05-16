package designpattern.Structural.Bridge;

/**
 * 不加奶
 * Created by liur on 17-4-30.
 */
public class FragrantCoffeeImp extends CoffeeImp {
    FragrantCoffeeImp(){

    }
    @Override
    public void pourCoffeeImp() {
        System.out.println("什么也没加，清香");
    }
}
