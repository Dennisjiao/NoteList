#include<iostream>
#include<cstdio>
#include<cstring>
#include<algorithm>
#include<cmath>
#include<queue>
#include<stack>
#include<map>
#include<sstream>
using namespace std;
typedef long long ll;
const int maxn = 1e3 + 10;
const int INF = 1 << 30;
int T, n, m;
struct edge
{
    int from, to, dist;
    edge(int u, int v, int d):from(u), to(v), dist(d){}
    edge(){}
};
struct Heapnode
{
    int d, u;//d为距离，u为起点
    Heapnode(){}
    Heapnode(int d, int u):d(d), u(u){}
    bool operator <(const Heapnode & a)const
    {
        return d > a.d;//这样优先队列先取出d小的
    }
};
struct Dijkstra
{
    int n, m;
    vector<edge>edges;//存边的信息
    vector<int>G[maxn];//G[i]表示起点为i的边的序号集
    bool v[maxn];//标记点是否加入集合
    int d[maxn];//起点s到各个点的最短路
    int p[maxn];//倒叙记录路径
    Dijkstra(){}
    void init(int n)
    {
        this -> n = n;
        for(int i = 0; i < n; i++)G[i].clear();
        edges.clear();
    }
    void addedge(int from, int to, int dist)
    {
        edges.push_back(edge(from, to, dist));
        m = edges.size();
        G[from].push_back(m - 1);//存以from为起点的下一条边
    }
    void dijkstra(int s)//以s为起点
    {
        priority_queue<Heapnode>q;
        for(int i = 0; i < n; i++)d[i] = INF;
        d[s] = 0;
        memset(v, 0, sizeof(v));
        memset(p, -1, sizeof(p));
        q.push(Heapnode(0, s));
        while(!q.empty())
        {
            Heapnode now = q.top();
            q.pop();
            int u = now.u;//当前起点
            if(v[u])continue;//如果已经加入集合，continue
            v[u] = 1;
            for(int i = 0; i < G[u].size(); i++)
            {
                edge& e = edges[G[u][i]];//引用节省代码
                if(d[e.to] > d[u] + e.dist)
                {
                    d[e.to] = d[u] + e.dist;
                    p[e.to] = G[u][i];//记录e.to前的边的编号，p存的是边的下标,这样可以通过边找出之前的点以及每条路的路径，如果用邻接矩阵存储的话这里可以直接存节点u
                    q.push(Heapnode(d[e.to], e.to));
                }
            }
        }
    }
    void output(int u)
    {
        for(int i = 0; i < n; i++)
        {
            if(i == u)continue;
            printf("从%d到%d距离是：%2d   ", u, i, d[i]);
            stack<int>q;//存的是边的编号
            int x = i;//x就是路径上所有的点
            while(p[x] != -1)
            {
                q.push(x);
                x = edges[p[x]].from;//x变成这条边的起点
            }
            cout<<u;
            while(!q.empty())
            {
                cout<<"->"<<q.top();
                q.pop();
            }
            cout<<endl;
        }
    }
};
Dijkstra ans;
int main()
{
    cout<<"分别输入顶点个数和路径条数：";
    cin >> n >> m;
    ans.init(n);
    cout<<"分别输入起点，终点，权重：\n";
    for(int i = 0; i < m; i++)
    {
        int u, v, w;
        cin >> u >> v >> w;
        ans.addedge(u, v, w);
    }
    int u = 0;
    cout<<"输出结果为：\n";
    ans.dijkstra(u);
    ans.output(u);
}
