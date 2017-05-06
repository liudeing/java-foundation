package designpattern.Structural.Composite;

/**
 * Created by liur on 17-4-29.
 */
public class Test {
    public static void main(String[] args) {
        Cabinet cabinet = new Cabinet("Tower");
        Chassis chassis =new Chassis("PC chassis");

        //将PC chassis装到Tower中（装盘盒装到箱子中）
        chassis.add(cabinet);
        //将一个10GB的硬盘盒装到盘盒里
        chassis.add(new Disk("10 GB"));

        /**
         * 这里的调用，Composite会使用Iterator遍历整个树形结构，寻找包含这个方法的对象并调用执行
         */
        System.out.println("netPrice="+cabinet.netPrice());
        System.out.println("discountPrice="+cabinet.discountPrice());
    }
}
