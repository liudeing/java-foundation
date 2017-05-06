package designpattern.Structural.Decorator;

/**
 * Decorator模式的调用，有点类似记取文件时的调用
 * FileReader fr = new FileReader(filename);
 * BufferedReader br = new BufferedReader(fr);
 * 实际上Java 的I/O API就是使用Decorator实现的,I/O变种很多,如果都采取继承方法,将会产生很多子类,显然相当繁琐.
 * Created by liur on 17-4-29.
 */
public class Test {
    public static void main(String[] args) {
        Work squarePeg = new SquarePeg();
        Work decorator = new Decorator(squarePeg);
        decorator.insert();
    }
}
