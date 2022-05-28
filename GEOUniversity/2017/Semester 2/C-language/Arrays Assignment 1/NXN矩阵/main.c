#include <stdio.h>
#include <stdlib.h>
#define M 3
#define N 3

int main()
{
    int s[M][N],i,j;
    for(i=0;i<M;i++){
        for(j=0;j<N;j++){
        scanf("%d",&s[i][j]);}
    }
    for(i=0;i<M;i++){
        for(j=0;j<N;j++){
    printf("%d ",s[j][i]);}
    printf("\n");

    }
    return 0;
}
