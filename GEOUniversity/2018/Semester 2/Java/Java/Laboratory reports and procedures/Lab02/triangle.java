package sanjiao;

import java.util.Scanner;

public class triangle
{
   public static void main(String[] args)
   {
       sanjiao s=new sanjiao(3,4,5);

       Scanner in=new Scanner(System.in);
       System.out.println("�����һ���ߵ���Ϣ:");
       double x=in.nextDouble();
       s.setX(x);
       System.out.println("����ڶ����ߵ���Ϣ:");
       double y=in.nextDouble();
       s.setY(y);
       System.out.println("����������ߵ���Ϣ:");
       double z=in.nextDouble();
       s.setZ(z);

       try
       {
           System.out.println("�����εı߳�Ϊ:"+s.showInfo());
           System.out.println("�����ε����Ϊ:"+s.getArea());
       }catch(NotSanjiaoException ex){
           System.out.println("�������룡���ܹ��������Σ�");
           System.exit(-1);
       }

   }
}
