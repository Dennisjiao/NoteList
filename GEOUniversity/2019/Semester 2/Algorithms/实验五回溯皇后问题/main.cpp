
#include <iostream>
using namespace std;


int isconflict(int *x,int k)
{
    for(int i=1; i<k; i++)
    {
        if(x[i]==x[k]||(x[i]+i)==(x[k]+k)||(x[i]-i)==(x[k]-k))
        {
            return 1;
        }
    }
    return 0;
}

void printresult(int *x,int n)
{
    for(int i=1; i<=n; i++)
    {
        cout<<"第"<<i<<"行皇后在第"<<x[i]<<"列"<<endl;
    }
    cout<<"**********"<<endl;
}


int main()
{
    int n=4;
    int x[n+1];
    for(int i=1; i<=n; i++)
    {
        x[i]=1;
    }
    int k=1;
    while(1)
    {
        if(x[k]<=n)
        {
            if(isconflict(x,k))
            {
                x[k]++;
                continue;
            }
            else
            {
                if(k==n)
                {
                    printresult(x,n);
                    x[k]++;
                    continue;
                }
            }
        }
        else
        {
            if(k>1)
            {
                x[k]=1;
                k--;
                x[k]++;
                continue;
            }
            else
            {
                break;
            }
        }
        k++;
        continue;
    }
    return 0;
}
