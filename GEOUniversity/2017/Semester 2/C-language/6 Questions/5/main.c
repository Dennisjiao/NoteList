#include <stdio.h>
#include <stdlib.h>
#include <time.h>
int main()
{
    int one,two,s,p,count,pum,a,cas;
    srand(time(NULL));
    pum=0;

    exam:for(count=1;count<=10;count++)
    {
    one = rand()%10+1;
    two = rand()%10+1;
    cas = rand()%4+1;

    switch(cas)
    {
        case 1:s=one+two;printf("%d+%d=?\n",one,two);scanf("%d",&p);  if(s==p)
            {
            printf("Right!\n");
            pum=pum+1;
            }
            else
            {
             printf("Wrong!\n");
            }
            break;
        case 2:if(one>=two){s=one-two;printf("%d-%d=?\n",one,two);scanf("%d",&p);if(s==p)
             {
             printf("Right!\n");
             pum=pum+1;
             }
             else
             {
              printf("Wrong!\n");
             }
             }
             else
             {
                 count=count-1;
             }
             break;
        case 3:s=one*two;printf("%d*%d=?\n",one,two);scanf("%d",&p);if(s==p)
             {
             printf("Right!\n");
             pum=pum+1;
             }
             else
             {
             printf("Wrong!\n");
             }
             break;
       case 4:if(one%two==0){s=one/two;printf("%d/%d=?\n",one,two);scanf("%d",&p);if(s==p)
            {
             printf("Right!\n");
             pum=pum+1;
            }
            else
            {
            printf("Wrong!\n");
            }
            }
            else{count=count-1;}
            break;
    }


    }
     a=pum*10;
     if(a<75){printf("you have too many mistake,test again\n\n"); goto exam;}
     return 0;




}
