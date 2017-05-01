package Structural.Bridge;

/**
 * 抽象接口代码,加奶和不加奶是一个行为，将它抽象为一个专门的接口CoffeeImp
 * Created by liur on 17-4-30.
 */
public abstract class Coffee {
    CoffeeImp coffeeImp;

    public CoffeeImp getCoffeeImp() {
        return coffeeImp;
    }

    public void setCoffeeImp() {
        this.coffeeImp = CoffeeImpSingleton.getTheCoffeeImp();
    }

    public abstract void pourCoffee();
}
