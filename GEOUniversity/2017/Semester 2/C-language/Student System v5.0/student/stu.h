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

/*ѧ���ɼ�����ϵͳ�ĺ�������*/
void Input(ST stu[],int n,int m);//¼�����ݺ���
void printMenu();//��ӡϵͳ�˵�����
void zongping(ST stu[],float ave[],int n,int m);//��2��
void jiangxu(ST stu[],float ave[],int n,int m);//������
void zongfenjiangxu(ST stu[],int n,int m);//���ĸ�
void zongfenshengxu(ST stu[],int n,int m);//five
void xuehaoshengxu(ST stu[],int n,int m);//six
void zidianshunxu(ST stu[],int n,int m);//seven
void xuehaopai(ST stu[],int n,int m);//���߸�
void xingming(ST stu[],int n,int m);
void present(ST stu[],int n,int m);//�ھŸ�
void output(ST stu[],float ave[],int n,int m);//��ʮ��





#endif // STU_H_INCLUDED
