package Structural.Flyweight;

/**
 * Created by liur on 17-4-30.
 */
public class Main {
    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight fly1 = factory.getFlyweight("Fred");
        Flyweight fly2 = factory.getFlyweight("Wilma");

        System.out.println(fly1+" "+fly2);
    }
}
