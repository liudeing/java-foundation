package jvm.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理测试
 * Created by liur on 17-5-6.
 */
public class DynamicProxyTest {
    interface IHello {
        void sayHello();
    }

    static class Hello implements IHello {

        @Override
        public void sayHello() {
            System.out.println("hello world");
        }
    }

    //在代理类的实现前打印一个welcome
    static class DynamicProxy implements InvocationHandler {
        Object originalObj;

        Object bind(Object originalObj) {
            this.originalObj = originalObj;
            return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(), originalObj.getClass().getInterfaces(), this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("welcome");
            return method.invoke(originalObj, args);
        }
    }

    public static void main(String[] args) {
        //代理了new Hello()的实例行为对象
        IHello iHello = (IHello) new DynamicProxy().bind(new Hello());
        iHello.sayHello();

        //运行产生一个描述代理类的字节码byte[]数组
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
    }
}
