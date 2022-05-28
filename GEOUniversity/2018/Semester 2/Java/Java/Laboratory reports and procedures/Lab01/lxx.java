package lx;
import java.util.Scanner;

public class lxx {
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		int i=0,j=0;
		System.out.println("输入n的值");
		int n=sc.nextInt();
		int a[][] = new int[n][n];
		int x,y;
		for(x=1,y=1;y<=(n+1)/2;y++)
		{
			for(;j<=n-y;j++)//上
			{
				a[i][j]=x;
				x++;
			}
			j--;
			i++;
			
			for(;i<=n-y;i++)
			{
				a[i][j]=x;
				x++;
			}
			i--;
			j--;
			
			for(;j>=y-1;j--)
			{
				a[i][j]=x;
				x++;
			}
			j++;
			i--;
			
			for(;i>=y;i--)
			{
				a[i][j]=x;
				x++;
			}
			i++;
			j++;
			
		}
		for(x=0;x<n;x++)
		{
			for(y=0;y<n;y++)
			{
				System.out.printf("%3d",a[x][y]);
			}
			System.out.println(" ");
		}
		
	}

}
