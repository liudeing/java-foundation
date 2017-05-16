package jvm.OverOfMemory;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 本机直接内存溢出
 * 直接通过反射获取Unsafe实例进行内存分配
 * VM args:-Xmx20M -XX:MaxDirectMemorySize=10M
 * Created by liur on 17-5-5.
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        /**
         * Unsafe类的getUnsafe()方法限制了只有引导类加载器才会返回
         * 实例,也就是设计者希望只有rt.jar中的类才能使用Unsafe的功能
         */
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            //申请分配内存的方法
            unsafe.allocateMemory(_1MB);
        }
    }
}
