#include <iostream>
#include <stdlib.h>
#include <stdio.h>
#include <ctime>

using namespace std;

struct time {
    int year;
    int month;
    int day;
    int hour;
    int minute;
    int seconds;
    int millsecond;
};
int Judge (time T);
void Clock (time T,int millsecond);

int main()
{
    time T;
    int flag;
    int n;//��ʱ����
    int Q=0;//�ж��źţ�0��ʾ�жϣ�1��ʾ���ж�
    int Count=0;//������
    int millsecond=0;
    //���ÿ���ʱ��

    time_t now = time(0);

   // �� now ת��Ϊ�ַ�����ʽ
   char* dt = ctime(&now);

   cout << "�������ں�ʱ�䣺" << dt << endl;

   // �� now ת��Ϊ tm �ṹ
   tm *gmtm = gmtime(&now);
   dt = asctime(gmtm);
   cout << "UTC ���ں�ʱ�䣺"<< dt << endl;

    printf("�����뿪��ʱ����ꡢ�¡��ա�ʱ���֡��롢���루�ÿո��������\n");
    scanf("%d %d %d %d %d %d %d",&T.year,&T.month,&T.day,&T.hour,&T.minute,&T.seconds,&T.millsecond);
    printf("����ʱ��Ϊ��%d��%d��%d��%dʱ%d��%d��%d���롣\n",T.year,T.month,T.day,T.hour,T.minute,T.seconds,T.millsecond);
    flag=Judge(T);
    if(flag==1)
    {
        printf("�����������ڷǷ���\n");
    }
    else
    {
        printf("���������ӣ�");
        scanf("%d",&n);
        while(n!=0)
        {
            while(Q==0)
            {
               printf("������ʱ���ж��źţ�1��ʾ�жϣ�0��ʾ���жϣ���");
               scanf("%d",&Q);
            }
            printf("�����ֳ���\n");
            Count++;
            millsecond+=20;
            n--;
            Q=0;
        }
        printf("�жϴ�����%d�����ӵĺ�������%d \n",Count,millsecond);
        Clock(T,millsecond);
    }
    return 0;
}
int Judge(time T)
{
    if(T.year%400==0 || (T.year%4==0&&T.year%100!=0))
    {
        if(T.month==2 && T.day>29)
        {
            return 1;
        }
        else if((T.month==4 || T.month==6 || T.month==9 || T.month==11) && T.day>30)
        {

            return 1;
        }
        else if((T.month==1 || T.month==3 || T.month==5 || T.month==7 || T.month==8 || T.month==10 || T.month==12) && T.day>31)
        {

            return 1;
        }
    }
    else
    {
        if(T.month==2 && T.day>28)
        {

            return 1;
        }
        else if((T.month==4 || T.month==6 || T.month==9 || T.month==11) && T.day>30)
        {

            return 1;
        }
        else if((T.month==1 || T.month==3 || T.month==5 || T.month==7 || T.month==8 || T.month==10 || T.month==12) && T.day>31)
        {

            return 1;
        }
    }
    return 0;
}
void Clock(time T,int millsecond)
{
    int year=0,month=0,day=0,hour=0,minute=0,seconds=0;
    if(T.millsecond+millsecond<1000)
        T.millsecond=T.millsecond+millsecond;
    else{
        seconds=seconds+((T.millsecond+millsecond)/1000);
        T.millsecond=T.millsecond+millsecond-((T.millsecond+millsecond)/1000)*1000;
        if(T.seconds+seconds<60)
            T.seconds=T.seconds+seconds;
        else{
            minute=minute+(T.seconds+seconds)/60;
            T.seconds=T.seconds+seconds-((T.seconds+seconds)/60)*60;
            if(T.minute+minute<60)
                T.minute=T.minute+minute;
            else{
                hour=hour+(T.minute+minute)/60;
                T.minute=T.minute+minute-((T.minute+minute)/60)*60;

                if(T.hour+hour<24)
                    T.hour=T.hour+hour;
                else{
                    day=day+(T.hour+hour)/24;
                    T.hour=T.hour+hour-((T.hour+hour)/24)*24;

                    if(T.day+day<=28)
                        T.day=T.day+day;
                    else if(T.year%400==0 || (T.year%4==0&&T.year%100!=0))
                    {
                        if(T.day+day==29){
                            T.day=T.day+day;
                        }
                        else if(T.day+day>29){
                            if(T.month==2){
                                month=month+1;
                                T.day=T.day+day-29;
                            }
                            else if(T.month==4 || T.month==6 || T.month==9 || T.month==11){
                                if(T.day+day<=30){
                                    T.day=T.day+day;
                                }
                                else{
                                    T.day=T.day+day-30;
                                    month=month+1;
                                }
                            }
                            else if(T.month==1 || T.month==3 || T.month==5 || T.month==7 || T.month==8 || T.month==10 || T.month==12){
                                if(T.day+day<=31){
                                    T.day=T.day+day;
                                }
                                else{
                                    T.day=T.day+day-31;
                                    month=month+1;
                                }
                            }
                        }
                    }
                    else{
                       if(T.day+day>28){
                            if(T.month==2){
                                month=month+1;
                                T.day=T.day+day-28;
                            }
                            else if(T.month==4 || T.month==6 || T.month==9 || T.month==11){
                                if(T.day+day<=30){
                                    T.day=T.day+day;
                                }
                                else{
                                    T.day=T.day+day-30;
                                    month=month+1;
                                }
                            }
                            else if(T.month==1 || T.month==3 || T.month==5 || T.month==7 || T.month==8 || T.month==10 || T.month==12){
                                if(T.day+day<=31){
                                    T.day=T.day+day;
                                }
                                else{
                                    T.day=T.day+day-31;
                                    month=month+1;
                                }
                            }
                        }
                    }
                    if(T.month+month<12)
                        T.month=T.month+month;
                    else{
                        T.month=T.month+month-12;
                        year=year+1;
                        T.year=T.year+year;
                    }
                }
            }
        }
    }
    printf("��ǰ����ʱ��Ϊ��%d��%d��%d��%dʱ%d��%d��%d����",T.year,T.month,T.day,T.hour,T.minute,T.seconds,T.millsecond);
}
