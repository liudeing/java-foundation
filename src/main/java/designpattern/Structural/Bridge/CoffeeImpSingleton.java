package designpattern.Structural.Bridge;

/**
 * 设计一个单态类来hold当前行为类
 * Created by liur on 17-4-30.
 */
public class CoffeeImpSingleton {
    private static CoffeeImp coffeeImp;
    public CoffeeImpSingleton (CoffeeImp coffeeImp){
        this.coffeeImp=coffeeImp;
    }
    public static CoffeeImp getTheCoffeeImp(){
        return coffeeImp;
    }
}
