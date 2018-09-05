本笔记主要针对[JAVA NIO](https://book.douban.com/subject/1433583/)第1-4章,做一下总结,豆瓣评分7.5,但本人还是强烈推荐的.对JDK 1.4的NIO接口做了很充分的讲解.

### I/O概念

所谓“I(输入)/O(输出)”讲的无非就是把数据移进或移出缓冲区.
进程执行 I/O 操作，归结起来，也就是向操作系统发出请求，让它要么把缓冲区里的数据排干 (写)，要么用数据把缓冲区填满(读).

- 缓冲区操作

![read操作](https://segmentfault.com/img/bVTYnE?w=1084&h=348)

如上图所示,进程使用read( )系统调用，要求其缓冲区被填满。内核随即向磁盘控制硬件发出命令，要求其从磁盘读取数据。磁盘控制器把数据直接写入内核内存缓冲区，这一步通过 DMA 完成，无需主 CPU 协助。一旦磁盘控制器把缓冲区装满，内核即把数据从内核空间的临时缓冲区拷贝到进程执行read( )调用时指定的缓 冲区。

注意图中用户空间和内核空间的概念。用户空间是常规进程所在区域。JVM 就是常规进程,驻守于用户空间。用户空间是非特权区域:比如，在该区域执行的代码就不能直接访问硬件设备。 内核空间是操作系统所在区域。内核代码有特别的权力:它能与设备控制器通讯，控制着用户区域 进程的运行状态,等等。最重要的是,所有 I/O 都直接(如这里所述)或间接通过内核空间。
为什么不直接 让磁盘控制器把数据送到用户空间的缓冲区呢?

```
1.硬件通常不能直接访问 用户空间.
2.像磁盘这样基于块存储的硬件设备操作的是固定大小的数据块，而用户进程请 求的可能是任意大小的或非对齐的数据块。在数据往来于用户空间与存储设备的过程中，内核负责数据的分解、再组合工作，因此充当着中间人的角色
```

- 发散/汇聚
  ![发散/内聚](https://segmentfault.com/img/bVTYnU?w=1134&h=440)

如上图所示,发散/汇聚就是操作系统为了提升性能同时操纵多个缓存区的情况,这样就不用多次执行系统调用了.

- 虚拟内存
  虚拟内存意为使用虚假(或虚拟)地址取代物理(硬件RAM)内存地址

优点:

```
 1.一个以上的虚拟地址可指向同一个物理内存地址
 2.虚拟内存空间可大于实际可用的硬件内存
```

![内存空间多重映射](https://segmentfault.com/img/bVTYn3?w=990&h=496)
利用一个以上的虚拟地址可指向同一个物理内存地址把内核空间地址与用户空间的虚拟地址映射到同一个物理地址，这样， DMA 硬件(只能访问物理内存地址)就可以填充对内核与用户空间进程同时可见的缓冲区(上图)。
前提条件是，内核与用户缓冲区必须使用相同的页对齐，缓冲区的大小还必须是磁盘控制器块大小(通常为 512 字节磁盘扇区)的倍数。

- 内存页面调度
  为了支持虚拟内存的第二个特性(寻址空间大于物理内存)，就必须进行虚拟内存分页(经常称为交换，虽然真正的交换是在进程层面完成，而非页层面)。依照该方案，虚拟内存空间的页面能够继续存在于外部磁盘存储，这样就为物理内存中的其他虚拟页面腾出了空间。从本质上说，物理内存充当了分页区的高速缓存;而所谓分页区，即从物理内存置换出来，转而存储于磁盘上的内存页面.

MMU:现代 CPU 包含一个称为内存管理单元(MMU)的子系统，逻辑上位于 CPU 与物理内存之间。该设备包含虚拟地址向物理内存地址转换时所需映射信息。当 CPU 引用某内存地址时，MMU负责确定该地址所在页(往往通过对地址值进行移位或屏蔽位操作实现)，并将虚拟页号转换为物理页号(这一步由硬件完成，速度极快)。如果当前不存在与该虚拟页形成有效映射的物理内存页，MMU 会向CPU交一个页错误.

页错误随即产生一个陷阱(类似于系统调用)，把控制权移交给内核，附带导致错误的虚拟地址信息，然后内核采取步骤验证页的有效性。内核会安排页面调入操作，把缺失的页内容读回物理内存。这往往导致别的页被移出物理内存，好给新来的页让地方。在这种情况下，如果待移出的页已经被碰过了(自创建或上次页面调入以来，内容已发生改变)，还必须首先执行页面调出，把页内容拷贝到磁盘上的分页区。
如果所要求的地址不是有效的虚拟内存地址(不属于正在执行的进程的任何一个内存段)，则该页不能通过验证，段错误随即产生。于是，控制权转交给内核的另一部分，通常导致的结果就是进程被强令关闭。
一旦出错的页通过了验证，MMU随即更新，建立新的虚拟到物理的映射(如有必要，中断被移出页的映射)，用户进程得以继续。造成页错误的用户进程对此不会有丝毫察觉，一切都在不知 不觉中进行。

\#### 文件I/O

文件 I/O 属文件系统范畴，文件系统与磁盘迥然不同。磁盘把数据存在扇区上，通常一个扇区512字节。磁盘属硬件设备，对何谓文件一无所知，它只是 供了一系列数据存取窗口。在这点上，磁盘扇区与内存页颇有相似之处:都是统一大小，都可作为大的数组被访问。
文件系统是更高层次的抽象，是安排、解释磁盘(或其他随机存取块设备)数据的一种独特方式。您所写代码几乎无一例外地要与文件系统打交道，而不是直接与磁盘打交道。是文件系统定义了文件名、路径、文件、文件属性等抽象概念。

采用分页技术的操作系统执行 I/O 的全过程可总结为以下几步:

- 确定请求的数据分布在文件系统的哪些页(磁盘扇区组)。磁盘上的文件内容和元数据可能跨越多个文件系统页，而且这些页可能也不连续。
- 在内核空间分配足够数量的内存页，以容纳得到确定的文件系统页。
- 在内存页与磁盘上的文件系统页之间建立映射。
- 为每一个内存页产生页错误。
- 虚拟内存系统俘获页错误，安排页面调入，从磁盘上读取页内容，使页有效。
- 一旦页面调入操作完成，文件系统即对原始数据进行解析，取得所需文件内容或属性信息。

#### 内存映射文件

传统的文件I/O是通过用户进程发布read()和write()系统调用来传输数据的。为了在内核空间的文件系统页与用户空间的内存区之间移动数据，一次以上的拷贝操作几乎总是免不了的。这是因为，在文件系统页与用户缓冲区之间往往没有一一对应关系。但是，还有一种大多数操作系统都支持的特殊类型的 I/O 操作，允许用户进程最大限度地利用面向页的系统I/O特性，并完全摒弃缓冲区拷贝。这就是内存映射 I/O，如图下所示。

![内存映射文件](https://segmentfault.com/img/bVTIZ9?w=1200&h=488)

内存映射 I/O 使用文件系统建立从用户空间直到可用文件系统页的虚拟内存映射.
优点:

- 用户进程把文件数据当作内存，所以无需发布read()或write()系统调用。
- 当用户进程碰触到映射内存空间，页错误会自动产生，从而将文件数据从磁盘读进内存。如果用户修改了映射内存空间，相关页会自动标记为脏，随后刷新到磁盘，文件得到更新。
- 操作系统的虚拟内存子系统会对页进行智能高速缓存，自动根据系统负载进行内存管理。
- 数据总是按页对齐的，无需执行缓冲区拷贝。
- 大型文件使用映射，无需耗费大量内存，即可进行数据拷贝

### Buffer

![Buffer结构](https://segmentfault.com/img/bVTYok?w=1382&h=588)
Buffer对应于上节所述概念中的缓存区.包在一个对象内的基本数据元素数组。Buffer 类相比一个简单数组的优点是它将关于数据的数据内容和信息包含在一个单一的对象中。Buffer类以及它专有的子类定义了一个用于处理数据缓冲区的 API.有7种主要的缓冲区类，每一种都具有一种 Java 语言中的非布 类型的原始类型数据。

属性:

```
    //标记:一个备忘位置。调用mark( )来设定mark=postion。调用reset( )设定position= mark。标记在设定前是未定义的(undefined)
    private int mark = -1;
    //位置:下一个要被读或写的元素的索引。位置会自动由相应的get()和 put()函数更新。
    private int position = 0;
    //上界:缓冲区的第一个不能被读或写的元素。或者说，缓冲区中现存元素的计数。
    private int limit;
    //容量:缓冲区能够容纳的数据元素的最大数量。这一容量在缓冲区创建时被设定，并且 远不能被改变
    private int capacity;
```

上述属性有以下关系 
0 <= mark <= position <= limit <= capacity

基本方法:

```
    //返回容量
    public final int capacity();
    //返回位置
    public final int position();
    //设置容量
    public final Buffer position(int newPosition);
    //返回上届
    public final int limit() ;
    //标记当前position为mark
    public final Buffer mark();
    //重回mark位置
    public final Buffer reset();
    //一般在把数据写入Buffer前调用
    public final Buffer clear() {
        position = 0;
        limit = capacity;
        mark = -1;
        return this;
    }
    //翻转:将缓存字节数组的指针设置为数组的开始序列即数组下标0。这样就可以从buffer开头，对该buffer进行遍历（读取）了.
    //buf.put(magic);  Prepend header
    //in.read(buf);    Read data into rest of buffer
    //buf.flip();      Flip buffer
    //out.write(buf);  Write header + data to channel<
    public final Buffer flip() {
        limit = position;
        position = 0;
        mark = -1;
        return this;
    }
    // 一般在把数据重写入Buffer前调用
    // out.write(buf);    // Write remaining data
    // buf.rewind();      // Rewind buffer
    // buf.get(array);    // Copy data into array
    public final Buffer rewind() {
        position = 0;
        mark = -1;
        return this;
    }
    // limit - position
    public final int remaining();
    // position < limit
    public final boolean hasRemaining();
    public abstract boolean isReadOnly();
  
```

存取方法:

```
public abstract byte get( );
public abstract byte get (int index);
public abstract ByteBuffer put (byte b);
public abstract ByteBuffer put (int index, byte b);
```

压缩方法:

```
//将position到limit之间的数据迁移至0开始处,然后limit=capacity
public abstract ByteBuffer compact( );
```

比较方法

```
// true:两个对象类型相同,两个对象都剩余同样数量的元素,在每个缓冲区中应被Get()函数返回的剩余数据元素序列必须一致
public boolean equals (Object ob)
// 比较是针对每个缓冲区内剩余数据进行的，与它们在equals()中的方式相同，直到不相等的元素被发现或者到达缓冲区的上界。如果一个缓冲区在不相等元素发现前已经被耗尽，较短 的缓冲区被认为是小于较长的缓冲区
public int compareTo (Object ob)
```

#### 直接缓冲区

字节缓冲区跟其他缓冲区类型最明显的不同在于，它们可以成为通道所执行的 I/O 的源 头和/或目标,只有字节缓冲区有资格参与 I/O 操作。
直接缓冲区被用于与通道和固有I/O例程交互。它们通过使用固有代码来告知操作系统直接释放或填充内存区域，对用于通道直接或原始存取的内存区域中的字节元素的存储尽了最大的努力。
直接字节缓冲区通常是 I/O 操作最好的选择。在设计方面，它们支持 JVM 可用的最高效 I/O 机制。非直接字节缓冲区可以被传递给通道，但是这样可能导致性能耗。通常非直接缓冲不可能成为一个本地I/O操作的目标。如果您向一个通道中传递一个非直接ByteBuffer对象用于写入，通道可能会在每次调用中隐含地进行下面的操作:

- 创建一个临时的直接 ByteBuffer 对象

- 将非直接缓冲区的内容复制到临时缓冲区中。

- 使用临时缓冲区执行低层次 I/O 操作。

- 临时缓冲区对象离开作用域，并最终成为被回的无用数据。

  直接缓冲区使用的内存是通过调用本地操作系统方面的代码分配的， 过了标准JVM。建立和销毁直接缓冲区会明显比具有的缓冲区更加费，这取决于主操作系统以及JVM实现。直接缓冲区的内存区域不受无用存储单元 集支配，因为它们位于标准JVM之外。

  public static ByteBuffer allocateDirect (int capacity);

### Channel

基本接口

```
public interface Channel extends Closeable {
    public boolean isOpen();
    public void close() throws IOException;
}
```

#### FileChannel 文件通道

文件通道总是阻塞的,不能被置于非阻模式.现代操作系统都有复杂的缓存和预取机制，使得本地磁盘 I/O 操作延迟很少。ileChannel实例只能通过在一个 打开的file对象(RandomAccessFile、FileInputStream或FileOutputStream)上调用getChannel()方法获取.
FileChannel 对象是线程安全(thread-safe)的。多个进程可以在同一个实例上并发调用方法而 不会引起任何问题，不过并非所有的操作都是多线程的(multithreaded)。影响通道位置或者影响文件大小的操作都是单线程的(single-threaded)。如果有一个线程已经在执行会影响通道位置或文件大小的操作，那么其他试进行此类操作之一的线程必须等待。并发行为也会受到底层的操作系 统或文件系统影响。
同大多数 I/O 相关的类一样，FileChannel 是一个反映 Java 虚拟机外部一个具体对象的抽象。 FileChannel 类保证同一个 Java 虚拟机上的所有实例看到的某个文件的 图均是一致的，但是 Java 虚拟机却不能对超出它控制范围的因素 供 保。通过一个 FileChannel 实例看到的某个文件的 图同通过一个外部的非 Java 进程看到的该文件的 图可能一致，也可能不一致。多个进程发起的并发文件访问的语义高度取决于底层的操作系统和(或)文件系统。一般而言，由运行在不同 Java 虚拟机上的 FileChannel 对象发起的对某个文件的并发访问和由非Java进程发起的对该文件的并发 访问是一致的。

##### 内存映射文件

新的 FileChannel 类供了一个名为map()的方法，该方法可以在一个打开的文件和一个特殊类型的ByteBuffer之间建立一个虚拟内存映射(第一章中已经归纳了什么是内存映射文件以及它们 如何同虚拟内存交互)。在FileChannel 上调用 map()方法会创建一个由磁盘文件支持的虚拟内存映射(virtual memory mapping)并在那块虚拟内存空间外部封装一个 MappedByteBuffer 对象.
由 map()方法返回的MappedByteBuffer对象的行为在多数方面类似一个基于内存的缓冲区，只不过该对象的数据元素存储在磁盘上的一个文件中。调用 get()方法会从磁盘文件中获取数据，此数据反映该文件的当前内容，即使在映射建立之后文件已经被一个外部进程做了修改。通过文件映射看到的数据同您用常规方法读取文件看到的内容是完全一样的。相似地，对映射的缓冲区实现一个put()会更新磁盘上的那个文件(假设对该文件您有写的权限)，并且您做的修改对于该文件的其他阅读者也是可见的。

```
通过内存映射机制来访问一个文件会比使用常规方法读写高效得多，甚至比使用通道的效率都高。因为不需要做明确的系统调用，那会很消耗时间。更重要的是，操作系统的虚拟内存可以自动 缓存内存页(memory page)。这些页是用系统内存来缓存的，所以不会消耗 Java 虚拟机内存  (memory heap).
一旦一个内存页已经生效(从磁盘上缓存进来)，它就能以完全的硬件速度再次被访问而不需 要再次调用系统命令来获取数据。那些包含索引以及其他需频繁引用或更新的内容的巨大而结构化文件能因内存映射机制受益非常多。如果同时结合文件锁定来保护关键区域和控制事务原子性，那您将能了解到内存映射缓冲区如何可以被很好地利用。
MemoryMappedBuffer 直接反映它所关联的磁盘文件。如果映射有效时文件被在结构上修改， 就会产生奇 的行为(当然具体的行为是取决于操作系统和文件系统的)。MemoryMappedBuffer 有固定的大小，不过它所映射的文件却是弹性的。具体来说，如果映射有效时文件大小变化了，那么缓冲区的部分或全部内容都可能无法访问，并将返回未定义的数据或者抛出未检查的异常。关于被内存映射的文件如何受其他线程或外部进程控制这一点，请务必小心对待。
所有的 MappedByteBuffer对象都是直接的，这意味着它们占用的内存空间位于 Java虚拟机内存之外(并且可能不会算作Java虚拟机的内存占用，不过这取决于操作系统的虚拟内存模型)。
因为 MappedByteBuffers 也是 ByteBuffers，所以能够被传递 SocketChannel 之类通道的read()或write()以有效传输数据给被映射的文件或从被映射的文件读取数据。
    public abstract class MappedByteBuffer
    extends ByteBuffer
    {
    // 加载文件到物理内存
    public final MappedByteBuffer load( )
    //是否已加载
    public final boolean isLoaded( )
    //将缓存区的更改写入磁盘
    public final MappedByteBuffer force( )
    }
```

当我们为一个文件建立虚拟内存映射之后，文件数据通常不会因此被从磁盘读取到内存(这取 决于操作系统)。该过程类似打开一个文件:文件先被定位，然后一个文件句柄会被创建，当您准备好之后就可以通过这个句来访问文件数据。对于映射缓冲区，虚拟内存系统将根据您的需要来把文件中相应区块的数据读进来。这个页验证或防错过程需要一定的时间，因为将文件数据读取到 内存需要一次或多次的磁盘访问。某些场下，您可能想先把所有的页都读进内存以实现最小的缓冲区访问延迟。如果文件的所有页都是常驻内存的，那么它的访问速度就和访问一个基于内存的缓冲区一样了。
load()方法会加载整个文件以使它常驻内存。正如我们在第一章所讨论的，一个内存映射缓冲区会建立与某个文件的虚拟内存映射。此映射使得操作系统的底层虚拟内存子系统可以根据需要将文件中相应区块的数据读进内存。已经在内存中或通过验证的页会占用实际内存空间，并且在它们 被读进 RAM 时会挤出最近较少使用的其他内存页。

#### SocketChannel TCP客户端

```
public class ChannelAccept
{
    public static final String GREETING = "Hello I must be going.\r\n";
    public static void main (String [] argv)
        throws Exception
    {
        int port = 1234; // default
        if (argv.length > 0) {
            port = Integer.parseInt (argv [0]);
        }
        ByteBuffer buffer = ByteBuffer.wrap (GREETING.getBytes( ));
        ServerSocketChannel ssc = ServerSocketChannel.open( );
        ssc.socket( ).bind (new InetSocketAddress (port));
        ssc.configureBlocking (false);
        while (true) {
            System.out.println ("Waiting for connections");
            SocketChannel sc = ssc.accept( );
            if (sc == null) {
                // no connections, snooze a while
                Thread.sleep (2000);
            } else {
                System.out.println ("Incoming connection from: "
                   + sc.socket().getRemoteSocketAddress( ));
                buffer.rewind( );
                sc.write (buffer);
                sc.close( );
    } 
}
```

#### ServerSocketChannel TCP服务器端

```
public class ConnectAsync
{
public static void main (String [] argv) throws Exception
{
String host = "localhost";
int port = 80;
if (argv.length == 2) {
host = argv [0];
port = Integer.parseInt (argv [1]);
}
InetSocketAddress addr = new InetSocketAddress (host, port);
SocketChannel sc = SocketChannel.open( );
sc.configureBlocking (false);
System.out.println ("initiating connection");
sc.connect (addr);
while ( ! sc.finishConnect( )) {
doSomethingUseful( );
}
System.out.println ("connection established");
// Do something with the connected socket
// The SocketChannel is still nonblocking
sc.close( );
}
private static void doSomethingUseful( )
{
System.out.println ("doing something useless");
}
}
```

#### DatagramChannel UDP

```
public class TimeClient {
    private static final int DEFAULT_TIME_PORT = 37;
    private static final long DIFF_1900 = 2208988800L;
    protected int port = DEFAULT_TIME_PORT;
    protected List remoteHosts;
    protected DatagramChannel channel;

    public TimeClient(String[] argv) throws Exception {
        if (argv.length == 0) {
            throw new Exception("Usage: [ -p port ] host ...");
        }
        parseArgs(argv);
        this.channel = DatagramChannel.open();
    }

    protected InetSocketAddress receivePacket(DatagramChannel channel,
        ByteBuffer buffer)
        throws Exception {
        buffer.clear();
        // Receive an unsigned 32-bit, big-endian value
        return ((InetSocketAddress) channel.receive(buffer));
    }

    // Send time requests to all the supplied hosts
    protected void sendRequests()
        throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(1);
        Iterator it = remoteHosts.iterator();
        while (it.hasNext()) {
            InetSocketAddress sa = (InetSocketAddress) it.next();
            System.out.println("Requesting time from "
                + sa.getHostName() + ":" + sa.getPort());
            // Make it empty (see RFC868)
            buffer.clear().flip();
            // Fire and forget
            channel.send(buffer, sa);
            112
        }
    }

    // Receive any replies that arrive
    public void getReplies() throws Exception {
        // Allocate a buffer to hold a long value
        ByteBuffer longBuffer = ByteBuffer.allocate(8);
        // Assure big-endian (network) byte order
        longBuffer.order(ByteOrder.BIG_ENDIAN);
        // Zero the whole buffer to be sure
        longBuffer.putLong(0, 0);
        // Position to first byte of the low-order 32 bits
        longBuffer.position(4);
        // Slice the buffer; gives view of the low-order 32 bits
        ByteBuffer buffer = longBuffer.slice();
        int expect = remoteHosts.size();
        int replies = 0;
        System.out.println("");
        System.out.println("Waiting for replies...");
        while (true) {
            InetSocketAddress sa;
            sa = receivePacket(channel, buffer);
            buffer.flip();
            replies++;
            printTime(longBuffer.getLong(0), sa);
            if (replies == expect) {
                System.out.println("All packets answered");
                break;
            }
            // Some replies haven't shown up yet
            System.out.println("Received " + replies
                + " of " + expect + " replies");
        }
    }

    // Print info about a received time reply
    protected void printTime(long remote1900, InetSocketAddress sa) {
        // local time as seconds since Jan 1, 1970
        113
        long local = System.currentTimeMillis() / 1000;
        // remote time as seconds since Jan 1, 1970
        long remote = remote1900 - DIFF_1900;
        Date remoteDate = new Date(remote * 1000);
        Date localDate = new Date(local * 1000);
        long skew = remote - local;
        System.out.println("Reply from "
            + sa.getHostName() + ":" + sa.getPort());
        System.out.println(" there: " + remoteDate);
        System.out.println(" here: " + localDate);
        System.out.print(" skew: ");
        if (skew == 0) {
            System.out.println("none");
        } else if (skew > 0) {
            System.out.println(skew + " seconds ahead");
        } else {
            System.out.println((-skew) + " seconds behind");
        }
    }

    protected void parseArgs(String[] argv) {
        remoteHosts = new LinkedList();
        for (int i = 0; i < argv.length; i++) {
            String arg = argv[i];
            // Send client requests to the given port
            if (arg.equals("-p")) {
                i++;
                this.port = Integer.parseInt(argv[i]);
                continue;
            }
            // Create an address object for the hostname
            InetSocketAddress sa = new InetSocketAddress(arg, port);
            // Validate that it has an address
            if (sa.getAddress() == null) {
                System.out.println("Cannot resolve address: "
                    + arg);
                continue;
            }
            114
            remoteHosts.add(sa);
        }
    }


    public static void main(String[] argv)
        throws Exception {
        TimeClient client = new TimeClient(argv);
        client.sendRequests();
        client.getReplies();
    }
}
```

### Selector

选择器供选择执行已经就绪的任务的能力，这使得多元I/O成为可能。就绪选择和多元执行使得单线程能够有效率地同时管理多个I/O通道(channels).将之前创建的一个或多个可选择的通道注册到选择器对象中。一个表示通道和选择器的键将会被返回。选择键会记您关心的通道。它们也会对应的通道是否已经就绪。当您调用一个选择器对象的 select()方法时，相关的键建会被更新， 用来检查所有被注册到该选择器的通道。
在与 SelectableChannel联合使用时，选择器供了这种服务，但这里面有更多的事情需要去了解。就绪选择的真正价值在于潜在的大量的通道可以同时进行就绪状态的检查。调用者可以轻松地决定多个通道中的哪一个准备好要运行。有两种方式可以选择:被激发的线程可以处于休眠状态，直到一个或者多个注册到选择器的通道就绪，或者它也可以周期性地询选择器，看看从上次检查之后，是否有通道处于就绪状态。如果您考虑一下需要管理大量并发的连接的网络服务器(webserver)的实现，就可以很容易地想到如何善加利用这些能力。
真正的就绪选择必须由操作系统来做。操作系统的一项最重要的功能就是处理 I/O 请求并通知 各个线程它们的数据已经准备好了。选择器类供了这种抽象，使得 Java 代码能够以可移植的方式，请求底层的操作系统供就绪选择服务

Selector:

- 选择器类管理着一个被注册的通道集合的信息和它们的就绪状态。通道是和选择器一起被注册的，并且使用选择器来更新通道的就绪状态。当这么做的时候，可以选择将被激发的线程挂起，直到有就绪的的通道。

```
 public abstract class Selector implements Closeable {

    protected Selector() { }
    //实例化Selector
    public static Selector open() throws IOException {
        return SelectorProvider.provider().openSelector();
    }

    public abstract SelectorProvider provider();
    //返回 注册到它们之上的通道的集合,不可以直接修改的
    public abstract Set<SelectionKey> keys();
    //返回 就绪的键.已注册的键的集合的子集,这个集合的每个成员都是相关的通道被选择器(在前一个选择操作中)断为已经准备好的，并且包含于键的interest集合中的操作/
    public abstract Set<SelectionKey> selectedKeys();
    //非阻塞
    public abstract int selectNow() throws IOException;
    //设定时间内阻塞
    public abstract int select(long timeout) throws IOException;
    //完全阻塞
    public abstract int select() throws IOException;
    //停止阻塞中的select方法
    public abstract Selector wakeup();
    //测试一个选择器是否处于被打开的状态
    public abstract boolean isOpen();
    //释放它可能占用的资源并将所有相关的选择键设置为无效,一个在选择操作中阻 的线程都将被醒，就像wakeup()方法被调用了一样。与选择器相关的通道将被注销，而键将被取消
    public abstract void close() throws IOException;
}
```

select()方法:

```
1.  已取消的键的集合将会被检查。如果它是非空的，每个已取消的键的集合中的键将从另外两个集合中移除，并且相关的通道将被注销。这个步骤结束后，已取消的键的集合将是空的。

2.  已注册的键的集合中的键的interest集合将被检查。在这个步骤中的检查执行过后，对interest集合的改动不会影响剩余的检查过程。

    一旦就绪条件被定下来，底层操作系统将会进行查询，以确定每个通道所关心的操作的真实就绪状态。依赖于特定的select()方法调用，如果没有通道已经准备好，线程可能会在这时阻塞，通常会有一个超时值。

    直到系统调用完成为止，这个过程可能会使得调用线程一段时间，然后当前每个通道的就绪状态将确定下来。对于那些还没准备好的通道将不会执行任何的操作。对于那些操作系统指示至少已经准备好interest集合中的一种操作的通道，将执行以下两种操作中的一种:

- 如果通道的键还没有处于已选择的键的集合中，那么键的 ready 集合将被清空，然后表示操 作系统发现的当前通道已经准备好的操作的比特 码将被设置。
- 否则，也就是键在已选择的键的集合中。键的ready集合将被表示操作系统发现的当前已经准备好的操作的比特码更新。所有之前的已经不再是就绪状态的操作不会被清除。事实上，所有的比特位都不会被清理。由操作系统决定的 ready 集合是与之前的ready集合按位分离的，一旦键被放置于选择器的已选择的键的集合中，它的ready集合将是的。比特位只会被设置，不会被清理。
    
3.  步骤2可能会花费很长时间，特别是所激发的线程处于休眠状态时。与该选择器相关的键可能会同时被取消。当步骤2结束时，步骤1将重新执行，以完成任意一个在选择进行的过程中，键已经被取消的通道的注销

4.  select操作返回的值是ready集合在步骤2中被修改的键的数量，而不是已选择的键的集合中的通道的总数。返回值不是已准备好的通道的总数，而是从上一个 select()调用之后进入就绪状态的通道的数量。之前的调用中就绪的，并且在本次调用中仍然就绪的通道不会被计入，而那些在前一次调用中已经就绪但已经不再处于就绪状态的通道也不会被计入。这些通道可能仍然在已选择的 键的集合中，但不会被计入返回值中。返回值可能是 0。
```

选择是累积的。一旦一个选择器将一个键加到它的已选择的键的集合中，它就不会移除这个键。并且，一旦一个键处于已选择的键的集合中，这个键的ready集合将只会被设置，而不会被清理。
合理地使用选择器的是理解选择器维护的选择键集合所演的角色。最重要的部分是当键已经不再在已选择的键的集合中时将会发生什么。当通道上的至少一个感兴趣的操作就绪时,键的ready集合就会被清空，并且当前已经就绪的操作将会被加到ready集合中。该键之后将被 加到已选择的键的集合中。
清理一个 SelectKey的ready集合的方式是将这个键从已选择的键的集合中移除(removed掉)。选择键的就绪状态只有在选择器对象在选择操作过程中才会修改。处理思想是只有在已选择的键的集合中的键才被认为是包含了合法的就绪信息的。这些信息将在键中长久地存在，直到键从已选择的键的集合中移除，以通知选择器您已经看到并对它进行了处理。如果下一次通道的一些感兴趣的操作发生时，键将被重新设置以反映当时通道的状态并再次被 加到已选择的键的集合中。

SelectableChannel:

- FileChannel对象不是可选择的,SocketChannel,SocketServerChannel,DatagramChannel是可选择的.

```
public abstract class SelectableChannel extends AbstractChannel implements Channel{
        // 注册通道到Selector上,第二个参数表示所关心的通道操作
        public abstract SelectionKey register (Selector sel, int ops) throws ClosedChannelException;
        // 同上,第三个参数attach到SelectionKey
        public abstract SelectionKey register (Selector sel, int ops,Object att)
        throws ClosedChannelException;
        public abstract boolean isRegistered( );
        // 返回与该通道和指定的选择器相关的键
        public abstract SelectionKey keyFor (Selector sel);
        public abstract int validOps( );
        //通道在被注册到一个选择器上之前，必须先设置为false,否则会抛出IllegalBlockingModeException
        public abstract void configureBlocking (boolean block)
        throws IOException;
        public abstract boolean isBlocking( );
        public abstract Object blockingLock( );
}
```

SelectionKey:

- 选择键封装了特定的通道与特定的选择器的注册关系

```
public abstract class SelectionKey{
        public static final int OP_READ
        public static final int OP_WRITE
        public static final int OP_CONNECT
        public static final int OP_ACCEPT
        //返回相关SelectableChannel
        public abstract SelectableChannel channel( );
        //返回相关 Selector
        public abstract Selector selector( );
        //不会立即注销。直到下一次操作发生为止，它们仍然会处于被注册的状态.
        public abstract void cancel( );
        //是否仍然有效
        public abstract boolean isValid( );
        //返回 通道/ 选择器组合体所关心的操作(instrest 集合)
        public abstract int interestOps( );
        //修改 instrest 集合
        public abstract void interestOps (int ops);
        // 通道准备好要执行的操作,ready集合是interest集合的子集，并且表示了interest集合中从上次调用select()以来已经就绪的那些操作
        //通过相关的选择键的readyOps()方法返回的就绪状态指示只是一个 示，不是保证。底层的通道在任何时候都会不断改变。其他线程可能在通道上执行操作并影响它的就绪状态。
        public abstract int readyOps( );
        //是否可读
        public final boolean isReadable( )
        //是否可写
        public final boolean isWritable( )
        //是否可连接
        public final boolean isConnectable( )
        //是否可接受连接
        public final boolean isAcceptable( )
        //附上对象
        public final Object attach (Object ob)
        //返回 附着的对象
        public final Object attachment( )
}
```

1.建立选择器

```
    Selector selector = Selector.open( );
    channel1.register (selector, SelectionKey.OP_READ);
    channel2.register (selector, SelectionKey.OP_WRITE);
    channel3.register (selector, SelectionKey.OP_READ |SelectionKey.OP_WRITE);
   // Wait up to 10 seconds for a channel to become ready
   readyCount = selector.select (10000);
```

上述代码,建了一个新的选择器，然后将这三个(已经存在的)socket 通道注册到选择器上，而且感兴趣的操作各不相同

2.完整的交互例子



```
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class SelectSockets {
    public static int PORT_NUMBER = 1234;

    public static void main(String[] argv) throws Exception {
        new SelectSockets().go(argv);
    }

    public void go(String[] argv) throws Exception {
        int port = PORT_NUMBER;
        if (argv.length > 0) { // Override default listen port
            port = Integer.parseInt(argv[0]);
        }
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverChannel.socket();
        Selector selector = Selector.open();
        serverSocket.bind(new InetSocketAddress(port));
        serverChannel.configureBlocking(false);
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {

            int n = selector.select();
            if (n == 0) {
                continue;
            }
            Iterator it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();
                // Is a new connection coming in?
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel channel = server.accept();
                    registerChannel(selector, channel, SelectionKey.OP_READ);
                    sayHello(channel);
                }
            }
        }
    }

    protected void registerChannel(Selector selector,
        SelectableChannel channel, int ops) throws Exception {
        if (channel == null) {
            return;
        }
        channel.configureBlocking(false);
        channel.register(selector, ops);
    }

    private ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

    protected void readDataFromSocket(SelectionKey key) throws Exception {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        int count;
        buffer.clear(); // Empty buffer
        while ((count = socketChannel.read(buffer)) > 0) {
            buffer.flip(); // Make buffer readable
            while (buffer.hasRemaining()) {
                socketChannel.write(buffer);
            }
            buffer.clear(); // Empty buffer
        }
        if (count < 0) {
            socketChannel.close();
        }
    }

    private void sayHello(SocketChannel channel) throws Exception {
        buffer.clear();
        buffer.put("Hi there!\r\n".getBytes());
        buffer.flip();
        channel.write(buffer);
    }
}
```