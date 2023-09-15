package com.cuixiaobin;

import org.junit.Test;

import java.lang.annotation.*;

@MyAnnotation(value = "cjkc", age = 50)
//value是默认属性，但是只有填入一个元素时，才可以正确被赋值到value上，起到简写的作用，
// 多个属性还是要拿出来写
public class T_20Annotation {

    //解析注解：
    @Test
    public void test1() {
        Class<T_20Annotation> t_20AnnotationClass = T_20Annotation.class;
        MyAnnotation annotation = t_20AnnotationClass.getAnnotation(MyAnnotation.class);
        int age = annotation.age();
        String value = annotation.value();
        System.out.println(age+":"+value);

        Class<MyAnnotation> myAnnotationClass = MyAnnotation.class;
        Annotation[] annotations = myAnnotationClass.getAnnotations();
        for (Annotation annotation1 : annotations) {
            Class<? extends Annotation> aClass = annotation1.annotationType();

        }


    }

}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//SOURCE:保留到原码阶段
//ClASS:保留到字节码阶段阶段
//RUNTIME:保留到运行阶段阶段
@interface MyAnnotation {
    String value();

    //可以不写属性名直接填值
    int age() default 20;
    //接口属性本质：抽象方法


}

//
//class ProxyUtil {
//    public static Star createProxy(BigStar bigStar) {
//       /* newProxyInstance(ClassLoader loader,
//                Class<?>[] interfaces,
//                InvocationHandler h)
//                参数1：用于指定一个类加载器
//                参数2：指定生成的代理长什么样子，也就是有哪些方法
//                参数3：用来指定生成的代理对象要干什么事情
//                */
//        // Star starProxy = ProxyUtil.createProxy(s);
//        // starProxy.sing("好日子") starProxy.dance()
//        Star starProxy = (Star) Proxy.newProxyInstance(ProxyUtil.class.getClassLoader(), new Class[]{Star.class}, new InvocationHandler() {
//            @Override // 回调方法
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                // 代理对象要做的事情，会在这里写代码
//                if (method.getName().equals("sing")) {
//                    System.out.println("准备话筒，收钱20万");
//                } else if (method.getName().equals("dance")) {
//                    System.out.println("准备场地，收钱1000万");
//                }
//                return method.invoke(bigStar, args);
//            }
//        });
//        return starProxy;
//    }
//}
//
//class mo {
//    public static void main(String[] args) {
//        BigStar s = new BigStar("杨超越");
//        Star starProxy = ProxyUtil.createProxy(s);
//
//        String rs = starProxy.sing("好日子");
//        System.out.println(rs);
//
//        starProxy.dance();
//    }
//}