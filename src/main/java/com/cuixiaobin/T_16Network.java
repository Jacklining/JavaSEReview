package com.cuixiaobin;

import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class T_16Network {

    //补码与原码转换的方法：
    public void transfer(byte address) {
        byte value = -127; // 补码表示
        int intValue = value & 0xFF; // 将补码转换为无符号整数
        if (value < 0) {
            // 补码为负数，需要将其转换回原码
            int originalValue = -(~intValue + 1);
            System.out.println("原码表示为: " + Integer.toBinaryString(originalValue));
        } else {
            // 补码为正数，原码与补码相同
            System.out.println("原码表示为: " + Integer.toBinaryString(intValue));
        }
    }

    //1.InetAddress（Internet address） ip类
    @Test
    public void testInetAddress() throws Exception {

        InetAddress address = InetAddress.getLocalHost();
        byte[] address1 = address.getAddress();
        System.out.println("getAddress方法取到数据的个数：" + address1.length);
        System.out.println("******************getAddress***************");
        for (byte i : address1) {
            System.out.print(i & 0xFF); // 将字节转换为无符号整数
            System.out.print(".");
        }
        System.out.println();
        System.out.println("***********数据未转换之前：********************");
        for (byte i : address1) {
            System.out.print(i);
            System.out.print(".");
        }
        System.out.println();
        System.out.println("************数据未转换之前类型：*************");
        Object o = address1[0];
        System.out.println(o.getClass().getName());
        System.out.println("***************************************");
        int i = address1[0] & 0xff;
        System.out.println(Integer.toBinaryString(i) + ":" + i);

        System.out.println("");

        System.out.println("****************************");


    }


    //2.补码与反码
    @Test
    public void testByte() throws Exception {


        /**
         * ip（v4）地址的范围是0-225，是一个没有符号是整数，正好是一个字节8位可以表示的极限
         *  将字节转换为无符号整数
         *  按位与：& 逻辑乘
         *  按位或：| 逻辑加
         *  按位非：~ 反
         *  按位异或： ^ 无进位相加
         */

        /**
         * 源码与补码的基础知识：
         * 正数原码=反码=补码
         * 负数：原码=原码，反码=除符号位按位取反，补码=反码+1
         * 引入补码的原因：
         * 为了表示负数，将带有符号位1的，表示为负数的值与不带有符号位1的，
         * 表示为正数的值做加法运算时，运算结果错误
         * 解决方法：
         * 1.计算机中的所有数据都用补码存储
         * 2.用补码进行运算得出的结果也是补码
         * 3.结果求的补码是正数，那就不用转码，因为正数补码等于自身
         * 4.结果求的补码是负数，要转成原码看结果
         * 5.本质是用取余运算来计算的，补码相加超过模值则，减去模得结果，
         * 这个减去模的过程就是在将补码转化成原码的过程
         * 6.例如：（10进制中,模为10）-
         *   9-6=9+（10-6）-10=9+（模-6）-模
         *   3=13-10=3
         * 7.而在计算机中这个模就是进制2，由于计算机中的存储位数不是无限的，
         *  这里以byte为例，8为存储表示[-127,127],如果超过存储的极限也会出现“模循环”现象
         * 8.关于补码与补码运算之后到底要不要转回原码：
         *  如果结果补码为正数则不用转，为负数要转出来看结果
         * 9.将一个负数转换成无符号正整数后，正整数+负数的模=存储的模，以byte为例169+|-87|=256
         *   或者说正整数的按位取反+1就是负整数，虽然正整数不用算反码但是计算方式是一样的
         *
         */

        byte b = -127;
        byte c = 1;
        System.out.println((byte) (b + c));

        int d = b & 0xff;//做运算都是补码在做运算
        //java中的数值运算默认会转换成int/double类型
        int i = 0xff;
        System.out.println(d);
        System.out.println(i);


        System.out.println("*************************");

        System.out.println((byte) (b - c));//-128

    }


    //3.InetAddress（Internet address） ip类
    @Test
    public void text01() throws IOException {

        InetAddress address = InetAddress.getLocalHost();
        InetAddress address1 = InetAddress.getByAddress(new byte[]{127, 0, 0, 1});

        //对象是否表示本地主机的地址
        System.out.println("getHostAddress:" + address.getHostAddress());
        System.out.println("isAnyLocalAddress:" + address.isAnyLocalAddress());
        System.out.println("isAnyLocalAddress:" + address1.isAnyLocalAddress());
        System.out.println("************************************************");


        System.out.println("isLoopbackAddress:" + address.isLoopbackAddress());

        /**
         * 回环地址（loopback address）是一种特殊的网络地址，通常用于本地主机内部通信。它允许一个主机在不涉及实际网络通信的情况下与自己进行通信。
         * 回环地址通常在操作系统的网络协议栈中实现，用于处理本地主机上的网络数据传输。
         *
         * 在 IPv4 中，回环地址是 `127.0.0.1`，通常称为 "localhost"。这意味着当您的计算机尝试与 `127.0.0.1` 通信时，
         * 实际上是与自己进行通信。这对于本地测试和开发非常有用，因为它允许应用程序模拟与其他网络设备通信而无需实际的网络连接。
         *
         * 在 IPv6 中，回环地址是 `::1`。IPv6 中的回环地址的工作方式与 IPv4 中的相似，也用于本地主机内部通信。
         *
         * 回环地址在开发和测试网络应用程序时非常有用，因为它允许您在不涉及外部网络的情况下测试应用程序的各个部分。
         * 例如，您可以将应用程序配置为使用回环地址来模拟客户端与服务器之间的通信，而不需要实际的网络连接。
         *
         * 总之，回环地址是一种用于本地主机内部通信的特殊网络地址，用于测试和开发目的，允许主机与自己进行通信而无需实际的网络连接。
         */


        System.out.println("getCanonicalHostName:" + address.getCanonicalHostName());
        System.out.println("isLinkLocalAddress:" + address.isLinkLocalAddress());
        System.out.println("isMCGlobal:" + address.isMCGlobal());
        System.out.println("getAddress:" + address.getAddress());
        System.out.println("isSiteLocalAddress:" + address.isSiteLocalAddress());
        System.out.println("isReachable:" + address.isReachable(1000));
        System.out.println("isMCLinkLocal:" + address.isMCLinkLocal());
        System.out.println("isMulticastAddress:" + address.isMulticastAddress());
        System.out.println("isMulticastAddress:" + address.isMulticastAddress());
    }

    @Test
    public void text02() throws IOException {
        InetAddress address = InetAddress.getLocalHost();
        System.out.println( address.getHostName());
        System.out.println( address.isReachable(1000));

        System.out.println("*****************************");
        InetAddress address2 = InetAddress.getByAddress(new byte[]{127,0,0,1});
        System.out.println( address2.getHostName());
        System.out.println( address2.isReachable(1000));







    }


}
