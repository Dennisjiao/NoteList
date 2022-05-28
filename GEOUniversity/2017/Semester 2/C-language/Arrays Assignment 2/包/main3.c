#include <stdio.h>
#include <stdlib.h>

int main()
{   int  b,i,j;
    char d;
    scanf("%d",&b);
    int a[b][b],c[b][b],f[b][b];
    for(i=0;i<b;i++)
    {
        for(j=0;j<b;j++){
            scanf("%d",&a[i][j]);
        }
    }

    getchar();
    scanf("%c",&d);
    for(i=0;i<b;i++)
    {
    for(j=0;j<b;j++){
        scanf("%d",&c[i][j]);
        }
    }
    jiajian(b,a,c,f,d);
    for(i=0;i<b;i++)
    {
    for(j=0;j<b;j++){
        printf("%5d",f[i][j]);
        }
        printf("\n");
    }
    return 0;
}



void jiajian(int b,int a[b][b],int c[b][b],int f[b][b],int d)
{
    int i,j;
    for(i=0;i<b;i++){
    for(j=0;j<b;j++){
        if(d=='+')
        {f[i][j]=a[i][j]+c[i][j];}
        if(d=='-')
        {f[i][j]=a[i][j]-c[i][j];}
        }
    }
}
