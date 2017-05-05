package core.Compara;

/**
 * Comparable接口表明这个类的对象之间是可以互相比较的。这个类的对象组成的集合就可以直接用sort方法排序。
 * Created by liur on 17-5-5.
 */
public class Domain implements Comparable<Domain> {
    private String str;

    public Domain(String str) {
        this.str = str;
    }

    @Override
    public int compareTo(Domain domain) {
        if (this.str.compareTo(domain.str)>0){
            return 1;
        } else if (this.str.compareTo(domain.str)==0){
            return 0;
        } else {
            return -1;
        }
    }

    public String getStr() {
        return str;
    }

    public static void main(String[] args) {
        Domain d1 = new Domain("c");
        Domain d2 = new Domain("c");
        Domain d3 = new Domain("b");
        Domain d4 = new Domain("d");
        System.out.println(d1.compareTo(d2));
        System.out.println(d1.compareTo(d3));
        System.out.println(d1.compareTo(d4));
    }
}
