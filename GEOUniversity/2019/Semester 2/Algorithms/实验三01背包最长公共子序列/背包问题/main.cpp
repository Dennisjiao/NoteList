
#include <stdio.h>
#include <stdlib.h>
#include <iostream>
using namespace std;



int main()
{
	const int n=4,m=9;
	int i,j;
	int s[n],value[m],V[n+1][m+1];
	/*cout<<"�����뱳������:";
	cin>>n;
	cout<<"��������Ʒ����:";
	cin>>m;*/
	cout<<"�����������Ʒ���:"<<endl;
	for(i=0;i<n;i++)
	{
		cin>>s[i];
	}
	cout<<"�����������Ʒ��ֵ"<<endl;
	for(i=0;i<n;i++)
	{
		cin>>value[i];
	}
	for(i=0;i<m+1;i++)
	{
		V[0][i]=0;
	}
	for(i=0;i<n+1;i++)
	{
		V[i][0]=0;
	}
	for(i=1;i<n+1;i++)
	{
		for(j=1;j<m+1;j++)
		{
			V[i][j]=V[i-1][j];

			if(s[i-1]<=j)
			{
				if(V[i][j]>=V[i-1][j-s[i-1]]+value[i-1])
				{
						V[i][j]=V[i][j];
				}
				else
				{
					V[i][j]=V[i-1][j-s[i-1]]+value[i-1];
				}
			}
		}
	}
	 printf("    ");
	for(i=1;i<m+1;i++)
    {
        printf("%4d",i);
    }
    printf("\n");
	for(i=1;i<n+1;i++)
    {
        printf("%4d",i);
        for(j=1;j<m+1;j++)
        {
            printf("%4d",V[i][j]);
        }
        printf("\n");
    }
	return 0;
}
