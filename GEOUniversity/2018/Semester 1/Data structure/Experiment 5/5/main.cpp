#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <string.h>

typedef struct
{
   int key;
}Record;
typedef struct         //索引表
{
	 int key;
	 int link;
}Indextable;
int Seqsrch(Record R[],int n,int k)  //顺序查找     从后往前
{
   int i=n-1;
   //R[0].key=k;
   while(R[i].key!=k) //从后往前找
			i--;
   if(i>=0)
       return i;
   else
       return 0;
}
int BinsrchS(Indextable R[],int n,int k)   //折半查找  （索引表）
{
	 int low,hight,mid,found;
	 low=0;
	 hight=n-1;
	 found=0;
	 while((low<=hight)&&(found==0))
	 {
	 	   mid=(low+hight)/2;
	 	   if(k>R[mid].key)
			low=mid+1;
		   else if(k>=R[mid].key&&k<=R[mid+1].key)
		   {
			  found=1;
			  break;
		   }
		   else
		      hight=mid-1;
		   if(k<=R[mid].key&&k>=R[mid-1].key)
		   {
		       found=1;
		       break;
		   }
	 }
	 if(found==1)
		  return mid;
	 else
		  return 0;
}
int Binsrch(Record R[],int n,int k)   //折半查找  （有序结构体————递增的）
{
	 int low,hight,mid,found;
	 low=0;
	 hight=n-1;
	 found=0;
	 while((low<=hight)&&(found==0))
	 {
	 	   mid=(low+hight)/2;
	 	   if(k>R[mid].key)
			low=mid+1;
		   else if(k==R[mid].key)
		         found=1;
		   else
			hight=mid-1;
	 }
	 if(found==1)
		  return mid;
	 else
		  return 0;
}
int Blocksrch(Record R[],Indextable S[],int b,int n,int k)     //分块查找
{
	 int i=0,j,p;
//	 while((k>S[i].key)&&(i<=b-1))  //在索引表表中顺序查找
//		  i++;
         i=BinsrchS(S,b,k); //折半查找索引表
	 if(i>=b)
	    return 0;
	 j=S[i].link;    //确定开始查找的位置（索引表中得到）
	 if(i==b-1)
		  p=n-1;     //确定结束查找的位置（索引表中得到）
	 else
		  p=S[i+1].link-1; //确定结束查找的位置（索引表中得到）
	 while((j<p)&&(k!=R[j].key)&&(R[j].key<=S[i].key))   //从前往后查找
		  j++;
	 if(k!=R[j].key) //没找到
	    return 0;
	 return j;
}

int main()
{
	int s,p,k,a1,a2,a3;
	Record T[10]={23,45,56,67,68,69,72,78,89,90};//有序的结构体数组
	Record R[18]={22,12,13,8,9,20,33,42,44,38,24,48,60,58,74,57,86,53};//无序的结构体数组
	Indextable S[3]={{22,0},{48,6},{86,12}};//索引表数组84
	printf("请输入你要查找的数（无序顺序查找）：");
	scanf("%d",&a1);
	s=Seqsrch(R,18,a1);
	if(s)
           printf("%d 在该结构体数组的第%d 个位置 \n",R[s].key,s+1);
	else
           printf("没找到该数！\n");
	printf("请输入你要查找的数（有序折半查找）：");
	scanf("%d",&a2);
        p=Binsrch(T,10,a2);
	if(p)
           printf("%d 在该结构体数组的第%d 个位置 \n",T[p].key,p+1);
	else
           printf("没找到该数！\n");
        printf("请输入你要查找的数（索引表查找）：");
	scanf("%d",&a3);
	k=Blocksrch(R,S,3,18,a3);
	if(k)
           printf("%d 在该结构体数组的第%d 个位置 \n",R[k].key,k+1);
	else
           printf("没找到该数！\n");
	return 0;
}
