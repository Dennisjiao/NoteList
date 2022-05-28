#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main()
{
    struct tm * timeinfo;
    time_t nowtime;
    time(&nowtime);
    timeinfo=localtime(&nowtime);

    printf("开机时间为：%d年 %d月 %d日 %d时 %d分 %d秒\n",timeinfo->tm_year+1900,timeinfo->tm_mon+1,timeinfo->tm_mday,timeinfo->tm_hour,timeinfo->tm_min,timeinfo->tm_sec);
//define
    int jishiqi=0,clocks,inf,jishuqi=0,year=timeinfo->tm_year+1900,z,i;
    float times=0,second=timeinfo->tm_sec,minute=0,hour=timeinfo->tm_hour,day=timeinfo->tm_mday,res=timeinfo->tm_mon+1;

    printf("输入要求闹钟时间：");
    scanf("%d",&clocks);

    while(clocks!=0){
        jishuqi++;
        printf("进行第%d次操作\n",jishuqi);
        printf("输入键盘信息(1为中断，0继续操作)");
        scanf("%d",&inf);
        times=times+0.02;
        if(inf==1){
            printf("中断操作。\n\n");
            jishiqi++;
            clocks--;
            if(clocks==0){

                    if(times>=60){
                        minute=times/60;
                        second=second+times;
                        while(second>=60){
                            second=second-60;
                        }
                        minute=minute+timeinfo->tm_min;
                        while(minute>=60){
                            minute=minute-60;
                            hour++;
                            if(hour>=24){
                                hour=hour-24;
                                day++;
                            }
                        }

                        z=day/366+2;
                        for(i=0;i<z;i++){

                            if(year%4!=0){//365天  2月28天
                            while(day>=28)
                            {
                                if(res==1||res==3||res==5||res==7||res==8||res==10||res==12){
                                        if(day>31){
                                            day=day-31;
                                            res++;
                                            if(res==13){
                                                year++;
                                                res=1;
                                                break;
                                            }
                                        }
                                }
                                else if(res==2){
                                    if(day>28){
                                            day=day-28;
                                            res++;
                                        }
                                }
                                else{
                                   if(day>30){
                                            day=day-30;
                                            res++;
                                        }
                                    else{
                                        break;
                                    }
                                }

                            }
                        }
                        else{
                            while(day>=29){
                                if(res==1||res==3||res==5||res==7||res==8||res==10||res==12){
                                        if(day>31){
                                            day=day-31;
                                            res++;
                                            if(res==13){
                                                year++;
                                                res=1;
                                                break;
                                            }
                                        }
                                }
                                else if(res==2){
                                    if(day>29){
                                            day=day-29;
                                            res++;
                                        }
                                }
                                else{
                                   if(day>30){
                                            day=day-30;
                                            res++;
                                        }
                                    else{
                                        break;
                                    }
                                }
                            }
                        }
                        }
            }
            else{
                minute=timeinfo->tm_min;
                second=times+second;
                if(second>=60){
                    minute++;
                    second=second-60;
                    while(minute>=60){
                            minute=minute-60;
                            hour++;
                            if(hour>=24){
                                hour=hour-24;
                                day++;
                            }
                        }

                        z=day/366+2;
                        for(i=0;i<z;i++){
                            if(year%4){//365天  2月28天
                            while(day>=28){
                                if(res==1||res==3||res==5||res==7||res==8||res==10||res==12){
                                        if(day>31){
                                            day=day-31;
                                            res++;
                                            if(res==13){
                                                year++;
                                                res=1;
                                                break;
                                            }
                                        }
                                }
                                else if(res==2){
                                    if(day>28){
                                            day=day-28;
                                            res++;
                                        }
                                }
                                else{
                                   if(day>30){
                                            day=day-30;
                                            res++;
                                        }
                                    else{
                                        break;
                                    }
                                }
                            }
                        }
                        else{
                            while(day>=29){
                                if(res==1||res==3||res==5||res==7||res==8||res==10||res==12){
                                        if(day>31){
                                            day=day-31;
                                            res++;
                                            if(res==13){
                                                year++;
                                                res=1;
                                                break;
                                            }
                                        }
                                }
                                else if(res==2){
                                    if(day>29){
                                            day=day-29;
                                            res++;
                                            break;
                                        }
                                }
                                else{
                                   if(day>30){
                                            day=day-30;
                                            res++;
                                        }
                                    else{
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            printf("结束时间为：%d年 %.0f月 %.0f日 %.0f时 %.0f分 %.2f秒\n",year,res,day,hour,minute,second);
            }
            else{
                printf("执行处理机调度。\n");
            }
        }
    }

    return 0;
}
