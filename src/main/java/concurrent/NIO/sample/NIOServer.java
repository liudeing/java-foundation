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
 * NIO服务器
 * Created by liur on 17-5-14.
 */
public class NIOServer {
    //通道管理器
    private Selector selector;

    /**
     * 获得一个ServerSocket通道，并对该通道做一些初始化的工作
     * @param port
     * @throws IOException
     */
    public void initServer(int port) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //配置为不阻塞
        serverSocketChannel.configureBlocking(false);
        //将该通道对应的ServerSocket绑定到port
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        //获得一个通道管理器
        this.selector=Selector.open();
        //将该通道管理器和该通道绑定，并为该通道注册SelectKey.OP_ACCEPT事件，注册事件后
        //当事件到达时，selector.select()会返回，如果事件一直没有到达selector.select()会一直阻塞
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    /**
     * 采用轮询的方式循环监听selector上是否有需要处理的事件，如果有，则进行处理
     * @throws IOException
     */
    public void listen() throws IOException {
        System.out.println("服务端启动成功！");
        while (true){
            selector.select();

            //获得selector选中项的迭代器，选中的项为注册的事件
            Iterator iterator = this.selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = (SelectionKey) iterator.next();
                //删除已选中的项，避免重复
                iterator.remove();
                //如果客户端请求连接事件
                if (selectionKey.isAcceptable()){
                    ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                    //获得客户端的连接通道
                    SocketChannel channel = server.accept();
                    //设置成非阻塞
                    channel.configureBlocking(false);
                    //向客户端发送一条信息
                    channel.write(ByteBuffer.wrap(new String("向客户端发送一条消息！").getBytes()));
                    //在与客户端连接成功之后，为了可以接收到客户端的信息，需要给客户端设置读的权限
                    channel.register(this.selector,SelectionKey.OP_ACCEPT);
                } else if (selectionKey.isReadable()) { //获得了可读的事件
                    read(selectionKey);
                }
            }
        }
    }

    /**
     * 处理客户端发送过来的消息
     * @param key
     * @throws IOException
     */
    public void read(SelectionKey key) throws IOException {
        //服务器端可读的消息：得到事件发生的通道
        SocketChannel channel = (SocketChannel) key.channel();
        //创建读取的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        channel.read(byteBuffer);
        byte[] data = byteBuffer.array();
        String msg = new String(data).trim();
        System.out.println("服务端收到消息："+msg);
        ByteBuffer  outBuffer = ByteBuffer.wrap(msg.getBytes());
        //将消息回送给客户端
        channel.write(outBuffer);
    }

    /**
     * 启动服务端
     */
    public static void main(String[] args) throws IOException {
        NIOServer server = new NIOServer();
        server.initServer(8000);
        server.listen();
    }
}
