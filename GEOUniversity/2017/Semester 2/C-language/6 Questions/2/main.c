#include <stdio.h>
#include <stdlib.h>
#include <time.h>
int main()
{
    int one,two,s,p,count;
    srand(time(NULL));
    one = rand()%10+1;
    two = rand()%10+1;
    s=one*two;
    printf("%d*%d=?\n",one,two);
    scanf("%d",&p);
    for(count=1;;count++)
    {
    if(s==p)
    {
        printf("Right!");
        break;
    }
    else
    {
        printf("Wrong! Please try again.\n");
        scanf("%d",&p);
        for(count=1;;count++)
        {
        if(s==p)
        {
        printf("Right!");
        break;
        }
        else
        {
        printf("Wrong! Please try again.\n");
               scanf("%d",&p);
           for(count=1;;count++)
           {
           if(s==p)
           {
           printf("Right!");
           break;
           }
           else
           {
           printf("Wrong! Please try again.\n");
              scanf("%d",&p);
              for(count=1;;count++)
              {
              if(s==p)
              {
              printf("Right!");
              break;
              }
              else
              {
              printf("Wrong! You have tried three times! Test over.");
              break;
              }
              }
              break;
           }
           }
           break;
        }
        }
        break;
    }
    return 0;

    }
}
