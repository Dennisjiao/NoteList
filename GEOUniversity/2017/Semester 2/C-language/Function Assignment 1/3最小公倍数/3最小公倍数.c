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
    int a,temp,p;
    if(i_N<i_M){
        temp=i_N;
        i_N=i_M;
        i_M=temp;
    }
    p=i_N*i_M;
    while(i_M!=0){
        a=i_N%i_M;
        i_N=i_M;
        i_M=a;
    }
    return p/i_N;
}
