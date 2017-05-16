package core;

/**
 * intern 这个方法返回的是 返回字符串对象的规范化表示形式，当调用 intern 方法时，如果池已经包含一个等于此 String
 * 对象的字符串（该对象由 equals(Object) 方法确定），则返回池中的字符串。否则，将此 String 对象添加到池中，并且返回此 String 对象的引用
 * Created by liur on 17-5-14.
 */
public class StringIntern {
    public static void main(String[] args) {
        String a = "A";
        String b = "A";
        System.out.println(a == b);
        String c = "B";
        String d = new String("B").intern();
        System.out.println(c == d);


        String s1 = "ab123" ;
        String s2 = new String( "ab123" ) ;
        System.out.println( s1 == s2 );
        String s3 = s2.intern() ;
        System.out.println( s1 == s3 ) ;
    }
}
