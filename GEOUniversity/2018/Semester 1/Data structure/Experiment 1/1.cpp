#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <string.h>
#define  OK 1
#define ERROR -1

typedef int ElemType;
typedef int status ;

//�������㶨��
typedef struct node
{
	ElemType data;
	struct node *next ;
}node,*link;

//�������������
//status init_List(link &L); //��ͷ���ĵ�������ʼ��
//status insertlist(link &L,int i,ElemType e)//����е�i��λ�ò�������
//status dellist(link &L,int i,ElemType &e)//�ӱ���ɾ����i��λ������
//status createlist(link &L )//������ͷ��㵥����
//link locateList(link L,int e,int (*compare)(int ,int));//��ĳ�ֱȽϷ�ʽ�Һ�e��ص�Ԫ�أ��ҵ�����Ԫ�����ڽ���ַ���Ҳ������ؿ�
//void traveList(link L)//������ͷ��㵥����

//��������
//void unionlist(link &LA,link LB);//�󼯺�LA��LB�Ĳ���LA��
//void intersection_list(link &LA,link LB)//���������ϵĽ���
//status equal(ElemType a,ElemType b);//�ж�a��b�Ƿ����

status equal(ElemType a,ElemType b)
{
	return a==b;
}

status init_List(link &L) //��ͷ���ĵ�������ʼ��
{
//�˴�д�����
    link p;
    p = (link)malloc(sizeof(node));
    if (!p) return ERROR;
    p->next = NULL;
    L = p;
	return OK ;
}


link locateList(link L,int e,int (*compare)(int ,int))//��ĳ�ֱȽϷ�ʽ�Һ�e��ص�Ԫ�أ��ҵ�����Ԫ�����ڽ���ַ���Ҳ������ؿ�
{
	link p=L->next ;
	while( p )
	{
		if(compare(p->data,e)) return p ;
        p = p->next;
	}
	return NULL  ;
}
void traveList(link L)//������ͷ��㵥����
{
	link p=L->next ;
	while( p )
	{
		printf("%d ",p->data);
        p = p->next;
	}
}

status createlist(link &L )//������ͷ��㵥����
{
	int n ,i;
	link s;
	link p;
	printf("�������ݸ�����");
	scanf("%d",&n) ;
	printf("����%d�������ݣ�\n",n);
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
status insertlist(link &L,int i,ElemType e)//���ͷ���ĵ������е�i��λ�ò�������
{
//�˴�д�����
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

status dellist(link &L,int i,ElemType &e)//�����ͷ���ĵ�������ɾ����i��λ������
{
//�˴�д�����
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

void unionlist(link &LA,link LB)//���������ϵĲ���
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

void intersection_list(link &LA,link LB)//���������ϵĽ���
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
	printf("��������A��");
	createlist(LA) ;
	printf("��������B��");
	createlist(LB) ;
	printf("��A=AUB����1\n");
	printf("��A=AnB����2\n");
	scanf("%d",&i);
	if(i==1)
	{
			unionlist(LA,LB);
			printf("�������ϵĲ���Ϊ��");
	}
	if(i==2)
	{
			intersection_list(LA,LB) ;
			printf("�������ϵĽ���Ϊ��");
	}
	traveList(LA);

	system("pause");
	return 0;
}
