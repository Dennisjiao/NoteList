package xy;
import java.util.Scanner;
import java.math.BigDecimal;


public class xy {
	public static void main(String[]args)
	{
		double x1,x2;
		System.out.println("���̱��ʽΪ��ax2+bx+C=0");
		Scanner sc=new Scanner(System.in);
		System.out.println("������a��ֵ:");
		double a=sc.nextDouble();
		System.out.println("������b��ֵ");
		double b=sc.nextDouble();
		System.out.println("������c��ֵ");
		double c=sc.nextDouble();
		double t=b*b-4*a*c;
		if(a==0&&b==0&c==0)
		{
			System.out.println("���̲�����");
		}
		else if(a==0&&b==0&&c!=0)
		{
			System.out.println("���̲�����");
		}
		else if(a==0&&b!=0&&c!=0)
		{
			System.out.println("�˷���ΪһԪһ�η���");
			double result = (-1*c)/b;
			System.out.println(resetValue(result)+"���̵Ľ�Ϊ��");
		}
		else if(t>0)
		{
			System.out.println("����������ʵ��");
			x1=((-b)+Math.sqrt(t))/2*a;
			x2=((-b)-Math.sqrt(t))/2*a;
			System.out.println("x1="+resetValue(x1));
			System.out.println("x2="+resetValue(x2));
		}
		else if(t<0)
		{
			double x4,x5;
			if(b!=0)
			{
				x4=(-1*b)/(2*a);
				x5=Math.sqrt(-1*t)/(2*a);
				System.out.println("�����й����");
				System.out.println("x1="+resetValue(x4).toString()+"+"+resetValue(x5).toString()+"i");
				System.out.println("x2="+resetValue(x4).toString()+"-"+resetValue(x5).toString()+"i");	
			}
			else
			{
				double x6=Math.sqrt(-1*t)/(2*a);
				System.out.println("������һ���෴���:");
				System.out.println("x1=0-"+resetValue(x6).toString()+"i");
				System.out.println("x2=0-"+resetValue(x6).toString()+"i");
			}
		}
		else if(t==0)
		{
			double x3=(-1*b)/2*a;
			System.out.println("������һ��ʵ��");
			System.out.println("���̵Ľ�Ϊ��"+resetValue(x3));
			}
		}
		
		private static BigDecimal resetValue(double n)
		{
			BigDecimal bd=new BigDecimal(n);
			return bd.setScale(2,BigDecimal.ROUND_HALF_UP);
		}
	}

