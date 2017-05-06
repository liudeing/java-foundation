package Structural.Decorator;

/**
 * 具体的实例，插入方形柱
 * Created by liur on 17-4-29.
 */
public class SquarePeg implements Work {
    @Override
    public void insert() {
        System.out.println("方形柱插入");
    }
}
