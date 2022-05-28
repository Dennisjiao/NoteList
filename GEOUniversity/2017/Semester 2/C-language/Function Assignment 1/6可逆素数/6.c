#include <stdio.h>
int isPrime(int n)
{
    int i;
    if(n<2)
        return 0;
    for(i=2;i*i<=n;i++)
        if(!(n%i))
            return 0;
    return 1;
}
int reverse(int n)
{
    int t=0;
    while(n)
    {
        t=t*10+n%10;
        n/=10;
    }
    return t;
}
int main()
{
    int n;
    scanf("%d",&n);
    printf(isPrime(n)&&isPrime(reverse(n))?"yes\n":"no\n");
    return 0;
}
