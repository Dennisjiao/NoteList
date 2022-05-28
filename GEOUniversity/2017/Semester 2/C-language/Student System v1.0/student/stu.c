#include "stu.h"

/* ***  本文件实现学生成绩管理系统的主要操作函数  *** */
/*
函数名称：ReadInofo
函数功能：录入学生的学号和成绩，并返回录入的有效数据的个数（即学生人数）
函数参数：学生学号和成绩的两个一维数组
函数返回值：学生信息通过数组传递回调用函数，学生人数通过return语句返回到调用函数
*/
int ReadInfo(int stuID[],float stuScore[])
{
    int n=0,i;
    for(i=0;i<)


   return n; //返回输入的学生人数

}
/*
函数名称：printMenu
函数功能：在屏幕输出系统的菜单
函数参数：  无
函数返回值： 无
*/
void printMenu()
{
    //此处添加输出菜单的代码
    printf("1.Input record\n");
    printf("2.Caculate total and average score of course\n");
    printf("3.Sort in descending order by score\n");
    printf("4.Sort in ascending order by number\n");
    printf("5.Search by number\n");
    printf("6.Statistic analysis\n");
    printf("7.List record\n");
    printf("0.Exit\n");
    printf("Please enter your choice\n");
}
/*
函数名称：outputInofo
函数功能：将学生的学号和成绩信息显示到屏幕
函数参数：学生学号和成绩的两个一维数组，学生人数number
函数返回值： 无
*/
void outputInfo(int stuID[],float stuScore[],int number)
{
    //此处添加输出学生信息的代码

}


