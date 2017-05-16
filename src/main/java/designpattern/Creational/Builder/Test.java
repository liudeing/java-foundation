package designpattern.Creational.Builder;

/**
 * 调用Builder模式
 * Created by liur on 17-4-28.
 */
public class Test {
    public static void main(String[] args) {
        ConcreteBuilder builder = new ConcreteBuilder();
        Director director = new Director(builder);

        director.construct();
        Product product = builder.getResult();
    }
}
