#include<stdio.h>
#include<stdlib.h>
#include <windows.h>
#define MAXSIZE 100
#define N 8
//��2�����������Ͷ���
int board[8][8];           //��������
int Htry1[8]={1,-1,-2,2,2,1,-1,-2};
 /*�洢���������λ����Ե�ǰλ�����±����������*/
int Htry2[8]={2,-2,1,1,-1,-2,2,-1};
                         /*�洢���������λ����Ե�ǰλ�����±����������*/
struct Stack{              //����ջ����
	int i;                  //������
	int j;                  //������
   int director;           //�洢����
}stack[MAXSIZE];           //����һ��ջ����
int top=-1;                //ջָ��

//��3������������
void horseweizhi(int xi,int yi);
int changshi(int i,int j);
void display();

//��4������ʼ���꺯��ģ��
void horseweizhi(int xi,int yi)
{
	int x,y,i;
	top++;
	stack[top].i=xi;
	stack[top].j=yi;
	stack[top].director=-1;
	board[xi][yi]=top+1;
	x=stack[top].i;
	y=stack[top].j;
	if(changshi(x,y))
		display();
	else
 printf("�޽�");
}

//��5����̽Ѱ·������ģ��
int changshi(int i,int j)
{
	int find,director,number,min;
int i1,j1,h,k,s;
	int a[8],b1[8],b2[8],d[8];
	while(top>-1)
	{
		for(h=0;h<8;h++)
		{
			number=0;
	    	i=stack[top].i+Htry1[h];
	    	j=stack[top].j+Htry2[h];
	    	b1[h]=i;
	    	b2[h]=j;
	    	if(board[i][j]==0&&i>=0&&i<8&&j>=0&&j<8)
			{
				for(k=0;k<8;k++)
				{
					i1=b1[h]+Htry1[k];
		            j1=b2[h]+Htry2[k];
				    if(board[i1][j1]==0&&i1>=0&&i1<8&&j1>=0&&j1<8)

				     	number++;
				}
			    a[h]=number;
			}
		}
    	for(h=0;h<8;h++)
		{
			min=9;
	    	for(k=0;k<8;k++)
				if(min>a[k])
				{
					min=a[k];
			    	d[h]=k;
                   s=k;
				}
   		   	a[s]=9;
		}
       director=stack[top].director;
		if(top>=63)
			return (1);
		find=0;
		for(h=director+1;h<8;h++)
		{
			i=stack[top].i+Htry1[d[h]];
		    j=stack[top].j+Htry2[d[h]];
			if(board[i][j]==0&&i>=0&&i<8&&j>=0&&j<8)
			{
				find=1;
				break;
			}
		}
		if(find==1)
		{
			stack[top].director=director;
			top++;
			stack[top].i=i;
			stack[top].j=j;
			stack[top].director=-1;
			board[i][j]=top+1;
		}
		else
		{
			board[stack[top].i][stack[top].j]=0;
			top--;
		}
	}
	return (0);
}

//��6�����·������ģ��
void display()
{
	 int i,j,k;

    for(i=0;i<N;i++)
	{
	    for(k=0;k<28;k++){printf(" ");}

		for(j=0;j<N;j++)
        {
			printf("\t%d  ",board[i][j]);
        }
		printf("\n\n");
	}
	printf("\n");
}

//��5��������ģ��
int main()
{
	int i,j;
	int x,y;
	for(i=0;i<N;i++)                      //��ʼ������
	  for(j=0;j<N;j++)
	     board[i][j]=0;


    printf("\n\n\n\n\n");
    printf("\n\n\t\t        ********************************************************************\n\n\n");
    printf("\t\t\t\t\t         ��̤����\n\n");
    printf("\t\t\t\t\t\t            �����������·��\n\n\n");
    printf("\t\t\t\t\t\tPlease press enter to continue...\n\n\n");
    printf("\t\t        ********************************************************************");
    getchar();
    system("cls.exe");
printf("\n\n");
printf("\t");
    for(i=0;i<48;i++)
    {printf("*");}
    printf("��̤����");
    for(i=0;i<50;i++)
    {printf("*");}
    printf("\n");
	for(;;)
	{
	    printf("\n");
	    for(i=0;i<40;i++){printf(" ");}
		printf("������������ʼ����(1<=x<=8 and 1<=y<=8)\n");
		for(i=0;i<50;i++){printf(" ");}
		printf("������������ x = ");
		scanf("%d",&x);         //������ʼλ�õĺ�����
		for(i=0;i<50;i++){printf(" ");}
		printf("������������ y = ");
		scanf("%d",&y);         //������ʼλ�õ�������
		if(x>=1&&x<=8&&y>=1&&y<=8)break;

		printf("\n\n");
		for(i=0;i<36;i++){printf(" ");}
		printf("Your input is worng!!! please input right date!!!\n");
	}
	for(i=0;i<47;i++){printf(" ");}printf("���������λ�ÿ�ʼ (%d,%d) \n\n", x,y);
	horseweizhi(x-1,y-1);      //������ʼ���꺯��
	printf("\t");
	for(i=0;i<48;i++)
    {printf("*");}
    printf("��̤����");
    for(i=0;i<50;i++)
    {printf("*");}
    printf("\n");
}

