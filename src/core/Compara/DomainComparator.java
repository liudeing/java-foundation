package core.Compara;

import java.util.Comparator;

/**
 * Comparator可以看成一种算法的实现，将算法和数据分离的策略模式
 * Comparator也可以在下面两种环境下使用：
 * 1、类的设计师没有考虑到比较问题而没有实现Comparable，可以通过Comparator来实现排序而不必改变对象本身
 * 2、可以使用多种排序标准，比如升序、降序等
 * Created by liur on 17-5-5.
 */
public class DomainComparator implements Comparator<Domain> {
    @Override
    public int compare(Domain o1, Domain o2) {
        if (o1.getStr().compareTo(o2.getStr())>0){
            return 1;
        } else if(o1.getStr().compareTo(o2.getStr())==0){
            return 0;
        }else{
            return -1;
        }
    }

    public static void main(String[] args) {
        Domain d1 = new Domain("c");
        Domain d2 = new Domain("c");
        Domain d3 = new Domain("b");
        Domain d4 = new Domain("d");
        DomainComparator dc = new DomainComparator();
        System.out.println(dc.compare(d1, d2));
        System.out.println(dc.compare(d1, d3));
        System.out.println(dc.compare(d1, d4));
    }
}
