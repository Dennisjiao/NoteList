#include <stdio.h>
#include <stdlib.h>

int main()
{
int sum,n;
scanf("%d",&n);
if(n%2==0){
sum=(n/2)*(n/2);
printf("sum=%d",sum);}
else{sum=((n+1)/2)*((n+1)/2);
     printf("sum=%d",sum);}
}
