package Prototype;

/**
 * 调用Prototype模式,克隆的两个对象
 * Created by liur on 17-4-27.
 */
public class Test {
    public static void main(String[] args) {
        AbstractSpoon spoon = new SoupSpoon();
        AbstractSpoon spoon2 = (AbstractSpoon) spoon.clone();
        System.out.println(spoon.getSpoonName());
        System.out.println(spoon2.getSpoonName());
    }
}
