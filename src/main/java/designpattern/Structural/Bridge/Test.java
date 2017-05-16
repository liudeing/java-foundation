package designpattern.Structural.Bridge;

/**
 * 注意: Bridge模式的执行类如CoffeeImp和Coffee是一对一的关系, 正确创建CoffeeImp是该模式的关键。
 * Created by liur on 17-4-30.
 */
public class Test {
    public static void main(String[] args) {
        //拿出牛奶
        CoffeeImpSingleton coffeeImpSingleton  = new CoffeeImpSingleton(new MilkCoffeeImp());

        //中杯加奶
        MediumCoffee mediumCoffee = new MediumCoffee();
        mediumCoffee.pourCoffee();

        //大杯加奶
        SuperSizeCoffee superSizeCoffee = new SuperSizeCoffee();
        superSizeCoffee.pourCoffee();
    }
}
