#include <stdio.h>
int main(void)
{
    int m,n,i,a[20];
    printf("Input m: ");
    scanf("%d",&m);
    printf("Input n: ");
    scanf("%d",&n);
    a[0]=1;
    a[1]=1;
    for(i=2;i<=20;i++)
    {
        a[i]=a[i-1]+a[i-2];
    }
    for(i=0;i<=20;i++)
    {
        if(a[i]>m&&a[i]<=n)
            printf("%d ",a[i]);
    }
    return 0;
}

