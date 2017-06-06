package multithreadcore.pipestream;

import java.io.PipedOutputStream;

/**
 * Created by liur on 17-5-25.
 */
public class ThreadWrite extends Thread{
    private WriteData writeData;
    private PipedOutputStream out;
    public ThreadWrite(WriteData writeData,PipedOutputStream out){
        super();
        this.writeData=writeData;
        this.out=out;
    }

    @Override
    public void run() {
        writeData.writeMethod(out);
    }
}
