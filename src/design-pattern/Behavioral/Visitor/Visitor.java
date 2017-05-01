package Behavioral.Visitor;

import java.util.Collection;

/**
 * 定义一个访问者接口
 * Created by liur on 17-5-1.
 */
public interface Visitor {
    void visitString(StringElement stringElement);
    void visitFloat(FloatElement floatElement);
    void visitCollection(Collection collection);
}
