
#include <stdio.h>
#include <stdlib.h>
#define M 10000
int main()
{
    int i=0,k,j=0;
    int a[M];
    FILE *fp;
    fp=fopen("Test.txt","r");
    if(!fp)
    {
        printf("文件打开失败！\n");
        exit(1);
    }
    while (!feof(fp))
    {
        fscanf(fp,"%d ",&a[i]);
        j++;
        printf("%d  ",a[i]);
        i++;
    }
    printf("\n");
    printf("\n");
    printf("请输入要寻找的第K小项:");
    scanf("%d",&k);
    printf("第%d小项为：",k);
    printf("%d",random_select(a,0,j-1,k));
    return 0;
}


int partition(int a[],int l,int r)
{
    int i,j,x,temp;
    i = l;
    j = r+1;
    x = a[l];
    while (1)
    {
        while(a[++i] < x);
        while(a[--j] > x);
        if(i >= j) break;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    a[l] = a[j];
    a[j] = x;
    return j;

}

int random_partition(int a[],int l,int r)
{
    int i = l+rand()%(r-l+1);
    int temp = a[i];
    a[i] = a[l];
    a[l] = temp;
    return partition(a,l,r);
}

int random_select(int a[],int l,int r,int k)
{
    int i,j;
    if (l == r)
    {
        return a[l];
    }
    i = random_partition(a,l,r);
    j = i-l+1;
    if(k == j)
        return a[i];
    if(k < j)
    {
        return random_select(a,l,i-1,k);
    }
    else
        return random_select(a,i+1,r,k-j);
}
