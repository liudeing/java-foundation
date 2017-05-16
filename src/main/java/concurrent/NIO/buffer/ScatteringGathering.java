package concurrent.NIO.buffer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Buffer的创建可以通过两种方式。使用静态方法allocate()从堆中分配缓冲区，或者从一个既有数组中创建缓冲区。</p>
 * 散射和聚集处理结构化数据
 * Created by liur on 17-5-15.
 */
public class ScatteringGathering {
    public static void main(String[] args) throws IOException {
        ByteBuffer bookBuf = ByteBuffer.wrap("实战Java虚拟机".getBytes("utf-8"));
        ByteBuffer authBuf = ByteBuffer.wrap("各一名".getBytes("utf-8"));

        int booklen = bookBuf.limit();
        int authlen = authBuf.limit();

        ByteBuffer[] buffers = new ByteBuffer[]{bookBuf, authBuf};
        File file = new File("D://file.txt");
        if (!file.exists()) file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file);

        FileChannel fc = fos.getChannel();
        fc.write(buffers);//聚集写文件
        fos.close();
    }
}
