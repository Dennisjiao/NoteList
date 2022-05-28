package sanjiao;

import java.util.Scanner;

public class triangle
{
   public static void main(String[] args)
   {
       sanjiao s=new sanjiao(3,4,5);

       Scanner in=new Scanner(System.in);
       System.out.println("输入第一条边的信息:");
       double x=in.nextDouble();
       s.setX(x);
       System.out.println("输入第二条边的信息:");
       double y=in.nextDouble();
       s.setY(y);
       System.out.println("输入第三条边的信息:");
       double z=in.nextDouble();
       s.setZ(z);

       try
       {
           System.out.println("三角形的边长为:"+s.showInfo());
           System.out.println("三角形的面积为:"+s.getArea());
       }catch(NotSanjiaoException ex){
           System.out.println("错误输入！不能构成三角形！");
           System.exit(-1);
       }

   }
}
