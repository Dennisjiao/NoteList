package com.jas.reflect;
/*
 * 在 Java 中可以通过三种方法获取类的字节码 (Class) 对象
 * 1.通过 Object 类中的 getClass() 方法
 * 2.所有数据类型都具备一个静态的属性.class 来获取对应的 Class 对象
 * 3.只要通过给定类的字符串名称就可以获取该类的字节码对象，这样做扩展性更强。
 *   通过 Class.forName() 方法完成，必须要指定类的全限定名
 */
public class ReflectTest1 {
	public static void main(String[] args) {

        Fruit1 fruit = new Fruit1();
        Class<?> class1 = fruit.getClass();     //方法一

        Class<?> class2 = Fruit1.class;     //方法二

        Class class3 = null;     
        try {    //方法三，如果这里不指定类所在的包名会报 ClassNotFoundException 异常
            class3 = Class.forName("com.jas.reflect.Fruit");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(class1 + "," +class2 + "," + class3);

    }
}
class Fruit1{}