package sxha;

public class Sx {
	public static void main(String[] args)
	{
		flower();
	}
	public static void flower()
	{
		int x=0,y=0,z=0;
		System.out.print("100-999������ˮ�ɻ�����");
		for(int i=100;i<1000;i++)
		{
			x=i/100;
			y=(i%100)/10;
			z=(i%100)%10;
			if(x*x*x+y*y*y+z*z*z==i)
			{
				System.out.print(i+" ");
			}
		}
	}

}
