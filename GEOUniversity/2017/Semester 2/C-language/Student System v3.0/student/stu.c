#include "stu.h"

/* ***  本文件实现学生成绩管理系统的主要操作函数  *** */
/*
函数名称：ReadInofo
函数功能：录入学生的学号和成绩，并返回录入的有效数据的个数（即学生人数）
函数参数：学生学号和成绩的两个一维数组
函数返回值：学生信息通过数组传递回调用函数，学生人数通过return语句返回到调用函数
*/
void Input(ST stu[],int n)
{
    int i;

    for(i=0;i<n;i++)
    {
        scanf("%d",&stu[i].stuID);
        scanf(" %s",&stu[i].stuname);
        scanf("%d",&stu[i].score);
    }
}
void printMenu()
{
    //此处添加输出菜单的代码
    printf("1.Input record\n");
    printf("2.Caculate total and average score of course\n");
    printf("3.Sort in descending order by score\n");
    printf("4.Sort in ascending order by number\n");
    printf("5.Sort in ascending order by number\n");
    printf("6.Sort in dictionary order by name\n");
    printf("7.Search by number\n");
    printf("8.Search by name\n");
    printf("9.Statistic analysis\n");
    printf("10.List record\n");
    printf("0.Exit\n");
    printf("Please enter your choice\n");
}


void zongping(ST stu[],int n)
{
    int i;
    int sum=0;
    float ave;
    for(i=0;i<n;i++)
    {
    sum+=stu[i].score;
    }
    ave=sum/(n*1.00);
    printf("The total score is:%d",sum);
    printf("\n");
    printf("The ave score is:%.2f",ave);
}


void jiangxu(ST stu[],int n)
{
    int i,j,temp,t;
    for(i=0;i<n;i++)
    {
        for(j=i+1;j<n;j++)
        {
            if(stu[i].score<stu[j].score)
            {
                temp=stu[i].score;
                stu[i].score=stu[j].score;
                stu[j].score=temp;

                t=stu[i].stuID;
                stu[i].stuID=stu[j].stuID;
                stu[j].stuID=t;
            }
        }
    }
    printf("Student's follows:\n");
    for(i=0;i<n;i++)
    {
        printf("Student's ID: %d Socre: %d",stu[i].stuID,stu[i].score);
        printf("\n");
    }

}

void shengxu(ST stu[],int n)
{
    int i,j,temp,t;
    for(i=0;i<n;i++)
    {
        for(j=i+1;j<n;j++)
        {
            if(stu[i].score>stu[j].score)
            {
                temp=stu[i].score;
                stu[i].score=stu[j].score;
                stu[j].score=temp;

                t=stu[i].stuID;
                stu[i].stuID=stu[j].stuID;
                stu[j].stuID=t;
            }
        }
    }

    printf("Student's follows:\n");
    for(i=0;i<n;i++)
    {
        printf("Student's ID: %d Socre: %d",stu[i].stuID,stu[i].score);
        printf("\n");
    }

}

void xuehaosheng(ST stu[],int n)
{
    int i,j,temp,t;
    for(i=0;i<n;i++)
    {
        for(j=i+1;j<n;j++)
        {
            if(stu[i].stuID>stu[j].stuID)
            {
                temp=stu[i].stuID;
                stu[i].stuID=stu[j].stuID;
                stu[j].stuID=temp;

                t=stu[i].score;
                stu[i].score=stu[j].score;
                stu[j].score=t;
            }
        }
    }
    printf("Student's follows:\n");
    for(i=0;i<n;i++)
    {
        printf("Student's ID: %d Socre: %d",stu[i].stuID,stu[i].score);
        printf("\n");
    }
}




void xuehaopai(ST stu[],int n)
{
    int i,m;
    printf("Please input student's ID:");
    scanf("%d",&m);
    jiangxu(stu,n);
    for(i=0;i<n;i++)
    {
        if(m==stu[i].stuID)
        {

            printf("NO.");
            printf("%d\n",i+1);
            printf("Score:%d",stu[i].score);
        }
    }
}



void xingmingshou(ST stu[],int n)
{
    int i,j,t;
    char temp[10];
    for(i=0;i<n;i++)
    {
        for(j=i+1;j<n;j++)
        {
            if(strcmp(stu[j].stuname,stu[i].stuname)<0)
            {
                strcpy(temp,stu[i].stuname);
                strcpy(stu[i].stuname,stu[j].stuname);
                strcpy(stu[j].stuname,temp);

                t=stu[i].score;
                stu[i].score=stu[j].score;
                stu[j].score=t;
            }
        }
    }
    printf("Name follows:\n");
    for(i=0;i<n;i++)
    {
        printf("Student's name: %s Socre: %d",stu[i].stuname,stu[i].score);
        printf("\n");
    }
}



















void xingmingpai(ST stu[],int n)
{
    int i;
    char m[10];
    printf("Please input student's name:");
    scanf("%s",m);
    jiangxu(stu,n);

    for(i=0;i<n;i++)
    {
        if(strcmp(m,stu[i].stuname)==0)
        {
            printf("NO.");
            printf("%d\n",i+1);
            printf("Score:%d",stu[i].score);
        }

    }

}


void present(ST stu[],int n)
{
    int sum1=0,sum2=0,sum3=0,sum4=0,sum5=0,i;
    for(i=0;i<n;i++)
    {
        if(stu[i].score>=90&&stu[i].score<=100)
        {
            sum1++;
        }
        else if(stu[i].score>=80&&stu[i].score<90)
        {
            sum2++;
        }
        else if(stu[i].score>=70&&stu[i].score<80)
        {
            sum3++;
        }
        else if(stu[i].score>=60&&stu[i].score<70)
        {
            sum4++;
        }
        else if(stu[i].score>=0&&stu[i].score<60)
        {
            sum5++;
        }


    }
    printf("excellent:%d good:%d middle:%d pass:%d flunk:%d\n",sum1,sum2,sum3,sum4,sum5);
    printf("A:%.2f%% B:%.2f%% C:%.2f%% D:%.2f%% E:%.2f%%",(float)(sum1/(n*1.00))*100,(float)(sum2/(n*1.00))*100,(float)(sum3/(n*1.00))*100,(float)(sum4/(n*1.00))*100,(float)(sum5/(n*1.00))*100);
}



void output(ST stu[],int n)
{
    int i;
    int sum=0;
    float ave;
    printf("ID name score\n");

    for(i=0;i<n;i++)
    {
        printf("%d",stu[i].stuID);
        printf(" %s",stu[i].stuname);
        printf(" %d",stu[i].score);
        printf("\n");
    }
    for(i=0;i<n;i++)
    {
    sum+=stu[i].score;
    }
    ave=sum/(n*1.00);
    printf("total Score:%d\n",sum);
    printf("Ave:%.2f",ave);
}


