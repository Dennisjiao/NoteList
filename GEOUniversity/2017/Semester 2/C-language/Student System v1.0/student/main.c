#include "stu.h"

int main()
{
    int stuID[num]; //����ѧ��ѧ�ŵ�����
    float stuScore[num];//����ѧ���ɼ�������
    char c_Select;//�����û��Բ˵���ѡ��
    char c_Continue; // ѯ���û��Ƿ����������ѡ��
    while(1)
    {
        printMenu();


        scanf(" %c",&c_Select);
        //�����û���ѡ�񣬵�����Ӧ�ĺ�����ɲ���
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



        printf("\n��Ҫ���������𣿣���������Y����N����");
        scanf(" %c",&c_Continue);
        if(('N' == c_Continue)||('n' == c_Continue))
            break;

    }
    return 0;
}
