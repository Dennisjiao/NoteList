#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <string.h>
#include <math.h>

//ֱ�Ӳ�������
void insertionSort(int a[],int length)
{
    int i,j,temp;
    for (i = 1; i < length; i++)
    {
        if(a[i]<a[i-1])
        {
            temp=a[i];
            for(j=i-1;j>=0&&a[j]>temp;j--)
            {
                a[j+1]=a[j];
            }
            a[j+1]=temp;
        }
    }
}
void bubble_sort(int arr[], int len)
{
    int i, j,temp;
    for (i = 0; i < len - 1; i++)
        for (j = 0; j < len - 1 - i; j++)
        if (arr[j] > arr[j + 1])
        {
            temp = arr[j];
            arr[j] = arr[j + 1];
            arr[j + 1] = temp;
        }
}
//��ѡ������
void Select_Sort(int a[],int n)
{
    int i,j,k;
    int temp;
    for(i=0;i<n-1;i++)
    {
        k=i;
        a[k]=a[i];
        for(j=i+1;j<n;j++)
        {
            if(a[j]<a[k])
            {
                k=j;
            }
        }
        if(i!=k)
        {
            temp=a[k];
            a[k]=a[i];
            a[i]=temp;
        }
    }
}
//��������
void quick_sort(int s[],int low,int high)
{
    if(low < high)
    {
        int i=low,j=high,x=s[low];
        while(i<j)
        {
            while(i<j && s[j]>=x)//���ҵ����ҵ���һ��С��x����
                j--;
            if(i<j)
            {
                s[i]=s[j];
                i++;
            }
            while(i<j && s[i]<=x)//���������ҵ���һ������x����
                i++;
            if(i<j)
            {
                s[j]=s[i];
                j--;
            }
        }

        s[i]=x;//i = j��ʱ�򣬽�x�����м�λ��
        quick_sort(s,low,i-1);//�ݹ����
        quick_sort(s,i+1,high);
    }
}

void result(int a[],int length)
{
    int i;
    for(i=0;i<length;i++)
    {
        printf("%d ",a[i]);
    }
}
int main()
{
    int i,length,select;
    printf("������Ҫ��������ݵĸ�����\n");
    scanf("%d",&length);
    int a[length],b[length];
    /*printf("������Ҫ��������ݣ�\n");
    for(i=0;i<length;i++)
    {
        scanf("%d",&a[i]);
    }*/
    FILE *fp;
    fp=fopen("data.txt","r");
    for(i=0;i<length;i++)
    {
        fscanf(fp,"%d",&a[i]);
    }
    fclose(fp);
    printf("1.ֱ�Ӳ�������\n");
    printf("2.ð������\n");
    printf("3.��ѡ������\n");
    printf("4.��������\n");
    printf("5.exit(0)");
    while(1)
    {
        for(i=0;i<length;i++)
        {
            b[i]=a[i];
        }
        printf("��������Ҫ���еĲ�������ţ�\n");
        scanf("%d",&select);
        switch(select)
        {
            case 1:insertionSort(b,length);
                    printf("ֱ�Ӳ�������Ľ��Ϊ��\n");
                    result(b,length);
                    printf("\n");
                    break;
            case 2:bubble_sort(b,length);
                    printf("ð������Ľ��Ϊ��\n");
                    result(b,length);
                    printf("\n");
                    break;
            case 3:Select_Sort(b,length);
                    printf("��ѡ������Ľ��Ϊ��\n");
                    result(b,length);
                    printf("\n");
                    break;
            case 4:quick_sort(b,0,length-1);
                    printf("��������Ľ��Ϊ��\n");
                    result(b,length);
                    printf("\n");
                    break;
            case 5:exit(0);
        }
    }
    return 0;
}
