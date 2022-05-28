#include <stdio.h>
#include <stdlib.h>

int main()
{   int a[10],b,c,i;
    for(i=0;i<9;i++){
        scanf("%d",&a[i]);
    }
    scanf("%d",&b);
    for(i=0;i<9;i++)//查找插入位置
    {
        if (b<a[i])
            {
            break;
            }
    }
    for(c=8;c>=i;c--) //i后面的数后移
    {
        a[c+1]=a[c];
    }
    a[i]=b;//插入数据到i位
    for(i=0;i<10;i++){
            printf("%d ",a[i]);
    }
    return 0;
}
