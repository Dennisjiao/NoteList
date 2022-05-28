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

  #define STACK_INIT_SIZE 100       //栈空间初始分配量
  #define STACKINCREMENT 10


typedef struct BiTNode
{
    char data;
    struct BiTNode *lchild,*rchild;
}BiTNode,*BiTree;


typedef struct{
      BiTree *base;     //在栈构造之前和销毁之后，base的值为NULL
      BiTree *top;      //栈顶指针
      int stacksize;  //当前已分配的存储空间，以元素为单位
  }Stack;

  Status InitStack(Stack &S){
      //构造一个空栈S
     S.base = (BiTree *)malloc(STACK_INIT_SIZE * sizeof(BiTree));
      if(!S.base){
          exit(NotOK);  //存储分配失败
      }
      S.top = S.base;
      S.stacksize = STACK_INIT_SIZE;
      return OK;
  }

  Status GetTop(Stack S, BiTree &e){
      //若栈不空，则用e返回S的栈顶元素，并返回OK；否则返回NotOK
      if(S.top == S.base){
          return NotOK;
      }
      e = *(S.top-1);
      return OK;
  }

  Status Push(Stack &S, BiTree e){
      //插入元素e为新的栈顶元素
      if(S.top - S.base >= S.stacksize){
          //栈满，追加存储空间
          S.base = (BiTree *)realloc(S.base, (S.stacksize + STACKINCREMENT)*sizeof(BiTree));
          if(!S.base){
              //存储分配失败
             exit(NotOK);
          }
          S.top = S.base + S.stacksize;
          S.stacksize += STACKINCREMENT;
      }
      *S.top++ = e;
      return OK;
  }

  Status Pop(Stack &S, BiTree &e){
      //若栈不空，则删除S的栈顶元素，用e返回其值，并返回OK；否则返回NotOK
      if(S.top == S.base){
        return NotOK;
    }
    e = * --S.top;
      return OK;
}

 bool StackIsEmpty(Stack &S){
     //若栈为空，返回true；若栈非空，返回false
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



//创建二叉树
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


//前序遍历
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

//中序遍历
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

//后序遍历
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

//遍历二叉树
int Howmuch(BiTree T,int h)
{
    BiTNode *Q[100];
    if(T==NULL)
        printf("空的二叉树\n");
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

//计算树的深度
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

//功能选择
int choose(BiTree T)
{
    int a;
    scanf("%d",&a);
    if(a==1)
    {
        printf("先序遍历\n");
        PreTravel(T);
    }
    else if(a==2)
    {
        printf("中序遍历\n");
        MidTravel(T);
    }
    else if(a==3)
    {
        printf("后序遍历\n");
        PostTravel(T);
    }
    else if(a==4)
    {
        printf("按层遍历\n");
        Howmuch(T,2);
    }
    else if(a==5)
    {
        printf("总结点数: \n");
        Howmuch(T,0);
    }
    else if(a==6)
    {
        printf("总叶子数: \n");
        Howmuch(T,1);
    }
    else if(a==7)
    {
        printf("树的深度: \n");
        printf("%d",Depth(T));
    }
    else if(a==8)
    {
        printf("非递归前序遍历：\n");
        PreOrderTraverse(T);
    }
    else if(a==9)
    {
        printf("非递归中序遍历：\n");
        MidOrder_2(T);

    }
    else if(a==0)
        exit(1);
    else printf("没有这个操作\n");
    printf("操作完成，请输入下一个操作\n");
    choose(T);
    return 0;
}

int main()
{
    printf("---------------------------二叉树基本操作----------------------------\n");
    printf("请先建立二叉树，按先序的方式输入如果数据为空输入#,例如:ABC##DE#G##F###\n");
    BiTree T;
    CreatBiTree(T);
    printf("1.进行先序遍历\n");
    printf("2.进行中序遍历\n");
    printf("3.进行后序遍历\n");
    printf("4.进行按层遍历\n");
    printf("5.总节点数个数\n");
    printf("6.总叶子数个数\n");
    printf("7.树的深度\n");
    printf("8.非递归前序遍历\n");
    printf("9.非递归中序遍历\n");
    printf("0.结束\n");
    choose(T);
    return 0;
}
