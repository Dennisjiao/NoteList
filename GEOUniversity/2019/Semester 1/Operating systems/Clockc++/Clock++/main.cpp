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
    int n;//定时闹钟
    int Q=0;//中断信号，0表示中断，1表示不中断
    int Count=0;//计数器
    int millsecond=0;
    //设置开机时间

    time_t now = time(0);

   // 把 now 转换为字符串形式
   char* dt = ctime(&now);

   cout << "本地日期和时间：" << dt << endl;

   // 把 now 转换为 tm 结构
   tm *gmtm = gmtime(&now);
   dt = asctime(gmtm);
   cout << "UTC 日期和时间："<< dt << endl;

    printf("请输入开机时间的年、月、日、时、分、秒、毫秒（用空格隔开）：\n");
    scanf("%d %d %d %d %d %d %d",&T.year,&T.month,&T.day,&T.hour,&T.minute,&T.seconds,&T.millsecond);
    printf("开机时间为：%d年%d月%d日%d时%d分%d秒%d毫秒。\n",T.year,T.month,T.day,T.hour,T.minute,T.seconds,T.millsecond);
    flag=Judge(T);
    if(flag==1)
    {
        printf("错误！输入日期非法！\n");
    }
    else
    {
        printf("请设置闹钟：");
        scanf("%d",&n);
        while(n!=0)
        {
            while(Q==0)
            {
               printf("请输入时钟中断信号（1表示中断，0表示不中断）：");
               scanf("%d",&Q);
            }
            printf("保护现场！\n");
            Count++;
            millsecond+=20;
            n--;
            Q=0;
        }
        printf("中断次数：%d，增加的毫秒数：%d \n",Count,millsecond);
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
    printf("当前日历时间为：%d年%d月%d日%d时%d分%d秒%d毫秒",T.year,T.month,T.day,T.hour,T.minute,T.seconds,T.millsecond);
}
