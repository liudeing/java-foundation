package designpattern.Creational.Prototype;

/**
 * 利用Java中提供的clone()方法来实现对象的克隆,Prototype模式
 * Created by liur on 17-4-27.
 */
public abstract class AbstractSpoon implements Cloneable {
    String spoonName;

    public String getSpoonName() {
        return spoonName;
    }

    public void setSpoonName(String spoonName) {
        this.spoonName = spoonName;
    }

    public Object clone() {
        Object object = null;
        try {
            object = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            System.err.println("AbstractSpoon is not Cloneable  ");
        }
        return object;
    }
}
