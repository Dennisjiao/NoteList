
//Target�������㷨��ʵ������ʱ��ıȽ�

//Author��������

//Data��2019.04.16




#include <stdio.h>
#include <stdlib.h>
#include <time.h>


typedef long clock_t;
#define _CLOCK_T_DEFINED

#define M 100000

void swap(int *a, int *b);                      //��������

void Input(int a[],int n);                      //��������

double Bubble(int a[],int n);                  //ð������

double Insert(int a[],int n);                  //ֱ�Ӳ�������

double Quick(int a[],int low,int high);       //��������


int main()
{
    int Data[M],n,i;
    double t1,t2,t3;
    printf("������Ҫ�Ƚϵ����ݸ���n:");
    scanf("%d",&n);
    Input(Data,n);
    t1=Bubble(Data,n);

    t2=Quick(Data,0,n-1);
    /*for(i=0;i<n;i++)
    {
        printf("%4d",Data[i]);
    }*/
    printf("\n");
    printf("*******ð�������ʱ��Ϊ%f��*******\n",t1);
    printf("*******�Ե����Ϻϲ������ʱ��Ϊ%f��***\n",t2);

    return 0;
}




//��������
void Input(int a[],int n)
{
    int i;
    srand(time(NULL));
    for(i=0;i<n;i++)
    {
        a[i]=rand()%100+1;
    }
}


//ð������
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



//��������
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


//��������
void swap(int *a, int *b)
{
    int temp;

    temp = *a;
    *a = *b;
    *b = temp;

}
