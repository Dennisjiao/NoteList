#include <stdio.h>
#include <stdlib.h>
int Least(int i_N,int i_M);//求最小公倍数的函数声明
int main()
{
    int i_LCM,i_N,i_M;
    scanf("%d %d",&i_N,&i_M);
    i_LCM=Least(i_N,i_M);
    printf("%d",i_LCM);
    return 0;
}
    int Least(int i_N,int i_M)//定义求最小公倍数的函数
{
   int a,x,y;
   x=i_N;
   y=i_M;
    while(i_N!=0){
        a=i_M%i_N;
        i_M=i_N;
        i_M=a;
    }
    printf("%d",i_M);
    return x*y/i_M;
}
