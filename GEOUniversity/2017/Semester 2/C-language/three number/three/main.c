 #include<stdio.h>
 #include<math.h>
 int func(int x)//�Զ��庯��ʵ��Ѱ����������
  {
      int i, flag = 1;
     for (i = 2; i <= (int)sqrt((float)x); i++)    //ȡ��ƽ�����ͺ�,(float)x,ǿ�ƽ�int x��ת����float�ͣ��ٽ�ƽ����ת��Ϊint��
      {
          if (x%i == 0)    //�Ǻ���������
              flag = 0;
     }
     return flag;
 }
 int main(void)
 {
     int i, a, b, num,temp;
     while (~scanf("%d%d", &a, &b))    //a��b֮��������ж�
     {
         num = 0;
         if (a > b)
         {
             temp = a;
            a = b;
             b = temp;
         }
         for (i = a; i <= b; i++)
         {
             if (func(i) == 1)
                 num++;    //��¼��������
        }
        printf("%d\n", num);//���
     }
     return 0;

 }
