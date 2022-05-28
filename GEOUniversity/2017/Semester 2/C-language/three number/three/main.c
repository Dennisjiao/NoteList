 #include<stdio.h>
 #include<math.h>
 int func(int x)//自定义函数实现寻找素数功能
  {
      int i, flag = 1;
     for (i = 2; i <= (int)sqrt((float)x); i++)    //取到平方根就好,(float)x,强制将int x型转化成float型，再将平方根转化为int型
      {
          if (x%i == 0)    //是合数，则标记
              flag = 0;
     }
     return flag;
 }
 int main(void)
 {
     int i, a, b, num,temp;
     while (~scanf("%d%d", &a, &b))    //a到b之间的素数判定
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
                 num++;    //记录素数个数
        }
        printf("%d\n", num);//输出
     }
     return 0;

 }
