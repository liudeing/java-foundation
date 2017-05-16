package designpattern.Structural.Flyweight;

/**
 * Created by liur on 17-4-30.
 */
public class ConcreteFlyweight implements Flyweight {
    private IntrinsicState state;
    @Override
    public void operation(ExtrinsicState state) {
        //具体操作
    }
}
