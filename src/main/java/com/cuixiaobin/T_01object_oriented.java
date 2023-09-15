package com.cuixiaobin;

import org.junit.Test;

public class T_01object_oriented {

    public static void main(String[] args) {
        {
            double exchangeRate = Currency.USD.getExchangeRate();
            System.out.println("汇率："+exchangeRate);

            double convertedAmount = Currency.EUR.convertToUSD(100);
            System.out.println("欧元换美元："+convertedAmount);
        }

    }

    @Test
    public void test() {

    }

}

//单例模式
//饿汉单例模式：
class SingleInstance {
    //私有构造函数，公有静态属性,调用静态对象已经存在
    public static SingleInstance singleInstance = new SingleInstance();

    private SingleInstance() {

    }

}

//懒汉单例模式：
class SingleInstance01 {
    //私有构造函数，公有静态方法，需要实例在创建对象,公有获取实例的方法

    public static SingleInstance01 singleInstance01;

    //私有方法，类内可以应用
    private SingleInstance01() {
    }

    public static SingleInstance01 getInstance() {
        singleInstance01 = new SingleInstance01();
        return singleInstance01;
    }


}

//枚举
enum Currency {
    //枚举类都是继承了枚举类型：java.lang.Enum
    //枚举都是最终类，不可以被继承。
    //构造器都是私有的，枚举对外不能创建对象。

    //枚举类的第一行默认都是罗列枚举对象的名称的。
    //枚举类相当于是多例模式。

    USD(1.0),EUR(0.85),GBP(0.72);


    //枚举类中，每一个枚举对象都是一个对象
    //枚举对象公用一套属性和函数，包括构造函数，对其进行构造
    //枚举对象，直接在后面填入构造函数所需参数，不能是变量
    private final double exchangeRate;

    Currency(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public double getExchangeRate(){
        return exchangeRate;
    }

    public double convertToUSD(double amount){
        return amount*exchangeRate;
    }
}

