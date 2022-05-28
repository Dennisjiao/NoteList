#ifndef STU_H_INCLUDED
#define STU_H_INCLUDED

#include <stdio.h>
#include <stdlib.h>

#define num 30

/*学生成绩管理系统的函数声明*/
int ReadInfo(int stuID[],float stuScore[]);//录入数据函数
void printMenu();//打印系统菜单函数
void outputInfo(int stuID[],float stuScore[],int number);//输出所有学生的学号和成绩信息




#endif // STU_H_INCLUDED
