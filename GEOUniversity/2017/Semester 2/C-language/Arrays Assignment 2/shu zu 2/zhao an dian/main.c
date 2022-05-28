#include <stdio.h>
#include <stdlib.h>

int main()
{
    int n,i,j,m,c,d;
    printf("input n:");
    scanf("%d",&n);
    int array[n][n];
    printf("input array:\n");
    for(i=0;i<n;i++)
    {
        for(j=0;j<n;j++)
        {
           scanf("%d",&array[i][j]);
        }
    }
    for(i=0;i<n;i++)
    {
       m=array[i][0];
       for(j=0;j<n;j++)
       {
           if(array[i][j]>=m)
           {
               m=array[i][j];
           }
       }
       d=1;
       for(c=0;c<n;c++)
       {
           if(m>array[c][j-1])
           {
               d=0;
               break;
           }
       }
       if(d==1)
       {
           printf("a[%d][%d]=%d",i,j-1,array[i][j-1]);
           break;
       }
    }
    if(d!=1)
    {
        printf("NO");
    }

    return 0;
}
