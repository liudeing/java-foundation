package concurrent.Condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 假定有一个有界缓冲区，它支持put()和take()方法。如果试图在缓冲区，如果执行take操作，则在某一项变得可用之前
 * 将一直阻塞，如果执行put操作，则在空间变得可用之前一直阻塞。我们喜欢在单独的set中保存put各take操作，这样在
 * 缓冲区中的项或空间变得可用之前，一次只通知一个线程。
 * 其实就是java.util.main.java.concurrent.ArrayBlockingQueue的功能
 * Created by liur on 17-5-13.
 */
public class BoundedBuffer {
    //锁对象
    final Lock lock = new ReentrantLock();
    //写线程锁
    final Condition notFull = lock.newCondition();
    //读线程锁
    final Condition notEmpty = lock.newCondition();

    //缓存队列
    final Object[] items = new Object[100];
    //写索引
    int putptr;
    //读索引
    int takeptr;
    //队列中的数据数目
    int count;

    public void put(Object x) {
        //锁定
        lock.lock();

        try {
            //如果队列满，则阻塞写
            while (count == items.length) {
                notFull.await();
            }
            //写入队列并更新索引
            items[putptr] = x;
            if (++putptr == items.length) putptr = 0;
            ++count;
            //唤醒读线程
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //解除锁定
            lock.unlock();
        }

    }

    public Object take() {
        lock.lock();
        try {
            //如果队列为空，则阻塞
            while (count == 0) {
                notEmpty.await();
            }
            //读取队列并更新读索引
            Object x = items[takeptr];
            if (++takeptr == items.length) takeptr = 0;
            --count;

            //唤醒写线程
            notFull.signal();
            return x;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }
}
