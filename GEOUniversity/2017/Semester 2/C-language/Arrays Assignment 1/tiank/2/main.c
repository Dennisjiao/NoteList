#include <stdio.h>
#include <stdlib.h>
#define N 10
void Delete(int i_Arr[]);
int main()
{
    int i_Array[N],i;
    //���������10��Ԫ��
    for(i=0;i<N;i++)
    {
        scanf("%d",&i_Array[i]);
    }
    //���ú���ʵ����ͬ���ݵ�ɾ��
    Delete(i_Array);
    //���ɾ���������Ԫ��
    for(i=0;i<N;i++)
    {
       if(i_Array[i]!=0){
        printf("%d ",i_Array[i]);
       }

    }

    return 0;
}
//˵����ɾ����Ԫ����Ϊ0
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
