#include "stu.h"

/* ***  ���ļ�ʵ��ѧ���ɼ�����ϵͳ����Ҫ��������  *** */
/*
�������ƣ�ReadInofo
�������ܣ�¼��ѧ����ѧ�źͳɼ���������¼�����Ч���ݵĸ�������ѧ��������
����������ѧ��ѧ�źͳɼ�������һά����
��������ֵ��ѧ����Ϣͨ�����鴫�ݻص��ú�����ѧ������ͨ��return��䷵�ص����ú���
*/
void Input(ST stu[],int n,int m)
{
    int i,j;

    for(i=0;i<n;i++)
    {
        scanf("%d",&stu[i].stuID);
        scanf(" %s",&stu[i].stuname);
        for(j=0;j<m;j++)
        {
        scanf("%d",&stu[i].score[j]);
        }
    }
}
void printMenu()
{
    //�˴��������˵��Ĵ���
    printf("1.Input record\n");
    printf("2.Caculate total and average score of every course\n");
    printf("3.Caculate total and average score of every student\n");
    printf("4.Sort in descending order by total score of every student\n");
    printf("5.Sort in ascending order by total score of every student\n");
    printf("6.Sort in ascending order by number\n");
    printf("7.Sort in dictionary order by name\n");
    printf("8.Search by number\n");
    printf("9.Search by name\n");
    printf("10.Statistic analysis for every course\n");
    printf("11.List record\n");
    printf("0.Exit\n");
    printf("Please enter your choice\n");
}
//�ĵ�����

void zongping(ST stu[],float ave[],int n,int m)//¼��ѧ��ѧ�������ɼ�
{
    int i,j;
    int sum[N];

    for(j=0;j<m;j++)
    {
        sum[j]=0;
        for(i=0;i<n;i++)
        {
            sum[j]+=stu[i].score[j];
        }
        ave[j]=(float)sum[j]/m*(1.00);
    }
    for(j=0;j<m;j++)
    {
        printf("The subject No.%d score is:%.2f\n",j+1,ave[j]);
    }

}




void jiangxu(ST stu[],float ave[],int n,int m)//����ÿ��ѧ�����ֺܷ�ƽ����
{
    int i,j;
    int sum[N];

    for(i=0;i<n;i++)
    {
        sum[i]=0;
        for(j=0;j<m;j++)
        {
            sum[i]+=stu[i].score[j];
        }
        ave[i]=(float)sum[i]/m*(1.00);
    }
    for(i=0;i<n;i++)
    {
        printf("The No.%d person score is:%.2f\n",i+1,ave[i]);
    }
}


void zongfenjiangxu(ST stu[],int n,int m)//��ÿ��ͬѧ���ܷ��ɸߵ���������
{
    int i,j,temp,t;
    char tx;
    int sum[N];
    for(i=0;i<n;i++)
    {
        sum[i]=0;
        for(j=0;j<m;j++)
        {
            sum[i]=sum[i]+stu[i].score[j];
        }
    }
    for(i=0;i<n;i++)
    {
        for(j=i+1;j<n;j++)
        {
            if(sum[i]<sum[j])
            {
                temp=sum[i];
                sum[i]=sum[j];
                sum[j]=temp;

                t=stu[i].stuID;
                stu[i].stuID=stu[j].stuID;
               stu[j].stuID=t;
            }
        }
    }
    printf("Student's follows:\n");
     for(i=0;i<n;i++)
    {
        printf("Student's ID: %d Socre: %d",stu[i].stuID,sum[i]);
        printf("\n");
    }

}



void zongfenshengxu(ST stu[],int n,int m)//��ÿ��ͬѧ���ܷ��ɵ͵���������
{
    int i,j,temp,t;

    int sum[N];
    for(i=0;i<n;i++)
    {
        sum[i]=0;
        for(j=0;j<m;j++)
        {
            sum[i]=sum[i]+stu[i].score[j];
        }
    }
    for(i=0;i<n;i++)
    {
        for(j=i+1;j<n;j++)
        {
            if(sum[i]>sum[j])
            {
                temp=sum[i];
                sum[i]=sum[j];
                sum[j]=temp;

                t=stu[i].stuID;
                stu[i].stuID=stu[j].stuID;
               stu[j].stuID=t;
            }
        }
    }
    printf("Student's follows:\n");
     for(i=0;i<n;i++)
    {
        printf("Student's ID: %d Socre: %d",stu[i].stuID,sum[i]);
        printf("\n");
    }

}



void xuehaoshengxu(ST stu[],int n,int m)//��ѧ����С�����ų��ɼ���
{
    int i,j,k,temp,t[N][6];
     for(i=0;i<n;i++)
    {
        for(k=i+1;k<n;k++)
        {
            if(stu[i].stuID>stu[k].stuID)
            {
                temp=stu[i].stuID;
                stu[i].stuID=stu[k].stuID;
                stu[k].stuID=temp;

                t[i][j]=stu[i].score[j];
                stu[i].score[j]=stu[i].score[j];
               stu[i].score[j]=t[i][j];
            }
        }
    }
    printf("Student's follows:\n");
     for(i=0;i<n;i++)
    {
        printf("Student's ID: %d ",stu[i].stuID);
        for(j=0;j<m;j++)
        {
        printf("Socre: %d",stu[i].score[j]);
        }
        printf("\n");
    }
}


