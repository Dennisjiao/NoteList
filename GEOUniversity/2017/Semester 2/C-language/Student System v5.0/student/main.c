#include "stu.h"



int main()
{
    char c_Select;//�����û��Բ˵���ѡ��
    char c_Continue; // ѯ���û��Ƿ����������ѡ��


    while(1)
    {
        printMenu();
        scanf(" %d",&c_Select);
        int n,m;
        ST stu[N];
        float ave[6];
        switch(c_Select)
        {
            case 1:

                printf("Please input how many student n:");
                scanf("%d",&n);
                printf("Please input how many subject m:");
                scanf("%d",&m);
                printf("Please input such as (ID name score)\n");
                Input(stu,n,m);
                break;
            case 2:
                zongping(stu,ave,n,m);
                break;

            case 3:
                jiangxu(stu,ave,n,m);
                break;

            case 4:
                zongfenjiangxu(stu,n,m);
                break;

            case 5:
                zongfenshengxu(stu,n,m);
                break;

            case 6:
                xuehaoshengxu(stu,n,m);
                break;

            case 7:
                zidianshunxu(stu,n,m);
                break;

            case 8:
                xuehaopai(stu,n,m);
                break;

            case 9:
                xingming(stu,n,m);
                break;

            case 10:
                present(stu,n,m);
                break;

            case 11:
                output(stu,ave,n,m);
                break;

            case 0:
                return 0;
                break;

        }



        printf("\n��Ҫ���������𣿣���������Y����N����");
        scanf(" %c",&c_Continue);
        if(('N' == c_Continue)||('n' == c_Continue))
            break;

    }
    return 0;
}
