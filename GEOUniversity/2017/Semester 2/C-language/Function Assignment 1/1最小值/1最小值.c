#include <stdio.h>
int min(int i_X,int i_Y);
int main()
{
    int i_X,i_Y,i_Min;
    scanf("%d,%d",&i_X,&i_Y);
    i_Min=min(i_X,i_Y);//µ÷ÓÃº¯Êı
    printf("%d",i_Min);
    return 0;
}
int min(int i_X,int i_Y)
{
    int i_Min;
    if(i_X<i_Y)
        i_Min=i_X;
    else i_Min=i_Y;
}
