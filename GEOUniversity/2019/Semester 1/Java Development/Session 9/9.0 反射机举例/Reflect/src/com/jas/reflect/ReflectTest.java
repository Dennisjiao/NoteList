package com.jas.reflect;

import java.lang.reflect.Constructor;

/*
 * 通过反射机制获取类信息 
 * 通过反射机制创建对象，在创建对象之前要获得对象的构造函数对象，
 * 通过构造函数对象创建对应类的实例
 */
public class ReflectTest {
	public static void main(String[] args) throws Exception {

        Class clazz = null;
        clazz = Class.forName("com.jas.reflect.Fruit");
        Constructor<Fruit> constructor1 = clazz.getConstructor();
        Constructor<Fruit> constructor2 = clazz.getConstructor(String.class);

        Fruit fruit1 = constructor1.newInstance();
        Fruit fruit2 = constructor2.newInstance("Apple");

    }
}

class Fruit{
    public Fruit(){
        System.out.println("无参构造器 Run...........");
    }
    public Fruit(String type){
        System.out.println("有参构造器 Run..........." + type);
    }
}