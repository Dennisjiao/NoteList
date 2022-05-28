#include <stdio.h>
#include <stdlib.h>
void input(int n,int a[]);
void MaxMin(int n,int a[]);
void output(int n,int a[]);
int main()
{
    int n;
    printf("Input n: ");
    scanf("%d",&n);
    int SZ[n];
    printf("Input %d integers: ",n);
    input(n,SZ);
    MaxMin(n,SZ);
    printf("After swapped: ");
    output(n,SZ);
    return 0;
}


void input(int n,int a[])
{
    int i;
    for(i=0;i<n;i++)
    {
        scanf("%d",&a[i]);
    }
}

void MaxMin(int n,int a[])
{
    int max,min,i,c,b;
    int temp,j;
    min=max=a[0];
    c=b=0;
    for(i=0;i<n;i++)
    {
       if(a[i]>max)
       {
           max=a[i];
           b=i;
       }
       else if(a[i]<min)
       {
           min=a[i];
           c=i;
       }

    }
    temp=a[n-1];
    a[n-1]=a[b];
    a[b]=temp;
    j=a[0];
    a[0]=a[c];
    a[c]=j;

}


void output(int n,int a[])
{
    int i;
    for(i=0;i<n;i++)
    {
        printf("%d ",a[i]);
    }
}







