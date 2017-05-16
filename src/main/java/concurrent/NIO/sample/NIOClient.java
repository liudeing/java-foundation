package concurrent.NIO.sample;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * NIO客户端
 * Created by liur on 17-5-14.
 */
public class NIOClient {
    //通道管理器
    private Selector selector;

    public void initClient(String ip,int port) throws IOException {
        //获得一个通道
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        //获得一个通道管理器
        this.selector=Selector.open();
        socketChannel.connect(new InetSocketAddress(ip,port));
        //绑定通道，注册事件
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void listen() throws IOException {
        //轮询
        while (true){
            selector.select();
            Iterator iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = (SelectionKey) iterator.next();
                iterator.remove();
                //如果连接事件已发生
                if (selectionKey.isConnectable()){
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    //如果正在连接，则完成连接
                    if (channel.isConnectionPending()){
                        channel.finishConnect();
                    }
                    channel.configureBlocking(false);
                    channel.write(ByteBuffer.wrap(new String("向服务端发消息！").getBytes()));
                    //在各服务端连接成功后，为了可以接收到服务端的消息，需要给通道设置读的权限
                    channel.register(selector,SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()){
                    //获得了可读事件
                    read(selectionKey);
                }
            }
        }
    }

    public void read(SelectionKey key) throws IOException {
        //获得可读的通道
        SocketChannel channel = (SocketChannel) key.channel();
        //创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(10);
        channel.read(buffer);
        byte[] data = buffer.array();
        String msg = new String(data).trim();
        System.out.println("客户端收到的消息："+msg);
        ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
        channel.write(outBuffer);

    }

    public static void main(String[] args) throws IOException {
        NIOClient client = new NIOClient();
        client.initClient("localhost",8000);
        client.listen();

    }
}
