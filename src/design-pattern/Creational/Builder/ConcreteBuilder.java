package Creational.Builder;

/**
 * Builder的具体实现类，通过具体完成接口Builder来构建或装配产品的部件;
 * 定义并明确它所要创建的是什么具体的东西;提供一个可以重新获取产品的接口
 * Created by liur on 17-4-28.
 */
public class ConcreteBuilder implements Builder {
    Part partA, partB, partC;

    @Override
    public void buildPartA() {
        //这里是具体如何构建partA的代码
    }

    @Override
    public void buildPartB() {
        //这里是具体如何构建partB的代码
    }

    @Override
    public void buildPartC() {
        //这里是具体如何构建partC的代码
    }

    @Override
    public Product getResult() {
        //返回最后组装成品结果
        return null;
    }
}
