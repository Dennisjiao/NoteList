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
    int    vexnum,arcnum;                            // 图当前顶点数和弧数
    char vexs[MAX_VERTEX_NUM];                    // 顶点向量
    int    acrs[MAX_VERTEX_NUM][MAX_VERTEX_NUM];    // 邻接矩阵
}MGraph ;
typedef struct {
    int adjvex;//顶点
    int lowcost;//权值
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

int Locatevex(MGraph G,char v)//寻找顶点的下标
{
	int i ;
	for(i=0; i<G.vexnum ;i++)
		if(G.vexs[i]==v) return i ;
	return i ;
}
void CreateDN(MGraph &G)//邻接矩阵存储
{
	int i,j,k,flog,qz;
    printf("开始建立图\n") ;
	printf("请输入该图的顶点个数\n") ;
	scanf("%d",&G.vexnum );
	printf("请输入该图的边的个数\n") ;
	scanf("%d",&G.arcnum);
	fflush(stdin) ; //清空输入缓冲
    for(j=0;j<G.vexnum ;j++)
	{

		for(k=0;k<G.vexnum ;k++)
		{
            G.acrs[j][k]=inifity;
		}
	}
	printf("请输入各个顶点数据(将顶点字母连续输入按回车结束)\n") ;
	for(i=0; i<G.vexnum ;i++)
	{
		scanf("%c",&G.vexs[i]);
	}
	printf("该图是否有权值\n是：输入1\n否：输入0\n") ;
    scanf("%d",&flog);
	char v1,v2 ;
	int x1,x2 ; //输入顶点在g数组中的下标
	for(i=0 ;i<G.arcnum;i++)
	{
		printf("请输入第%d条边(直接输入边的两个顶点中间没有任何间隔)\n",i+1) ;
		fflush(stdin) ; //清空输入缓冲
		scanf(" %c%c",&v1,&v2) ;
		//确定该边两个顶点在g中的下标
		x1=Locatevex(G,v1) ;
		x2=Locatevex(G,v2) ;
        if(flog==0)
        {
            G.acrs[x1][x2]=1;
            G.acrs[x2][x1]=G.acrs[x1][x2];
        }
        if(flog==1)
        {
            printf("请输入%c-%c的权值",v1,v2);
            scanf("%d",&qz);
            G.acrs[x1][x2]=qz;
            G.acrs[x2][x1]=G.acrs[x1][x2];
        }
	}
}
int AdjVes(MGraph *Mg,int k)//找与下标为k的顶点相连且没被访问过的顶点并返回其下标
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
void MDFS(MGraph *Mg,int i)//深度优先遍历
{
    int k;
    visited[i]=1;
    printf("%c",Mg->vexs[i]);
    for(k=AdjVes(Mg,i);k>=0;k=AdjVes(Mg,k))
    {
        if(visited[k]==0)
        {
            MDFS(Mg,k);// 递归
        }
    }
}
void MBFS(MGraph *Mg )//广度优先遍历
{
    int i,w,u;
    for(i=0;i<Mg->vexnum;i++)
    {
        visited[i]=0;
    }
    InitQueue();
    for(i=0;i<Mg->vexnum;++i)
    {
        if(visited[i]==0)                        // 没被访问过
        {
            visited[i]=1;
            printf("%c",Mg->vexs[i]);
            EnQueue(&Q,i);
            while(!QueueEmpty(&Q))
            {
                u=DeQueue(&Q);
                for(w=AdjVes(Mg,u);w>=0;w=AdjVes(Mg,u))
                {
                    if(visited[w]==0)            // 没被访问过
                    {
                        visited[w]=1;
                        printf("%c",Mg->vexs[w]);
                        EnQueue(&Q,w);            // 入队
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
        for(j=0;j<G.vexnum;j++)//新顶点并入重新寻找最小边

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
    printf("邻接矩阵深度优先遍历MDFS：\t");
    for(i=0;i<Mg.vexnum;i++)
        visited[i]=0;
    for(i=0;i<Mg.vexnum;i++)
    {
        if(visited[i]==0)
        MDFS(&Mg,i);
    }
    printf("\n邻接矩阵广度优先遍历MBFS：\t");
    MBFS(&Mg);
    printf("\n");
    printf("是否生成最小生成树\n是：输入1\n否：输入0\n");
    scanf("%d",&flog);
    if(flog==1)
    {
        printf(" 输入你开始构建最小生成树的起始点\n");
        scanf(" %c",&a);
        MTree_PRIM(Mg,a);
    }
    if(flog==0)
        return 0;
}
