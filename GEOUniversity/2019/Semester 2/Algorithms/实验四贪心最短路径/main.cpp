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
    int d, u;//dΪ���룬uΪ���
    Heapnode(){}
    Heapnode(int d, int u):d(d), u(u){}
    bool operator <(const Heapnode & a)const
    {
        return d > a.d;//�������ȶ�����ȡ��dС��
    }
};
struct Dijkstra
{
    int n, m;
    vector<edge>edges;//��ߵ���Ϣ
    vector<int>G[maxn];//G[i]��ʾ���Ϊi�ıߵ���ż�
    bool v[maxn];//��ǵ��Ƿ���뼯��
    int d[maxn];//���s������������·
    int p[maxn];//�����¼·��
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
        G[from].push_back(m - 1);//����fromΪ������һ����
    }
    void dijkstra(int s)//��sΪ���
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
            int u = now.u;//��ǰ���
            if(v[u])continue;//����Ѿ����뼯�ϣ�continue
            v[u] = 1;
            for(int i = 0; i < G[u].size(); i++)
            {
                edge& e = edges[G[u][i]];//���ý�ʡ����
                if(d[e.to] > d[u] + e.dist)
                {
                    d[e.to] = d[u] + e.dist;
                    p[e.to] = G[u][i];//��¼e.toǰ�ıߵı�ţ�p����Ǳߵ��±�,��������ͨ�����ҳ�֮ǰ�ĵ��Լ�ÿ��·��·����������ڽӾ���洢�Ļ��������ֱ�Ӵ�ڵ�u
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
            printf("��%d��%d�����ǣ�%2d   ", u, i, d[i]);
            stack<int>q;//����Ǳߵı��
            int x = i;//x����·�������еĵ�
            while(p[x] != -1)
            {
                q.push(x);
                x = edges[p[x]].from;//x��������ߵ����
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
    cout<<"�ֱ����붥�������·��������";
    cin >> n >> m;
    ans.init(n);
    cout<<"�ֱ�������㣬�յ㣬Ȩ�أ�\n";
    for(int i = 0; i < m; i++)
    {
        int u, v, w;
        cin >> u >> v >> w;
        ans.addedge(u, v, w);
    }
    int u = 0;
    cout<<"������Ϊ��\n";
    ans.dijkstra(u);
    ans.output(u);
}
