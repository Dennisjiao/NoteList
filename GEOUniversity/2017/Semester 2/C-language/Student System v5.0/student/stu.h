#ifndef STU_H_INCLUDED
#define STU_H_INCLUDED

#include <stdio.h>
#include <stdlib.h>

#define N 30

typedef struct stud
    {
        int stuID;
        char stuname[10];
        int score[6];
    }ST;

/*学生成绩管理系统的函数声明*/
void Input(ST stu[],int n,int m);//录入数据函数
void printMenu();//打印系统菜单函数
void zongping(ST stu[],float ave[],int n,int m);//第2个
void jiangxu(ST stu[],float ave[],int n,int m);//第三个
void zongfenjiangxu(ST stu[],int n,int m);//第四个
void zongfenshengxu(ST stu[],int n,int m);//five
void xuehaoshengxu(ST stu[],int n,int m);//six
void zidianshunxu(ST stu[],int n,int m);//seven
void xuehaopai(ST stu[],int n,int m);//第七个
void xingming(ST stu[],int n,int m);
void present(ST stu[],int n,int m);//第九个
void output(ST stu[],float ave[],int n,int m);//第十个





#endif // STU_H_INCLUDED
