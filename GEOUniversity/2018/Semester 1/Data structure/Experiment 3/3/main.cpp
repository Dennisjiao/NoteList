#include <iostream>
#include<stdio.h>
#include<stdlib.h.>
#include<malloc.h>
#include <stack>
#include <string.h>
#include <queue>
using namespace std;
#define MAX_TREE_SIZE 100

typedef int Status;
   #define OK 0
   #define NotOK 1

  #define STACK_INIT_SIZE 100       //ջ�ռ��ʼ������
  #define STACKINCREMENT 10


typedef struct BiTNode
{
    char data;
    struct BiTNode *lchild,*rchild;
}BiTNode,*BiTree;


typedef struct{
      BiTree *base;     //��ջ����֮ǰ������֮��base��ֵΪNULL
      BiTree *top;      //ջ��ָ��
      int stacksize;  //��ǰ�ѷ���Ĵ洢�ռ䣬��Ԫ��Ϊ��λ
  }Stack;

  Status InitStack(Stack &S){
      //����һ����ջS
     S.base = (BiTree *)malloc(STACK_INIT_SIZE * sizeof(BiTree));
      if(!S.base){
          exit(NotOK);  //�洢����ʧ��
      }
      S.top = S.base;
      S.stacksize = STACK_INIT_SIZE;
      return OK;
  }

  Status GetTop(Stack S, BiTree &e){
      //��ջ���գ�����e����S��ջ��Ԫ�أ�������OK�����򷵻�NotOK
      if(S.top == S.base){
          return NotOK;
      }
      e = *(S.top-1);
      return OK;
  }

  Status Push(Stack &S, BiTree e){
      //����Ԫ��eΪ�µ�ջ��Ԫ��
      if(S.top - S.base >= S.stacksize){
          //ջ����׷�Ӵ洢�ռ�
          S.base = (BiTree *)realloc(S.base, (S.stacksize + STACKINCREMENT)*sizeof(BiTree));
          if(!S.base){
              //�洢����ʧ��
             exit(NotOK);
          }
          S.top = S.base + S.stacksize;
          S.stacksize += STACKINCREMENT;
      }
      *S.top++ = e;
      return OK;
  }

  Status Pop(Stack &S, BiTree &e){
      //��ջ���գ���ɾ��S��ջ��Ԫ�أ���e������ֵ��������OK�����򷵻�NotOK
      if(S.top == S.base){
        return NotOK;
    }
    e = * --S.top;
      return OK;
}

 bool StackIsEmpty(Stack &S){
     //��ջΪ�գ�����true����ջ�ǿգ�����false
     if(S.top == S.base){
         return true;
     }
     else{
         return false;
     }
 }

Status PreOrderTraverse(BiTree T){
     if( T == NULL ){
         return NotOK;
     }
     else{

         Stack TreeStack;
         InitStack(TreeStack);
         while( T!=NULL || !StackIsEmpty(TreeStack)){
             while(T != NULL){
                 printf("%c",T->data);
                 Push(TreeStack,T);
                 T = T->lchild;
             }
             if(!StackIsEmpty(TreeStack)){
                 Pop(TreeStack,T);
                 T = T->rchild;
             }
         }
         printf("\n");
         return OK;
     }
 }
Status MidOrder_2(BiTree T)
{
    if( T == NULL ){
         return NotOK;
     }
     else{
    Stack TreeStack;
    InitStack(TreeStack);
    while( T!=NULL || !StackIsEmpty(TreeStack))
    {
        if(T!=NULL)
        {
            Push(TreeStack,T);
            T=T->lchild;
        }
        else
        {
            Pop(TreeStack,T);
            printf("%c",T->data);
            T=T->rchild;
        }
    }
    }
    return 0;
}



//����������
int CreatBiTree(BiTree &T)
{
    char ch;
    if((ch=getchar())=='#')
        T=NULL;
    else
    {
        T=(BiTNode*)malloc(sizeof(BiTNode));
        if(!T)
           exit(1);
        T->data=ch;
        CreatBiTree(T->lchild);
        CreatBiTree(T->rchild);
    }
    return 0;
}


//ǰ�����
int PreTravel(BiTree &T)
{
    if(T)
    {
        printf("%c",T->data);
        PreTravel(T->lchild);
        PreTravel(T->rchild);
    }
    return 0;
}

//�������
int MidTravel(BiTree &T)
{
    if(T)
    {
        MidTravel(T->lchild);
        printf("%c",T->data);
        MidTravel(T->rchild);
    }
    return 0;
}

//�������
int PostTravel(BiTree &T)
{
    if(T)
    {
        PostTravel(T->lchild);
        PostTravel(T->rchild);
        printf("%c",T->data);
    }
    return 0;

}

//����������
int Howmuch(BiTree T,int h)
{
    BiTNode *Q[100];
    if(T==NULL)
        printf("�յĶ�����\n");
    Q[0]=T;
    int i,k=0;
    int j=1;
    for(i=0;i<j;i++)
    {
        if(Q[i]->lchild!=NULL)
        {
            Q[j]=Q[i]->lchild;
            j++;
        }
        if(Q[i]->rchild!=NULL)
        {
            Q[j]=Q[i]->lchild;
            j++;
        }
        if(Q[i]->lchild==NULL&&Q[i]->rchild==NULL)
            k++;
    }
    if(h==0)
        printf("%d",j);
    else if(h==1)
        printf("%d",k);
    else if(h==2)
    {
        for(i=0;i<j;i++)
            printf("%c",Q[i]->data);
    }
    else
    {
        printf("EEROR");
    }
    return 0;
}

//�����������
int Depth(BiTree &T)
{
    int lh,rh;
    if(NULL == T)
    {
        return 0;
    }
    else
    {
        lh = Depth(T->lchild);
        rh = Depth(T->rchild);
        return (lh>rh?lh:rh)+1;
    }
}

//����ѡ��
int choose(BiTree T)
{
    int a;
    scanf("%d",&a);
    if(a==1)
    {
        printf("�������\n");
        PreTravel(T);
    }
    else if(a==2)
    {
        printf("�������\n");
        MidTravel(T);
    }
    else if(a==3)
    {
        printf("�������\n");
        PostTravel(T);
    }
    else if(a==4)
    {
        printf("�������\n");
        Howmuch(T,2);
    }
    else if(a==5)
    {
        printf("�ܽ����: \n");
        Howmuch(T,0);
    }
    else if(a==6)
    {
        printf("��Ҷ����: \n");
        Howmuch(T,1);
    }
    else if(a==7)
    {
        printf("�������: \n");
        printf("%d",Depth(T));
    }
    else if(a==8)
    {
        printf("�ǵݹ�ǰ�������\n");
        PreOrderTraverse(T);
    }
    else if(a==9)
    {
        printf("�ǵݹ����������\n");
        MidOrder_2(T);

    }
    else if(a==0)
        exit(1);
    else printf("û���������\n");
    printf("������ɣ���������һ������\n");
    choose(T);
    return 0;
}

int main()
{
    printf("---------------------------��������������----------------------------\n");
    printf("���Ƚ�����������������ķ�ʽ�����������Ϊ������#,����:ABC##DE#G##F###\n");
    BiTree T;
    CreatBiTree(T);
    printf("1.�����������\n");
    printf("2.�����������\n");
    printf("3.���к������\n");
    printf("4.���а������\n");
    printf("5.�ܽڵ�������\n");
    printf("6.��Ҷ��������\n");
    printf("7.�������\n");
    printf("8.�ǵݹ�ǰ�����\n");
    printf("9.�ǵݹ��������\n");
    printf("0.����\n");
    choose(T);
    return 0;
}
