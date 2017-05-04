package Structural.Flyweight.CD;

/**
 * Flyweight模式在XML等数据源中读取了三张唱片。
 * 当有很多CD时，这样可以节少空间
 * Created by liur on 17-4-30.
 */
public class Test {
    public static void main(String[] args) {
        ArtistFactory factory= new ArtistFactory();

        Artist artist1= factory.getArtist("张学友");
        CD cd1 = new CD();

        //数据源。。。
        cd1.setTitle("吻别");
        cd1.setArtist(artist1);
        cd1.setYear(1997);

        System.out.println(cd1);
    }
}
