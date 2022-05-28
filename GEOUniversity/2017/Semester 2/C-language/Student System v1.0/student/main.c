#include "stu.h"

int main()
{
    int stuID[num]; //保存学生学号的数组
    float stuScore[num];//保存学生成绩的数组
    char c_Select;//保存用户对菜单的选择
    char c_Continue; // 询问用户是否继续操作的选择
    while(1)
    {
        printMenu();


        scanf(" %c",&c_Select);
        //根据用户的选择，调用相应的函数完成操作
        switch(c_Select)
        {
            case '1':
                break;
            case '2':
                break;
            case '3':
                break;
            case '4':
                break;
            case '5':
                break;
            case '6':
                break;
            case '7':
                break;
            case '0':
                break;

        }



        printf("\n您要继续操作吗？？（请输入Y或者N）：");
        scanf(" %c",&c_Continue);
        if(('N' == c_Continue)||('n' == c_Continue))
            break;

    }
    return 0;
}
