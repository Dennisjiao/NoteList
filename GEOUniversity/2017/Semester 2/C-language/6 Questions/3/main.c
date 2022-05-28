#include <stdio.h>
#include <stdlib.h>
#include <time.h>
int main()
{
    int one,two,s,p,count,pum,a;
    srand(time(NULL));
    pum=0;
    for(count=1;count<=10;count++)
    {
    one = rand()%10+1;
    two = rand()%10+1;
    s=one*two;
    printf("%d*%d=?\n",one,two);
    scanf("%d",&p);
    if(s==p)
    {
        printf("Right!\n");
        pum=pum+1;
    }
    else
    {
        printf("Wrong! Please try again.\n");
    }
    }
    a=pum*10;
    printf("score:");
    printf("%d\n",a);

    printf("Correct rate:");
    printf("%d%%",a);

    return 0;

}
