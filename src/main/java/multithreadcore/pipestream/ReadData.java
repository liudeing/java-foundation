package multithreadcore.pipestream;

import java.io.IOException;
import java.io.PipedInputStream;

/**
 * Created by liur on 17-5-25.
 */
public class ReadData {
    public void readMethod(PipedInputStream input){
        try {
            System.out.println("read:");
            byte[] byteArray = new byte[20];
            int readLength = input.read(byteArray);
            while (readLength!=-1){
                String newData=new String(byteArray,0,readLength);
                    System.out.print(newData);
                //阻塞，直到有数据才写入
                readLength=input.read(byteArray);
            }
            System.out.println();
            input.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
