#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define N 30
#define M 20

void Menu();
void Input(int ID[],char name[][M],int score[],int n);
void Sumave(int score[],int n);
void Descending_score(int score[],int ID[],char name[][M],int n);
void Ascending_score(int score[],int ID[],char name[][M],int n);
void Ascending_id(int score[],int ID[],char name[][M],int n);
void Sort_ming(int score[],int ID[],char name[][M],int n);
void Search_id(int score[],int ID[],char name[][M],int n);
void search_name(int score[],int ID[],char name[][M],int n);
void Percent(int score[],int n);
void Output(int score[],int ID[],char name[][M],int n);

int main()
{
    int ID[30],score[30],select,i,n;
    char name[N][M],op;
    Menu();
    while(1)
    {
        printf("Please enter your choice: ");
        scanf("%d",&select);
        switch(select)
        {
            case 1:
                printf("Please input the number of student: ");
                scanf("%d",&n);
                Input(ID,name,score,n);
                break;
            case 2:
                Sumave(score,n);
                break;
            case 3:
                Descending_score(score,ID,name,n);
                break;
            case 4:
                Ascending_score(score,ID,name,n);
                break;
            case 5:
                Ascending_id(score,ID,name,n);
                break;
            case 6:
                Sort_ming(score,ID,name,n);
                break;
            case 7:
                Search_id(score,ID,name,n);
                break;
            case 8:
                search_name(score,ID,name,n);
                break;
            case 9:
                Percent(score,n);
                break;
            case 10:
                Output(score,ID,name,n);
                break;
            case 0:exit(0);
            default:
                printf("Please input the right choice!\n");
                break;
        }
        printf("\n您要继续操作吗？？（请输入Y或者N）：");
        scanf(" %c",&op);
        if(('N' == op)||('n' == op))
        {
            break;
        }
    }
    return 0;
}

void Menu()
{
    printf("1.Input record\n");
    printf("2.Caculate total and average score of every course\n");
    printf("3.Sort in descending order by score\n");
    printf("4.Sort in ascending order by score\n");
    printf("5.Sort in ascending order by number\n");
    printf("6.Sort in dictionary order by name\n");
    printf("7.Search by number\n");
    printf("8.Search by name\n");
    printf("10:Statistic analysis\n");
    printf("11.List record\n");
    printf("0.Exit\n");
}

void Input(int ID[],char name[][M],int score[],int n)
{
    int i;
    printf("Please input student's student ID: ");
    for(i=0;i<n;i++)
    {
        scanf("%d",&ID[i]);
    }
    printf("Please input student's name: ");
    for(i=0;i<n;i++)
    {
        scanf("%s",&name[i]);
    }
    printf("Please input student's score: ");
    for(i=0;i<n;i++)
    {
        scanf("%d",&score[i]);
    }
}

void Sumave(int score[],int n)
{
    int i,sum=0;
    float ave;
    for(i=0;i<n;i++)
    {
        sum+=score[i];
    }
    ave=sum/(n*1.00);
    printf("The student's total score is %d\n",sum);
    printf("The average score of student is %.2f\n",ave);
}

void Descending_score(int score[],int ID[],char name[][M],int n)
{
    int i,j,t;
    char temp[M];
    for(i=0;i<n;i++)
    {
        for(j=i+1;j<n;j++)
        {
            if(score[i]<score[j])
            {
                t=score[i];
                score[i]=score[j];
                score[j]=t;
                t=ID[i];
                ID[i]=ID[j];
                ID[j]=t;
                strcpy(temp,name[i]);
                strcpy(name[i],name[j]);
                strcpy(name[j],temp);
            }
        }
    }
    printf("The students are ranked as follows:\n");
    for(i=0;i<n;i++)
    {
         printf("ID: %d  name:%s  score: %d\n",ID[i],name[i],score[i]);
    }
}

void Ascending_score(int score[],int ID[],char name[][M],int n)
{
    int i,j,t;
    char temp[M];
    for(i=0;i<n;i++)
    {
        for(j=i+1;j<n;j++)
        {
            if(score[i]>score[j])
            {
                t=score[i];
                score[i]=score[j];
                score[j]=t;
                t=ID[i];
                ID[i]=ID[j];
                ID[j]=t;
                strcpy(temp,name[i]);
                strcpy(name[i],name[j]);
                strcpy(name[j],temp);
            }
        }
    }
    printf("The students are ranked as follows:\n");
    for(i=0;i<n;i++)
    {
         printf("ID: %d  name:%s  score: %d\n",ID[i],name[i],score[i]);
    }
}

