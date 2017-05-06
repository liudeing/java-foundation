package Structural.Bridge;

/**
 * 大杯Coffee
 * Created by liur on 17-4-30.
 */
public class SuperSizeCoffee extends Coffee {
    public SuperSizeCoffee(){
        setCoffeeImp();
    }
    @Override
    public void pourCoffee() {
        CoffeeImp coffeeImp = this.getCoffeeImp();
        //重复5次是大杯
        for (int i=0;i<5;i++){
            coffeeImp.pourCoffeeImp();
        }
    }
}
