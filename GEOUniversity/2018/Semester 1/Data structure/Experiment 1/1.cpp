#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <string.h>
#define  OK 1
#define ERROR -1

typedef int ElemType;
typedef int status ;

//单链表结点定义
typedef struct node
{
	ElemType data;
	struct node *next ;
}node,*link;

//单链表基本操作
//status init_List(link &L); //带头结点的单链表表初始化
//status insertlist(link &L,int i,ElemType e)//向表中第i个位置插入数据
//status dellist(link &L,int i,ElemType &e)//从表中删除第i个位置数据
//status createlist(link &L )//建立带头结点单链表
//link locateList(link L,int e,int (*compare)(int ,int));//按某种比较方式找和e相关的元素，找到返回元素所在结点地址，找不到返回空
//void traveList(link L)//遍历带头结点单链表

//其他操作
//void unionlist(link &LA,link LB);//求集合LA和LB的并到LA中
//void intersection_list(link &LA,link LB)//求两个集合的交集
//status equal(ElemType a,ElemType b);//判断a和b是否相等

status equal(ElemType a,ElemType b)
{
	return a==b;
}

status init_List(link &L) //带头结点的单链表表初始化
{
//此处写入代码
    link p;
    p = (link)malloc(sizeof(node));
    if (!p) return ERROR;
    p->next = NULL;
    L = p;
	return OK ;
}


link locateList(link L,int e,int (*compare)(int ,int))//按某种比较方式找和e相关的元素，找到返回元素所在结点地址，找不到返回空
{
	link p=L->next ;
	while( p )
	{
		if(compare(p->data,e)) return p ;
        p = p->next;
	}
	return NULL  ;
}
void traveList(link L)//遍历带头结点单链表
{
	link p=L->next ;
	while( p )
	{
		printf("%d ",p->data);
        p = p->next;
	}
}

status createlist(link &L )//建立带头结点单链表
{
	int n ,i;
	link s;
	link p;
	printf("输入数据个数：");
	scanf("%d",&n) ;
	printf("输入%d所有数据：\n",n);
	L=(link)malloc(sizeof(node)) ;
	if(!L) return 0;
	L->next=NULL ;
	for(i=1;i<=n;i++)
	{
		s=(link)malloc(sizeof(node)) ;
		if(!s) return 0;
		s->next=NULL ;
		scanf("%d",&s->data);
		if (!L->next)
        {
            L->next = s;
            p = s;
        }
        else
        {
            p->next = s;
            p = s;
        }
	}
	return 1;
}
status insertlist(link &L,int i,ElemType e)//向带头结点的单链表中第i个位置插入数据
{
//此处写入代码
    link p = L;
    link q = NULL;
    link s;
    int j;
    for (j=1;j<i&&!p;j++,p=p->next);
    if (!p) return ERROR;
    q = p->next;
    s = (link)malloc(sizeof(node));
    if (!s) return ERROR;
    s->data = e;
    s->next = q;
    p->next = s;
	return OK ;
}

status dellist(link &L,int i,ElemType &e)//从向带头结点的单链表中删除第i个位置数据
{
//此处写入代码
    link p = L;
    link q = NULL;
    int j;
    for (j=1;j<i&&p;j++,p=p->next);
    if (!p) return ERROR;
    q = p->next;
    p->next = q->next;
    e = q->data;
    free(q);
	return OK ;
}

void unionlist(link &LA,link LB)//求两个集合的并集
{
	ElemType x;
	link p ;
	p=LB->next ;
	while(p)
	{
		x=p->data;
		if(!locateList(LA,x,equal))
			insertlist(LA,1,x) ;
		p=p->next ;
	}
}

void intersection_list(link &LA,link LB)//求两个集合的交集
{
	ElemType x;
	link p;
	link q = NULL;
	int i = 1;
	int e;
	q = LA;
	while(q->next)
	{
	    p = q->next;
		x=p->data;
		if(!locateList(LB,x,equal))
        {
            dellist(LA,i,e);
            printf("***%d***\n",e);
        }
        else
        {
            i++;
            q = q->next;
        }
	}
}
int main()
{
	link LA,LB ;
	int i ,n,temp;
	printf("建立集合A：");
	createlist(LA) ;
	printf("建立集合B：");
	createlist(LB) ;
	printf("求A=AUB，按1\n");
	printf("求A=AnB，按2\n");
	scanf("%d",&i);
	if(i==1)
	{
			unionlist(LA,LB);
			printf("两个集合的并集为：");
	}
	if(i==2)
	{
			intersection_list(LA,LB) ;
			printf("两个集合的交集为：");
	}
	traveList(LA);

	system("pause");
	return 0;
}
