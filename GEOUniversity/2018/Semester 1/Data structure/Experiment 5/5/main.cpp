#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <string.h>

typedef struct
{
   int key;
}Record;
typedef struct         //������
{
	 int key;
	 int link;
}Indextable;
int Seqsrch(Record R[],int n,int k)  //˳�����     �Ӻ���ǰ
{
   int i=n-1;
   //R[0].key=k;
   while(R[i].key!=k) //�Ӻ���ǰ��
			i--;
   if(i>=0)
       return i;
   else
       return 0;
}
int BinsrchS(Indextable R[],int n,int k)   //�۰����  ��������
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
int Binsrch(Record R[],int n,int k)   //�۰����  ������ṹ�塪�����������ģ�
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
int Blocksrch(Record R[],Indextable S[],int b,int n,int k)     //�ֿ����
{
	 int i=0,j,p;
//	 while((k>S[i].key)&&(i<=b-1))  //�����������˳�����
//		  i++;
         i=BinsrchS(S,b,k); //�۰����������
	 if(i>=b)
	    return 0;
	 j=S[i].link;    //ȷ����ʼ���ҵ�λ�ã��������еõ���
	 if(i==b-1)
		  p=n-1;     //ȷ���������ҵ�λ�ã��������еõ���
	 else
		  p=S[i+1].link-1; //ȷ���������ҵ�λ�ã��������еõ���
	 while((j<p)&&(k!=R[j].key)&&(R[j].key<=S[i].key))   //��ǰ�������
		  j++;
	 if(k!=R[j].key) //û�ҵ�
	    return 0;
	 return j;
}

int main()
{
	int s,p,k,a1,a2,a3;
	Record T[10]={23,45,56,67,68,69,72,78,89,90};//����Ľṹ������
	Record R[18]={22,12,13,8,9,20,33,42,44,38,24,48,60,58,74,57,86,53};//����Ľṹ������
	Indextable S[3]={{22,0},{48,6},{86,12}};//����������84
	printf("��������Ҫ���ҵ���������˳����ң���");
	scanf("%d",&a1);
	s=Seqsrch(R,18,a1);
	if(s)
           printf("%d �ڸýṹ������ĵ�%d ��λ�� \n",R[s].key,s+1);
	else
           printf("û�ҵ�������\n");
	printf("��������Ҫ���ҵ����������۰���ң���");
	scanf("%d",&a2);
        p=Binsrch(T,10,a2);
	if(p)
           printf("%d �ڸýṹ������ĵ�%d ��λ�� \n",T[p].key,p+1);
	else
           printf("û�ҵ�������\n");
        printf("��������Ҫ���ҵ�������������ң���");
	scanf("%d",&a3);
	k=Blocksrch(R,S,3,18,a3);
	if(k)
           printf("%d �ڸýṹ������ĵ�%d ��λ�� \n",R[k].key,k+1);
	else
           printf("û�ҵ�������\n");
	return 0;
}
