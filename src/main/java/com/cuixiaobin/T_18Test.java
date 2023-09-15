package com.cuixiaobin;

import org.junit.*;

public class T_18Test {

    @Before
    public void setUp() throws Exception {
        System.out.println("你好");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("再见");
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("beforeClass");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("afterClass");
    }

    //断言：
    @Test
    public void test01() {
        int length = "123".length();
        System.out.println("123".length());
        Assert.assertEquals("有问题",4,length);
    }

    @Test
    public void test02() {
        int length = "123".length();
        System.out.println("123".length());
        Assert.assertEquals("有问题",3,length);
    }
}
