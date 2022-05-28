#include <stdio.h>
#include <stdlib.h>
#define N 10
void Sort(int i_Num[]);
int main()
{
    int i_Arr[N],i;
    for(i=0;i<N;i++)
    {
        scanf("%d",&i_Arr[i]);
    }

    Sort(i_Arr);
     for(i=0;i<N;i++)
    {
        printf("%d ",i_Arr[i]);
    }
    system("Pause");
    return 0;
}

void Sort(int i_Num[])
{

int temp,i,j,k;
for(i=0;i<=9;i++)
{k=i;
for(j=0;j<9;j++){
if(i_Num[j]>i_Num[k]){k=j;}
if(k!=i)
{temp=i_Num[k];
i_Num[k]=i_Num[i];
i_Num[i]=temp;
}
}
}return i_Num[i];}
