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

/*ѧ���ɼ�����ϵͳ�ĺ�������*/
void Input(ST stu[],int n);//¼�����ݺ���
void printMenu();//��ӡϵͳ�˵�����
void zongping(ST stu[],int n);//��2��
void jiangxu(ST stu[],int n);//������
void shengxu(ST stu[],int n);//���ĸ�
void xuehaosheng(ST stu[],int n);//�����
void xingmingshou(ST stu[],int n);
void xuehaopai(ST stu[],int n);//���߸�
void xingmingpai(ST stu[],int n);//�ڰ˸�
void present(ST stu[],int n);//�ھŸ�
void output(ST stu[],int n);//��ʮ��





#endif // STU_H_INCLUDED
