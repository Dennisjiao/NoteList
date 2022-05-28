#include "stu.h"



int main()
{
    char c_Select;//保存用户对菜单的选择
    char c_Continue; // 询问用户是否继续操作的选择

    while(1)
    {
        printMenu();
        scanf(" %d",&c_Select);
        int n;
        ST stu[N];

        switch(c_Select)
        {
            case 1:

                printf("please input how many student n:");
                scanf("%d",&n);
                printf("please input such as (ID name score)\n");
                Input(stu,n);
                break;
            case 2:
                zongping(stu,n);
                break;
            case 3:
                jiangxu(stu,n);
                break;
            case 4:
                shengxu(stu,n);
                break;
            case 5:
                xuehaosheng(stu,n);
                break;
            case 6:
                xingmingshou(stu,n);




                break;
            case 7:
                xuehaopai(stu,n);
                break;
            case 8:
                xingmingpai(stu,n);
                break;
            case 9:
                present(stu,n);
                break;
            case 10:
                output(stu,n);




                break;
            case 0:
                return 0;
                break;

        }



        printf("\n您要继续操作吗？？（请输入Y或者N）：");
        scanf(" %c",&c_Continue);
        if(('N' == c_Continue)||('n' == c_Continue))
            break;

    }
    return 0;
}
