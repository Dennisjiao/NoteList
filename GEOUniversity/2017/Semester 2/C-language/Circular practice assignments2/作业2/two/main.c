#include <stdio.h>
#include <stdlib.h>

int main()
{
    int m,n,y,i,j;
    printf("Input m:");
    scanf("%d",&m);
    printf("Input n:");
    scanf("%d",&n);
    // ‰»Î∂À
    for(i=1;i<n;i++)
    {
        m=m+1;
        for(j=2;j<=n-1;i++)
        {
            if(n%i == 0)
                break;
        }
        if(j<n)
        {
            printf("%d ",m);
        }
    }





}
