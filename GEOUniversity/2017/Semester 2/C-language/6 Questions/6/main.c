#include <stdio.h>
#include <stdlib.h>
#include <time.h>
int main()
{
    int one,two,s,p,count,pum,a,cas,yes,no;
    srand(time(NULL));
    pum=0;

    exam:for(count=1;count<=10;count++)
    {
    one = rand()%10+1;
    two = rand()%10+1;
    cas = rand()%4+1;

    yes = rand()%4+5;
    no = rand()%4+9;

    switch(cas)
    {
        case 1:s=one+two;printf("%d+%d=?\n",one,two);scanf("%d",&p);  if(s==p)
            {
               switch(yes)
               {
               case 5: printf("Very good!\n");break;
               case 6: printf("Excellent!\n");break;
               case 7: printf("Nice work!\n");break;
               case 8: printf("Keep up the good work!\n");break;
               }
            pum=pum+1;
            }
            else
            {
                switch(no)
               {
               case 9: printf("No. Please try again.\n");break;
               case 10: printf("Wrong. Try once more\n");break;
               case 11: printf("Don't give up!\n");break;
               case 12: printf("Not correct.Keep trying.\n");break;
               }
            }
            break;
        case 2:if(one>=two){s=one-two;printf("%d-%d=?\n",one,two);scanf("%d",&p);if(s==p)
             {
              switch(yes)
               {
               case 5: printf("Very good!\n");break;
               case 6: printf("Excellent!\n");break;
               case 7: printf("Nice work!\n");break;
               case 8: printf("Keep up the good work!\n");break;
               }
             pum=pum+1;
             }
             else
             {
              switch(no)
               {
               case 9: printf("No. Please try again.\n");break;
               case 10: printf("Wrong. Try once more\n");break;
               case 11: printf("Don't give up!\n");break;
               case 12: printf("Not correct.Keep trying.\n");break;
               }
             }
             }
             else
             {
                 count=count-1;
             }
             break;
        case 3:s=one*two;printf("%d*%d=?\n",one,two);scanf("%d",&p);if(s==p)
             {
              switch(yes)
               {
               case 5: printf("Very good!\n");break;
               case 6: printf("Excellent!\n");break;
               case 7: printf("Nice work!\n");break;
               case 8: printf("Keep up the good work!\n");break;
               }
             pum=pum+1;
             }
             else
             {
             switch(no)
               {
               case 9: printf("No. Please try again.\n");break;
               case 10: printf("Wrong. Try once more\n");break;
               case 11: printf("Don't give up!\n");break;
               case 12: printf("Not correct.Keep trying.\n");break;
               }
             }
             break;
       case 4:if(one%two==0){s=one/two;printf("%d/%d=?\n",one,two);scanf("%d",&p);if(s==p)
            {
              switch(yes)
               {
               case 5: printf("Very good!\n");break;
               case 6: printf("Excellent!\n");break;
               case 7: printf("Nice work!\n");break;
               case 8: printf("Keep up the good work!\n");break;
               }
             pum=pum+1;
            }
            else
            {
            switch(no)
               {
               case 9: printf("No. Please try again.\n");break;
               case 10: printf("Wrong. Try once more\n");break;
               case 11: printf("Don't give up!\n");break;
               case 12: printf("Not correct.Keep trying.\n");break;
               }
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
