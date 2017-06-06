package concurrent.NIO.buffer;

import java.nio.ByteBuffer;

/**
 * ByteBuffer原理探究(JDK为每一种原生类型都创建了一个Buffer;ByteBuffer,CharBuffer,DoubleBuffer,FloatBuffer,
 * IntBuffer,ShortBuffer,LongBuXffer)
 * Created by liur on 17-5-15.
 */
public class NIOBuffer {

    public static void main(String[] args) {
        //15个字节大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(15);
        System.out.println("limit=" + byteBuffer.limit() + " capacity=" + byteBuffer.capacity() + " position=" + byteBuffer.position());

        //存入10个字节的数据
        for (int i = 0; i < 10; i++) {
            byteBuffer.put((byte) i);
        }

        System.out.println("limit=" + byteBuffer.limit() + " capacity=" + byteBuffer.capacity() + " position=" + byteBuffer.position());

        //重置position
        byteBuffer.flip();
        System.out.println("limit=" + byteBuffer.limit() + " capacity=" + byteBuffer.capacity() + " position=" + byteBuffer.position());

        for (int i = 0; i < 5; i++) {
            System.out.println(byteBuffer.get());
        }

        System.out.println();

        System.out.println("limit=" + byteBuffer.limit() + " capacity=" + byteBuffer.capacity() + " position=" + byteBuffer.position());

        byteBuffer.flip();

        System.out.println("limit=" + byteBuffer.limit() + " capacity=" + byteBuffer.capacity() + " position=" + byteBuffer.position());

    }
}
