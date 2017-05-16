package core;

import java.io.IOException;

/**
 * Created by liur on 17-5-4.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        //
        int type=4;
        switch (type) {
            default:
                System.out.println(4);
            case 1:
                System.out.println(1);
            case 2:
                System.out.println(2);
            case 3:
                System.out.println(3);
        }

        //
        try{
            throw new Exception("1");
        }catch (IOException e){
            throw new Exception("2");
        }catch (Exception e) {
            throw new Exception("3");
        }finally {
            throw new Exception("4");
        }
    }

}
