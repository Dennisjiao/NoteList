package com.jas.reflect;

import java.lang.reflect.Field;

/*
 * 通过反射机制获取 Class 中的属性
 */
public class ReflectTest2 {
	public static void main(String[] args) throws Exception {

        Class<?> clazz = null;
        Field field = null;

        clazz = Class.forName("com.jas.reflect.Fruit2");
        //field = clazz.getField("num");       getField() 方法不能获取私有的属性
        // field = clazz.getField("type");     访问私有字段时会报 NoSuchFieldException 异常
        field = clazz.getDeclaredField("type");     //获取私有 type 属性
        field.setAccessible(true);  //对私有字段的访问取消检查
        Fruit2 fruit = (Fruit2) clazz.newInstance();  //创建无参对象实例
        field.set(fruit,"Apple");   //为无参对象实例属性赋值
        Object type = field.get(fruit); //通过 fruit 对象获取属性值

        System.out.println(type);
        
        field = clazz.getField("num");
        field.set(fruit,2);
        type = field.get(fruit);
        System.out.println(type);
    }
}
class Fruit2{
    public int num;
    private String type;

    public Fruit2(){
        System.out.println("无参构造器 Run...........");
    }
    public Fruit2(String type){
        System.out.println("有参构造器 Run..........." + type);
    }

}
