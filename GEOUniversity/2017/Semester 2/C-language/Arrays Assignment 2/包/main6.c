#include <stdio.h>
#include <stdlib.h>

int main()
{   int n,i,j,x,y,k,find;
    printf("Input n: ");
    scanf("%d",&n);
    int a[n][n];
    printf("Input array: ");
    printf("\n");
    for(i=0;i<n;i++)
    {
        for(j=0;j<n;j++){
            scanf("%d",&a[i][j]);
        }
    }
    for(i=0;i<n;i++)
    {
     x=0;
     for(j=1;j<n;j++)//穷举所有的行
     if(a[i][j]>a[i][x])x=j; //找到第i行上最大的数a[i][x]
     k=1;
     for(y=0;y<n;y++)  //对找到的该数穷举所有行
     if(a[y][x]<a[i][x])k=0;//如它不是本列上最小的数就“一票否决”
     if(k==1)
    {
     printf("a[%d][%d]=%d\n",i,x,a[i][x]);
     find=1;
    }
    }
    if(find!=1){
        printf("NO");
    }
    return 0;
}
