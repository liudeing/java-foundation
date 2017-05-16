package concurrent;

/**
 * Created by liur on 17-5-13.
 */
public class IdentityHashCode {
    public static void main(String[] args) {
        String s1 = new String("Hello");
        String s2 = new String("Hello");

        //String类重写了Object类的hashCode()方法，改为基于字符序列计算hashCode值，所有s1,s2的hashCode值应该是一样的
        System.out.println("s1.hashCode():" + s1.hashCode() + " s2.hashCode():" + s2.hashCode());
        //System.identityHashCode()是根据内存地址计算hashCode值，所以s1,s2的System.identityHashCode值不一样
        System.out.println("s1 identityHashCode:" + System.identityHashCode(s1) + " s2 identityHashCode:" + System.identityHashCode(s2));
    }
}