void zidianshunxu(ST stu[],int n,int m)//�������ֵ�˳���ų��ɼ���
{
     int i,j,t[N][6];
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

                t[i][j]=stu[i].score[j];
                stu[i].score[j]=stu[i].score[j];
               stu[i].score[j]=t[i][j];
            }
        }
    }
    printf("Name follows:\n");
     for(i=0;i<n;i++)
    {
        printf("Student's name: %s ",stu[i].stuname);
        for(j=0;j<m;j++)
        {
        printf("Socre: %d",stu[i].score[j]);
        }
        printf("\n");
    }
}



void xuehaopai(ST stu[],int n,int m)//���������ֵ�˳���ų��ɼ�
{
    int i,j,x;
    int sum[N];
    ccc(stu,n,m);
    for(i=0;i<n;i++)
    {
        if(x==sum[i])
        {
            printf("NO.");
            printf("%d\n",i+1);
            for(j=0;j<m;j++)
            {
            printf("Score:%d",stu[i].score);
            }
        }
    }
}

void ccc(ST stu[],int n,int m)//��ÿ��ͬѧ���ܷ��ɸߵ���������
{
    int i,j,temp,t;

    int sum[N];
    for(i=0;i<n;i++)
    {
        sum[i]=0;
        for(j=0;j<m;j++)
        {
            sum[i]=sum[i]+stu[i].score[j];
        }
    }
    for(i=0;i<n;i++)
    {
        for(j=i+1;j<n;j++)
        {
            if(sum[i]<sum[j])
            {
                temp=sum[i];
                sum[i]=sum[j];
                sum[j]=temp;

                t=stu[i].stuID;
                stu[i].stuID=stu[j].stuID;
               stu[j].stuID=t;
            }
        }
    }
    printf("Student's follows:\n");
     for(i=0;i<n;i++)
    {
        printf("Student's ID: %d Socre: %d",stu[i].stuID,sum[i]);
        printf("\n");
    }

}



void xingming(ST stu[],int n,int m)//���������ֵ�˳���ų��ɼ�
{
    int i,j;
    char x;
    int sum[N];
    printf("Please input student's name:");
    scanf("%s",&x);
    ccc(stu,n,m);
    for(i=0;i<n;i++)
    {
        if(x==stu[i].stuname)
        {
            printf("NO.");
            printf("%d\n",i+1);
            for(j=0;j<m;j++)
            {
            printf("Score:%d",stu[i].score);
            }
        }
    }
}

void present(ST stu[],int n,int m)//��90-100 80-90....���гɼ�����
{
    int sum1=0,sum2=0,sum3=0,sum4=0,sum5=0,i,j;
    for(i=0;i<n;i++)
    {
        for(j=0;j<m;j++)
        {
        if(stu[i].score[j]>=90&&stu[i].score[j]<=100)
        {
            sum1++;
        }
        else if(stu[i].score[j]>=80&&stu[i].score[j]<90)
        {
            sum2++;
        }
        else if(stu[i].score[j]>=70&&stu[i].score[j]<80)
        {
            sum3++;
        }
        else if(stu[i].score[j]>=60&&stu[i].score[j]<70)
        {
            sum4++;
        }
        else if(stu[i].score[j]>=0&&stu[i].score[j]<60)
        {
            sum5++;
        }
        }
    }
    printf("excellent:%d good:%d middle:%d pass:%d flunk:%d\n",sum1,sum2,sum3,sum4,sum5);
    printf("A:%.2f%% B:%.2f%% C:%.2f%% D:%.2f%% E:%.2f%%",(float)(sum1/(n*1.00))*100,(float)(sum2/(n*1.00))*100,(float)(sum3/(n*1.00))*100,(float)(sum4/(n*1.00))*100,(float)(sum5/(n*1.00))*100);
}



void output(ST stu[],float ave[],int n,int m)//���ÿ��ѧ����ѧ�������Ϳ��Գɼ��Լ��γ��ֺܷ�ƽ����
{
    int i,j;
    int sum[N];

    printf("ID name score\n");

    for(i=0;i<n;i++)
    {
        printf("%d",stu[i].stuID);
        printf(" %s",stu[i].stuname);
        for(j=0;j<m;j++)
        {
          printf(" %d",stu[i].score[j]);
        }
        printf("\n");
    }
    for(i=0;i<n;i++)
    {
        sum[i]=0;
        for(j=0;j<m;j++)
        {
            sum[i]+=stu[i].score[j];
        }
        ave[i]=(float)sum[i]/m*(1.00);
    }

    for(i=0;i<n;i++)
    {
    printf("total Score:%d\n",sum[i]);
    }
    for(i=0;i<n;i++)
    {
    printf("Ave:%.2f\n",ave[i]);
    }
}


























































