package designpattern.Structural.Bridge;

/**
 * 具体类 concrete class,中杯
 * Created by liur on 17-4-30.
 */
public class MediumCoffee extends Coffee {
    public MediumCoffee(){
        setCoffeeImp();
    }

    @Override
    public void pourCoffee() {
       CoffeeImp coffeeImp = this.getCoffeeImp();
       //我们以重复次数来说是冲中杯，还是冲大杯，重复2次是冲中杯
        for (int i=0;i<2;i++){
            coffeeImp.pourCoffeeImp();
        }
    }
}
