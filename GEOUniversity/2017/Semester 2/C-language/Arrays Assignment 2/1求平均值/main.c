#include <stdio.h>
#include <stdlib.h>

int ave(int arr[],int size);

int main()
{
    int y,bal[10];
    y=ave(bal,10);

    printf("%d",y);
    return 0;

}

int ave(int arr[],int size)
{
    int i,sum=0;
    for(i=0;i<size;i++)
    {
        scanf("%d",&arr[i]);
        sum=sum+arr[i];
    }
    return sum/size;

}
