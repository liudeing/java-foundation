package Creational.Builder;

/**
 * 接口，定义如何创建复杂对象的各个部件
 * Created by liur on 17-4-28.
 */
public interface Builder {
    //创建部件A,如创建汽车车轮
    void buildPartA();
    //创建部件B,如创建汽车方向盘
    void buildPartB();
    //创建部件C,如创建汽车发动机
    void buildPartC();

    //返回组装好的成品（装配好的汽车）
    //成品组装过程不在这里进行，而是在Director类中进行
    //从而实现解耦过程和部件
    Product getResult();
}
