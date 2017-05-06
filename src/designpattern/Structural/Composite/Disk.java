package Structural.Composite;

/**
 * Disk是组合体内的一个对象，或称一个部件，这个部件就是单独元素（Primitive）,
 * 一个部件也可以是一个组合体
 * Created by liur on 17-4-29.
 */
public class Disk extends Equipment {
    public Disk(String name){
        super(name);
    }

    /**
     * 定义Disk实价为1
     * @return
     */
    @Override
    public double netPrice() {
        return 1;
    }

    /**
     * 定义Disk的折扣价为0.5对折
     * @return
     */
    @Override
    public double discountPrice() {
        return 0.5;
    }
}
