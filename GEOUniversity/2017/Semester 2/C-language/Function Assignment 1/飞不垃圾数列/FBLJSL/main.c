#include <stdio.h>
#include <stdlib.h>
int fib(int x);
int main()
{
    int m,n,i,j;
    printf("Input m: ");
    scanf("%d",&m);
    printf("Input n: ");
    scanf("%d",&n);
    //ษจร่
    for(i=m;i<n;i++)
    {
        for(j=1;fib(j)<n;j++)
        {
            if(fib(j)>m&&fib(j)==i)
            {
                printf("%d ",i);
            }
        }
    }

}

int fib(int x)
{
    if(x<=2)
        return 1;
    else
        return fib(x-1)+fib(x-2);
}
