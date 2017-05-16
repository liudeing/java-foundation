package concurrent.NIO;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用NIO来实现多线程服务器
 * Created by liur on 17-5-3.
 */
public class NmultiThreadEchoServer {
    private Selector selector;
    private ExecutorService tp = Executors.newCachedThreadPool();
    //统计服务端在一个线程上花了多少时间
    public static Map<Socket, Long> time_stat = new HashMap<Socket, Long>();

    private void startServer() throws IOException {
        selector = SelectorProvider.provider().openSelector();
        //获得服务端的SokectChannel实例
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //设置为非阻塞方式
        ssc.configureBlocking(false);


        InetSocketAddress isa = new InetSocketAddress(InetAddress.getLocalHost(), 8000);
//        InetSocketAddress isa = new InetSocketAddress(8000);
        ssc.socket().bind(isa);

        //将ServerSocketChannel绑定到Selector上，并注册它感兴趣的时间为accept
        SelectionKey acceptKey = ssc.register(selector, SelectionKey.OP_ACCEPT);

        //死循环，处理等待-分发网络消息
        for (; ; ) {
            //阻塞方法
            selector.select();
            Set readKeys = selector.selectedKeys();
            Iterator i = readKeys.iterator();
            long e = 0;
            while (i.hasNext()) {
                SelectionKey sk = (SelectionKey) i.next();
                i.remove();

                if (sk.isAcceptable()) {
                    i.remove();
                } else if (sk.isValid() && sk.isReadable()) {
                    if (!time_stat.containsKey(((SocketChannel) sk.channel()).socket())) {
                        time_stat.put(((SocketChannel) sk.channel()).socket(), System.currentTimeMillis());
                        doRead(sk);
                    }
                } else if (sk.isValid() && sk.isWritable()) {
                    doWrite(sk);
                    e = System.currentTimeMillis();
                    long b = time_stat.remove(((SocketChannel) sk.channel()).socket());
                    System.out.println("spend:" + (e - b) + "ms");
                }
            }
        }
    }

    public void doAccept(){

    }

    public void doWrite(SelectionKey sk) {
        SocketChannel channel = (SocketChannel) sk.channel();
        //准备8k缓冲区读到数据
        ByteBuffer bb = ByteBuffer.allocate(8192);
        int len;
        try {
            len = channel.read(bb);
            if (len < 0) {
                disconnect(sk);
                return;
            }
        } catch (IOException e) {
            System.out.println("Failed to read from client.");
            e.printStackTrace();
            disconnect(sk);
            return;
        }
        bb.flip();
        tp.execute(new HandleMsg(sk, bb));
    }

    private void disconnect(SelectionKey sk) {
    }

    class HandleMsg implements Runnable {
        SelectionKey sk;
        ByteBuffer bb;

        public HandleMsg(SelectionKey sk, ByteBuffer bb) {
            this.sk = sk;
            this.bb = bb;
        }

        @Override
        public void run() {
            NechoClient nechoClient = (NechoClient) sk.attachment();
            //将收到的消息压入队列
            nechoClient.enQueue(bb);
            sk.interestOps(SelectionKey.OP_READ|SelectionKey.OP_WRITE);
            //强迫selector返回
            selector.wakeup();
        }
    }

    class NechoClient {
        private LinkedList<ByteBuffer> outq;

        public NechoClient() {
            outq = new LinkedList<ByteBuffer>();
        }

        public LinkedList<ByteBuffer> getOutputQueue() {
            return outq;
        }

        public void enQueue(ByteBuffer bb) {
            outq.addFirst(bb);
        }
    }

    public void doRead(SelectionKey sk) {

    }
}
