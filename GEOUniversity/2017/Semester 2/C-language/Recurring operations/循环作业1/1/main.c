#include <stdio.h>
#include <stdlib.h>

int main()
{
    int n,m,i;
    do{

    scanf(" %d",&n);
    if(n<=0){printf("input error");break;}
    else
    {
        for(i=2;i<n;i++)
        {
            m=n%i;
            if(0==m){printf("N\n");break;}
            else{break;}

        }

            if(0!=m){printf("Y\n");}

      }

}
    while(1);
    return 0;
    }


