
//Target：排序算法的实际运算时间的比较

//Author：杨晓猛

//Data：2019.04.16




#include <stdio.h>
#include <stdlib.h>
#include <time.h>


typedef long clock_t;
#define _CLOCK_T_DEFINED

#define M 100000

void swap(int *a, int *b);                      //交换函数

void Input(int a[],int n);                      //生成数据

double Bubble(int a[],int n);                  //冒泡排序

double Insert(int a[],int n);                  //直接插入排序

double Quick(int a[],int low,int high);       //快速排序


int main()
{
    int Data[M],n,i;
    double t1,t2,t3;
    printf("请输入要比较的数据个数n:");
    scanf("%d",&n);
    Input(Data,n);
    t1=Bubble(Data,n);

    t2=Quick(Data,0,n-1);
    /*for(i=0;i<n;i++)
    {
        printf("%4d",Data[i]);
    }*/
    printf("\n");
    printf("*******冒泡排序的时间为%f：*******\n",t1);
    printf("*******自底向上合并排序的时间为%f：***\n",t2);

    return 0;
}




//生成数据
void Input(int a[],int n)
{
    int i;
    srand(time(NULL));
    for(i=0;i<n;i++)
    {
        a[i]=rand()%100+1;
    }
}


//冒泡排序
double Bubble(int a[],int n)
{
    int i,j;
    clock_t T_start,T_end;
    T_start=clock();
    for (i=0;i<n;i++)
    {
        for (j=0;j<n-1-i;j++)
        {
            if (a[j]>a[j+1])
            {
                swap(&a[j],&a[j+1]);
            }
        }
    }
    T_end=clock();
    return (double)((T_end) - (T_start))/CLOCKS_PER_SEC;
}



//快速排序
int Partition(int a[],int low,int high)
{
    int i,j,x;
    i=low;
    j=high;
    x=a[low];
    while(low<high&&i<j)
    {
        if(x>a[j])
        {
            a[i]=a[j];
            i++;
        }
        else
        {
            j--;
        }
        if(x<a[i])
        {
            a[j]=a[i];
            j--;
        }
        else
        {
            i++;
        }
    }
    if(i>=j)
    {
        a[i]=x;
    }
    return i;
}


double Quick(int a[],int low,int high)
{
    int k;
    clock_t T_start,T_end;
    T_start=clock();
    if(low<high)
    {
        k=Partition(a,low,high);
        Quick(a,low,k-1);
        Quick(a,k+1,high);
    }
    T_end=clock();
    return (double)((T_end) - (T_start))/CLOCKS_PER_SEC;
}


//交换函数
void swap(int *a, int *b)
{
    int temp;

    temp = *a;
    *a = *b;
    *b = temp;

}
