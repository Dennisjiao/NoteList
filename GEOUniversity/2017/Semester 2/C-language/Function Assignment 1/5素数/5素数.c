#include <stdio.h>
#include <stdlib.h>
#include <math.h>
int PrimeTest(int i_Num);//素数判断函数的声明
int main()
{
    int i,i_Flag;
    for(i=200;i<=250;i++){
        i_Flag=PrimeTest(i);//调用函数判断是否是素数
        if (1==i_Flag)
            printf("%d ",i);
    }
    system("Pause");
    return 0;
}

int PrimeTest(int i_Num)
{
    int n,m;//m为因子，n为余数
    for(m=2;m<i_Num;m++){
        n=i_Num%m;
        if(0==n) break;
    }
    if(i_Num<=m) return 1;
    else return 0;
}
