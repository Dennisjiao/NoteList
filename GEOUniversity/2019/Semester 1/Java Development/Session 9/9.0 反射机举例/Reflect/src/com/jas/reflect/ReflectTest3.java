package com.jas.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/*
 * 通过反射机制获取 Class 中的方法并运行。
 */
public class ReflectTest3 {
	public static void main(String[] args) throws Exception {

        Class clazz = null;
        Method method = null;

        clazz = Class.forName("com.jas.reflect.Fruit3");
        Constructor<Fruit3> fruitConstructor = clazz.getConstructor(String.class);
        Fruit3 fruit = fruitConstructor.newInstance("Apple");    //创建有参对象实例

        method = clazz.getMethod("show",null);  //获取空参数 show 方法
        method.invoke(fruit,null);  //执行无参方法

        method = clazz.getMethod("show",int.class); //获取有参 show 方法
        method.invoke(fruit,20);  //执行有参方法

    }
}
class Fruit3{
    private String type;

    public Fruit3(String type){
        this.type = type;
    }
    public void show(){
        System.out.println("Fruit type = " + type);
    }
    public void show(int num){
        System.out.println("Fruit type = " + type + ".....Fruit num = " + num);
    }
}
