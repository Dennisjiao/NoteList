#ifndef STU_H_INCLUDED
#define STU_H_INCLUDED

#include <stdio.h>
#include <stdlib.h>

#define N 30

typedef struct stud
    {
        int stuID;
        char stuname[10];
        int score;
    }ST;

/*学生成绩管理系统的函数声明*/
void Input(ST stu[],int n);//录入数据函数
void printMenu();//打印系统菜单函数
void zongping(ST stu[],int n);//第2个
void jiangxu(ST stu[],int n);//第三个
void shengxu(ST stu[],int n);//第四个
void xuehaosheng(ST stu[],int n);//第五个
void xingmingshou(ST stu[],int n);
void xuehaopai(ST stu[],int n);//第七个
void xingmingpai(ST stu[],int n);//第八个
void present(ST stu[],int n);//第九个
void output(ST stu[],int n);//第十个





#endif // STU_H_INCLUDED
