#include <stdio.h>
#include <stdlib.h>

void shuzu(int num[]);
void bijiao(int num[]);


int main()
{
    int arr[10],a,c,i;

    shuzu(arr);


    bijiao(arr);

    for(c=8;c>=i;c--) //i后面的数后移
    {
        arr[c+1]=arr[c];
    }
    arr[i]=a;//插入数据到i位
    for(i=0;i<10;i++){
            printf("%d ",arr[i]);
    }
    return 0;





}

void shuzu(int num[])
{
    int i;
    for(i=0;i<9;i++)
    {
        scanf("%d",&num[i]);
    }
}

void bijiao(int num[])
{
    int i,a;
    scanf("%d",&a);
    for(i=0;i<9;i++)
    {
        a<num[i];
        break;
    }
}







