#include <stdio.h>
#include <malloc.h>
#include <stddef.h>
#include <math.h>
#define OK 1
#define ERROR 0
#define MAX_VERTEX_NUM     33
#define inifity 10000
int visited[MAX_VERTEX_NUM];
typedef struct
{
    int    vexnum,arcnum;                            // ͼ��ǰ�������ͻ���
    char vexs[MAX_VERTEX_NUM];                    // ��������
    int    acrs[MAX_VERTEX_NUM][MAX_VERTEX_NUM];    // �ڽӾ���
}MGraph ;
typedef struct {
    int adjvex;//����
    int lowcost;//Ȩֵ
}helparray;
typedef struct Queue
{
    int arry[MAX_VERTEX_NUM];
    int front,rear;
}Queue;
Queue Q;

void InitQueue()
{
    Q.front=Q.rear=0;
}

int  QueueEmpty(Queue *Q)
{
    if(Q->front==Q->rear)
    return 1;
    else
    return 0;
}

void EnQueue(Queue *Q,int w)
{
    if((Q->rear+1)%MAX_VERTEX_NUM==Q->front)
    printf("Error!");
    else
    {
    Q->arry[Q->rear]=w;
    Q->rear=(Q->rear+1)%MAX_VERTEX_NUM;
    }
}

int DeQueue(Queue *Q)
{
    int u;
    if(Q->front==Q->rear)
    return -1;
    u=Q->front;
    Q->front=(Q->front+1)%MAX_VERTEX_NUM;
    return u;
}

int Locatevex(MGraph G,char v)//Ѱ�Ҷ�����±�
{
	int i ;
	for(i=0; i<G.vexnum ;i++)
		if(G.vexs[i]==v) return i ;
	return i ;
}
void CreateDN(MGraph &G)//�ڽӾ���洢
{
	int i,j,k,flog,qz;
    printf("��ʼ����ͼ\n") ;
	printf("�������ͼ�Ķ������\n") ;
	scanf("%d",&G.vexnum );
	printf("�������ͼ�ıߵĸ���\n") ;
	scanf("%d",&G.arcnum);
	fflush(stdin) ; //������뻺��
    for(j=0;j<G.vexnum ;j++)
	{

		for(k=0;k<G.vexnum ;k++)
		{
            G.acrs[j][k]=inifity;
		}
	}
	printf("�����������������(��������ĸ�������밴�س�����)\n") ;
	for(i=0; i<G.vexnum ;i++)
	{
		scanf("%c",&G.vexs[i]);
	}
	printf("��ͼ�Ƿ���Ȩֵ\n�ǣ�����1\n������0\n") ;
    scanf("%d",&flog);
	char v1,v2 ;
	int x1,x2 ; //���붥����g�����е��±�
	for(i=0 ;i<G.arcnum;i++)
	{
		printf("�������%d����(ֱ������ߵ����������м�û���κμ��)\n",i+1) ;
		fflush(stdin) ; //������뻺��
		scanf(" %c%c",&v1,&v2) ;
		//ȷ���ñ�����������g�е��±�
		x1=Locatevex(G,v1) ;
		x2=Locatevex(G,v2) ;
        if(flog==0)
        {
            G.acrs[x1][x2]=1;
            G.acrs[x2][x1]=G.acrs[x1][x2];
        }
        if(flog==1)
        {
            printf("������%c-%c��Ȩֵ",v1,v2);
            scanf("%d",&qz);
            G.acrs[x1][x2]=qz;
            G.acrs[x2][x1]=G.acrs[x1][x2];
        }
	}
}
int AdjVes(MGraph *Mg,int k)//�����±�Ϊk�Ķ���������û�����ʹ��Ķ��㲢�������±�
{
    int i;
    for(i=0;i<Mg->vexnum;i++)
    {
        if(Mg->acrs[k][i]!=10000&&(visited[i])==0)
        {
            return i;
        }
    }
    return -1;
}
void MDFS(MGraph *Mg,int i)//������ȱ���
{
    int k;
    visited[i]=1;
    printf("%c",Mg->vexs[i]);
    for(k=AdjVes(Mg,i);k>=0;k=AdjVes(Mg,k))
    {
        if(visited[k]==0)
        {
            MDFS(Mg,k);// �ݹ�
        }
    }
}
void MBFS(MGraph *Mg )//������ȱ���
{
    int i,w,u;
    for(i=0;i<Mg->vexnum;i++)
    {
        visited[i]=0;
    }
    InitQueue();
    for(i=0;i<Mg->vexnum;++i)
    {
        if(visited[i]==0)                        // û�����ʹ�
        {
            visited[i]=1;
            printf("%c",Mg->vexs[i]);
            EnQueue(&Q,i);
            while(!QueueEmpty(&Q))
            {
                u=DeQueue(&Q);
                for(w=AdjVes(Mg,u);w>=0;w=AdjVes(Mg,u))
                {
                    if(visited[w]==0)            // û�����ʹ�
                    {
                        visited[w]=1;
                        printf("%c",Mg->vexs[w]);
                        EnQueue(&Q,w);            // ���
                    }
                }
            }
        }
    }
}
int minimum(helparray dge[],MGraph G)
{
    int i,k;
    for(i=0;i<G.vexnum;i++)
    {
        if(dge[i].lowcost!=0)
            break;
    }
    k=i;
    for(i=k+1;i<G.vexnum;i++)
    {
        if(dge[i].lowcost<dge[k].lowcost&&dge[i].lowcost!=0)
        {
            k=i;
        }
    }
    return k;
}
void MTree_PRIM(MGraph G,char u)
{
    int i,j, k;
    helparray dge[MAX_VERTEX_NUM];
    k=Locatevex(G,u);
    for(j=0;j<G.vexnum;j++)
    {
         if(j!=k)
         {
            dge[j].adjvex=u;
            dge[j].lowcost=G.acrs[k][j];
         }
    }
    dge[k].lowcost=0;
    for(i=1;i<G.vexnum;i++)
    {
        k=minimum(dge,G);
        printf("%c--%c",dge[k].adjvex,G.vexs[k]);
        printf("\n");
        dge[k].lowcost=0;
        for(j=0;j<G.vexnum;j++)//�¶��㲢������Ѱ����С��

        {
            if(G.acrs[k][j]<dge[j].lowcost)
            {
                dge[j].adjvex=G.vexs[k];
                dge[j].lowcost=G.acrs[k][j];
            }
        }
    }
}
int main()
{
    int i ,flog;
    char a;
    MGraph Mg;
    CreateDN(Mg);
    printf("�ڽӾ���������ȱ���MDFS��\t");
    for(i=0;i<Mg.vexnum;i++)
        visited[i]=0;
    for(i=0;i<Mg.vexnum;i++)
    {
        if(visited[i]==0)
        MDFS(&Mg,i);
    }
    printf("\n�ڽӾ��������ȱ���MBFS��\t");
    MBFS(&Mg);
    printf("\n");
    printf("�Ƿ�������С������\n�ǣ�����1\n������0\n");
    scanf("%d",&flog);
    if(flog==1)
    {
        printf(" �����㿪ʼ������С����������ʼ��\n");
        scanf(" %c",&a);
        MTree_PRIM(Mg,a);
    }
    if(flog==0)
        return 0;
}
