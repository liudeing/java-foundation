package multithreadcore.pipestream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 管道流进行线程通信
 * Created by liur on 17-5-25.
 */
public class Run {
    public static void main(String[] args) {
        try {
            WriteData writeData = new WriteData();
            ReadData readData = new ReadData();
            PipedInputStream inputStream = new PipedInputStream();
            PipedOutputStream outputStream = new PipedOutputStream();

//            inputStream.connect(outputStream);
            outputStream.connect(inputStream);

            ThreadRead threadRead = new ThreadRead(readData, inputStream);
            //读线程启动
            threadRead.start();
            Thread.sleep(2000);
            ThreadWrite threadWrite = new ThreadWrite(writeData, outputStream);
            threadWrite.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
