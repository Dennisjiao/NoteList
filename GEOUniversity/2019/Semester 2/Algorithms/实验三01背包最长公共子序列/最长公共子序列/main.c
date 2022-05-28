#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int c[100][100];
int b[100][100];
char f[100];
int Max(int m,int n,int i,int j)
{
	if(m > n)
	{
		b[i][j] = -1;
		return m;
	}
	else
	{
		b[i][j] = 1;
		return n;
	}
}
void print(int i,int j,int s,char x[],char y[])/*�ݹ��ӡ�����������*/
{
	if(b[i][j] == 0)
	{
		f[s-1] = x[i-1];
		i--;j--;s--;
		print(i,j,s,x,y);
	}
	else if(b[i][j] == -1)
	{
		i--;
		print(i,j,s,x,y);
	}
	else if(b[i][j] == 1)
	{
		j--;
		print(i,j,s,x,y);
	}
}
int LCS(char x[],char y[])
{
	int i,j;
	int x_len,y_len;
	x_len = strlen(x);
	y_len = strlen(y);
	printf("   ");
	for(i = 0;i < y_len;i++)
	{
		printf("%c  ",y[i]);
	}
	printf("\n");
	for(i = 1;i <= x_len;i++)
	{
		printf("%c  ",x[i-1]);
		for(j = 1;j <= y_len;j++)
		{
			if(x[i-1] == y[j-1])
			{
				c[i][j] = c[i-1][j-1] +1;
				b[i][j] = 0;
				printf("%d  ",c[i][j]);
			}
			else
			{
				c[i][j] = Max(c[i-1][j],c[i][j-1],i,j);
				printf("%d  ",c[i][j]);
			}
		}
		printf("\n");
	}

	//��ӡA��B�������������
	printf("A��B���������������:");
	print(x_len,y_len,c[x_len][y_len],x,y);
	printf("%s",f);
	printf("\n");
	return c[x_len][y_len];
}
int  main()
{
	char A[100],B[100];
	int s;
	printf("�������ַ���A:");
	scanf("%s",A);
	while(strlen(A) > 100)
	{
		printf("��������ַ���������󳤶�,����������!");
		scanf("%s",A);
	}
	printf("�������ַ���B:");
	scanf("%s",B);
	while(strlen(B) > 100)
	{
		printf("��������ַ���������󳤶�,����������!");
		scanf("%s",B);
	}
	s = LCS(A,B);
	printf("A��B������������г���Ϊ: %d \n",s);
}


