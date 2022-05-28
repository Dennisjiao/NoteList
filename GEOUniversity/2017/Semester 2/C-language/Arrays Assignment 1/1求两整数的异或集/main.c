#include <stdio.h>
#include <stdlib.h>
#define N 20

int input(int arr[N]);
void output(int arr[],int n);
void swap(int *pa,int *pb);
void sort(int arr[],int n);
int yihuo(int arr[],int arr2[],int arr3[],int n,int m);
int main()
{
    int a[N],b[N],c[N],aLength,bLength,cLength;
    aLength=input(a);
    bLength=input(b);
    sort(a,aLength);
    output(a,aLength);
    putchar('\n');

    sort(b,bLength);
    output(b,bLength);
    putchar('\n');
    cLength=yihuo(a,b,c,aLength,bLength);
    output(c,cLength);
    return 0;
}
int input(int arr[N]){
   int i;
   char ch;
   for(i=0;i<N;i++)
   {
       scanf("%d",&arr[i]);
       ch=getchar();
       if('\n'==ch){
        break;
       }
   }
   return i+1;
   }


void sort(int arr[],int n){
   int i,j,k;
   for(i=0;i<n;i++){
    k=i;
    for(j=1+i;j<n;j++){
        if(arr[j]>arr[k])
            k=j;
    }
    if(k!=1){
        swap(&arr[k],&arr[i]);
    }
   }
}
int yihuo(int arr1[],int arr2[],int arr3[],int n,int m)
{
    int i=0,j=0,k=0;
    while((i<n)&&(j<m)){
        if(arr1[i]>arr2[j]){
            arr3[k]=arr1[i];
            i++;k++;
        }
        else if(arr1[i]<arr2[j]){
            arr3[k]=arr2[j];
            j++;k++;
        }
        else{
            i++;
            j++;
        }
    }
    while(i<n){
        arr3[k]=arr1[i];
        k++;i++;
    }
    return k;
}
void swap(int *pa,int *pb)
{
   int temp;
   temp=*pa;
   *pa=*pb;
   *pb=temp;
}
void output(int arr[],int n)
{
    int i;
    for(i=0;i<n;i++)
    {
        printf("%d ",arr[i]);
    }
}




