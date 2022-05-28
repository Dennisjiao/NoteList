#include<stdio.h>
#include<stdlib.h>
#include <windows.h>
#define MAXSIZE 100
#define N 8
//（2）、数据类型定义
int board[8][8];           //定义棋盘
int Htry1[8]={1,-1,-2,2,2,1,-1,-2};
 /*存储马各个出口位置相对当前位置行下标的增量数组*/
int Htry2[8]={2,-2,1,1,-1,-2,2,-1};
                         /*存储马各个出口位置相对当前位置列下标的增量数组*/
struct Stack{              //定义栈类型
	int i;                  //行坐标
	int j;                  //列坐标
   int director;           //存储方向
}stack[MAXSIZE];           //定义一个栈数组
int top=-1;                //栈指针

//（3）、函数声明
void horseweizhi(int xi,int yi);
int changshi(int i,int j);
void display();

//（4）、起始坐标函数模块
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
 printf("无解");
}

//（5）、探寻路径函数模块
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

//（6）输出路径函数模块
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

//（5）主程序模块
int main()
{
	int i,j;
	int x,y;
	for(i=0;i<N;i++)                      //初始化棋盘
	  for(j=0;j<N;j++)
	     board[i][j]=0;


    printf("\n\n\n\n\n");
    printf("\n\n\t\t        ********************************************************************\n\n\n");
    printf("\t\t\t\t\t         马踏棋盘\n\n");
    printf("\t\t\t\t\t\t            ——马的行走路径\n\n\n");
    printf("\t\t\t\t\t\tPlease press enter to continue...\n\n\n");
    printf("\t\t        ********************************************************************");
    getchar();
    system("cls.exe");
printf("\n\n");
printf("\t");
    for(i=0;i<48;i++)
    {printf("*");}
    printf("马踏棋盘");
    for(i=0;i<50;i++)
    {printf("*");}
    printf("\n");
	for(;;)
	{
	    printf("\n");
	    for(i=0;i<40;i++){printf(" ");}
		printf("请输入棋子起始坐标(1<=x<=8 and 1<=y<=8)\n");
		for(i=0;i<50;i++){printf(" ");}
		printf("请输入行坐标 x = ");
		scanf("%d",&x);         //输入起始位置的横坐标
		for(i=0;i<50;i++){printf(" ");}
		printf("请输入列坐标 y = ");
		scanf("%d",&y);         //输入起始位置的纵坐标
		if(x>=1&&x<=8&&y>=1&&y<=8)break;

		printf("\n\n");
		for(i=0;i<36;i++){printf(" ");}
		printf("Your input is worng!!! please input right date!!!\n");
	}
	for(i=0;i<47;i++){printf(" ");}printf("从这里这个位置开始 (%d,%d) \n\n", x,y);
	horseweizhi(x-1,y-1);      //调用起始坐标函数
	printf("\t");
	for(i=0;i<48;i++)
    {printf("*");}
    printf("马踏棋盘");
    for(i=0;i<50;i++)
    {printf("*");}
    printf("\n");
}

