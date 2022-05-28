#include <stdio.h>
#include <stdlib.h>

int main()
{   int a,b[10];
    a=avr(b);
    printf("%d",a);
    return 0;
}
int avr(int a[])
{   int i,sum=0,avr;
    for(i=0;i<10;i++)
    {
        scanf("%d",&a[i]);
    }
    for(i=0;i<10;i++)
    {
        sum=sum+a[i];
    }
    avr=sum/10;
    return avr;
}
