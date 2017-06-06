package multithreadcore.pipestream;

import java.io.PipedInputStream;

/**
 * Created by liur on 17-5-25.
 */
public class ThreadRead extends Thread {
    private ReadData readData;
    private PipedInputStream input;
    public ThreadRead(ReadData readData,PipedInputStream input){
        super();
        this.readData=readData;
        this.input=input;
    }

    @Override
    public void run() {
        readData.readMethod(input);
    }
}
