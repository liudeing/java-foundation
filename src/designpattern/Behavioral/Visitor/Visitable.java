package designpattern.Behavioral.Visitor;

/**
 * 定义接口叫Visitable,用来定义一个Accept操作,也就是说让Collection每个元素具备可访问性.
 * 被访问者是我们Collection的每个元素Element,我们要为这些Element定义一个可以接受访问的接口
 * (访问和被访问是互动的,只有访问者,被访问者如果表示不欢迎,访问者就不能访问),取名为Visitable，
 * 也可取名为Element。
 * Created by liur on 17-5-1.
 */
public interface Visitable {
    public void accept(Visitor vistor);
}
