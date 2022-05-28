#include <stdio.h>
#include <stdlib.h>
#define N 10
void Delete(int i_Arr[]);
int main()
{
    int i_Array[N],i;
    //输入有序的10个元素
    for(i=0;i<N;i++)
    {
        scanf("%d",&i_Array[i]);
    }
    //调用函数实现相同数据的删除
    Delete(i_Array);
    //输出删除后的数组元素
    for(i=0;i<N;i++)
    {
       if(i_Array[i]!=0){
        printf("%d ",i_Array[i]);
       }

    }

    return 0;
}
//说明：删除的元素置为0
void Delete(int i_Arr[])
{ int i,j;
  for(i=0;i<N;i++){
    for(j=i+1;j<N;j++){
        if(i_Arr[i]==i_Arr[j]){

            i_Arr[j]=0;
        }
    }
  }
  return i_Arr[i];
}