void Ascending_id(int score[],int ID[],char name[][M],int n)
{
    int i,j,t;
    char temp[M];
    for(i=0;i<n;i++)
    {
        for(j=i+1;j<n;j++)
        {
            if(ID[i]>ID[j])
            {
                t=ID[i];
                ID[i]=ID[j];
                ID[j]=t;
                t=score[i];
                score[i]=score[j];
                score[j]=t;
                strcpy(temp,name[i]);
                strcpy(name[i],name[j]);
                strcpy(name[j],temp);
            }
        }
    }
    printf("The students are ranked as follows:\n");
    for(i=0;i<n;i++)
    {
         printf("ID: %d  name:%s  score: %d\n",ID[i],name[i],score[i]);
    }
}

void Sort_ming(int score[],int ID[],char name[][M],int n)
{
    int i,j,t;
    char temp[M];
    for(i=0;i<n;i++)
    {
        for(j=i+1;j<n;j++)
        {
            if(strcmp(name[j],name[i])<0)
            {
                t=ID[i];
                ID[i]=ID[j];
                ID[j]=t;
                t=score[i];
                score[i]=score[j];
                score[j]=t;
                strcpy(temp,name[i]);
                strcpy(name[i],name[j]);
                strcpy(name[j],temp);
            }
        }
    }
    printf("The students are ranked as follows:\n");
    for(i=0;i<n;i++)
    {
         printf("ID: %d  name:%s  score: %d\n",ID[i],name[i],score[i]);
    }
}

void Search_id(int score[],int ID[],char name[][M],int n)
{
    int i,j,t,id;
    char temp[M];
    for(i=0;i<n;i++)
    {
        for(j=i+1;j<n;j++)
        {
            if(ID[i]>ID[j])
            {
                t=ID[i];
                ID[i]=ID[j];
                ID[j]=t;
                t=score[i];
                score[i]=score[j];
                score[j]=t;
                strcpy(temp,name[i]);
                strcpy(name[i],name[j]);
                strcpy(name[j],temp);
            }
        }
    }
    printf("Please input student's student ID: ");
    scanf("%d",&id);
    for(i=0;i<n;i++)
    {
        if(ID[i]==id)
        {
            printf("The ranking is %d\n",i+1);
            printf("The score is %d\n",score[i]);
            break;
        }
    }
}

void search_name(int score[],int ID[],char name[][M],int n)
{
    char ming[99];
    int i,j,k;
    getchar();
    printf("Please input this student's name: ");
    gets(ming);
    j=strlen(ming);
    for(i=0;i<n;i++)
    {
        k=strlen(name[i]);
        if(j==k)
        {
            if(strcmp(name[i],ming)==0)
            {
                printf("The ranking is %d\n",i+1);
                printf("The score is %d\n",score[i]);
                break;
            }
        }
    }
}

void Percent(int score[],int n)
{
    int i,s1=0,s2=0,s3=0,s4=0,s5=0;
    for(i=0;i<n;i++)
    {
        if(score[i]>=90&&score[i]<=100)
        {
            s1++;
        }
        else if(score[i]>=80&&score[i]<=89)
        {
            s2++;
        }
        else if(score[i]>=70&&score[i]<=79)
        {
            s3++;
        }
        else if(score[i]>=60&&score[i]<=69)
        {
            s4++;
        }
        else if(score[i]>=0&&score[i]<=59)
        {
            s5++;
        }
    }
    printf("The excellent percentage is: %.2f%%\n",(float)s1/n*100);
    printf("The fine percentage is: %.2f%%\n",(float)s2/n*100);
    printf("The medium percentage is: %.2f%%\n",(float)s3/n*100);
    printf("The pass percentage is: %.2f%%\n",(float)s4/n*100);
    printf("The flunk percentage is: %.2f%%\n",(float)s5/n*100);
}

void Output(int score[],int ID[],char name[][M],int n)
{
    int i,j,sum=0;
    float ave;
    for(i=0;i<n;i++)
    {
        sum+=score[i];
        printf("StudentID: %d ",ID[i]);
        for(j=0;j<n;j++)
        {
            printf("%s",name[i]);
        }
        printf("Score: %d\n",score[i]);
    }
    ave=(float)sum/(n*1.00);
    printf("The total points is %d \n",sum);
    printf("The average is %.2f\n",ave);
}
