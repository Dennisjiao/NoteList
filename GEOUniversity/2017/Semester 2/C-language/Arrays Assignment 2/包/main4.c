#include <stdio.h>
#include <stdlib.h>

int main()
{   int i,j,n;
    scanf("%d",&n);
    int Y[n][n];
    for(i=0;i<n;i++)
    {
        Y[i][0]=1;
        Y[i][i]=1;

    }
    for(i=2;i<n;i++)
    {
            for(j=1;j<i;j++)
            {
                Y[i][j]=Y[i-1][j-1]+Y[i-1][j];
            }
    }
    for(i=0;i<n;i++)
    {
        for(j=0;j<=i;j++)
        {
            printf("%4d",Y[i][j]);
        }
        printf("\n");
    }
    return 0;
}
