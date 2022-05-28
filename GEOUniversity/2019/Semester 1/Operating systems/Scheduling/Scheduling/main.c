#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define num 5

struct PCB
{
    char ID;
    int runtime;
    int pri;
    char state;
};
struct PCB pcb_list[num];

void init()
{
    int i;
    int other;
    for(i=0;i<num;i++)
    {
        printf("PCB[%d]:ID pri runtime \n",i+1);
        scanf("%s%d%d",&pcb_list[i].ID,&pcb_list[i].pri,&pcb_list[i].runtime);
        pcb_list[i].state='R';//

        system("cls");
        getchar();
    }
    for(i=0;i<num;i++)
    {
        other=pcb_list[i].pri>pcb_list[i].pri?pcb_list[i].pri:pcb_list[i].pri;
    }
}

int judge()
{
    int max=-100;
    int i;
    int key;

    for(i=0;i<num;i++)
    {
        if(pcb_list[i].state=='r')
            return -1;
        else
            if(max<pcb_list[i].pri&&pcb_list[i].state=='R')
        {
            max=pcb_list[i].pri;
            key=i;
        }
    }
    if(pcb_list[key].state=='F')
        return -1;
    else
        return key;
}


void show()
{
    int i;
    printf("\n    ID  PRI  RUNTIME   STATE\n");
    printf("--------------------------------------\n");
    for(i=0;i<num;i++)
    {
        printf("%s%6d%8d     %s\n",&pcb_list[i].ID,pcb_list[i].pri,pcb_list[i].runtime,&pcb_list[i].state);
    }
    printf(" press any key to continue...\n");

}

void run()
{
    int i,j;
    int t=0;
    for(j=0;j<num;j++)
    {
        t=t+pcb_list[j].runtime;
    }
    printf("\n before run ,the condition is:\n");
    show();
    getchar();
    //system("cls");

    for(j=0;j<t;j++)
    {
        while(judge()!=-1)
        {
            pcb_list[judge()].state='r';
        }

        for(i=0;i<num;i++)
            {
                if(pcb_list[i].state=='r')
                {
                    pcb_list[i].pri-=1;
                    pcb_list[i].runtime--;
                    {
                        if(pcb_list[i].runtime==0)
                            pcb_list[i].state='F';
                        else
                            pcb_list[i].state='R';
                    }
                    show();
                   //getchar();
                }
            }

        system("cls");//走马灯效果
    }

}

void main()
{
    init();
    run();

}
