package concurrent.NIO.buffer;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIO通道读取文件
 * Created by liur on 17-5-15.
 */
public class FileChannelBuffer {
    public static void main(String[] args) {
        try {
            //读文件,取得文件的Channel
            FileInputStream fileInputStream = new FileInputStream(new File("D://file.txt"));
            FileChannel fileChannel = fileInputStream.getChannel();
            //从Channel中读取数据
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            fileChannel.read(byteBuffer);

            //此时文件已经在buffer中了，可以关闭通道了
            fileChannel.close();
            byteBuffer.flip();

            //之后就可以在byteBuffer中取得文件的内容
            String data = new String(byteBuffer.array());
            System.out.println(data);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件拷贝，展示NIO文件读写操作
     *
     * @param resouce
     * @param destination
     * @throws IOException
     */
    public static void nioCopyFile(String resouce, String destination) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(resouce);
        FileOutputStream fileOutputStream = new FileOutputStream(destination);

        FileChannel readChannel = fileInputStream.getChannel();
        FileChannel writeChannel = fileOutputStream.getChannel();

        //读入数据缓存
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            buffer.clear();
            int len = readChannel.read(buffer);
            if (len == -1) {
                break;  //读取完毕
            }
            buffer.flip();
            //写文件
            writeChannel.write(buffer);
        }

        readChannel.close();
        writeChannel.close();
    }
}
