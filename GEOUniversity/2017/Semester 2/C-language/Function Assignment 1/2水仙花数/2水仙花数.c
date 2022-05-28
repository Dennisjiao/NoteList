#include <stdio.h>
#include <stdlib.h>
#include <math.h>
int TestSXHS(int i_Num);
int main()
{
    int i,i_Result;
    for(i=100;i<=999;i++){
        i_Result=TestSXHS(i);//µ÷ÓÃº¯Êý
        if(1==i_Result)
            printf("%d ",i);
    }
    return 0;
}
int TestSXHS(int i_Num)
{
    int i_D1,i_D2,i_D3;
    i_D1=i_Num/100;
    i_D2=(i_Num/10)%10;
    i_D3=i_Num%10;
    if(i_Num==i_D1*i_D1*i_D1+i_D2*i_D2*i_D2+i_D3*i_D3*i_D3)
        return 1;
    else return 0;
}
