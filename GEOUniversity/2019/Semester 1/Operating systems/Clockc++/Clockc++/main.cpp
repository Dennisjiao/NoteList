#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
using namespace std;

int main()
{
    double p;
    int a,m,r,n,e,o,u;
    struct tm *local;
    char *st,s[27];
    time_t t;
    t=time(NULL);
    local=localtime(&t);
    st=asctime(local);
    int k;
    int x,x1,x2;

    printf("���붨ʱ���ӣ�");
    scanf("%d",&k);

    printf("���봦��ʱ��(ms)��");
    scanf("%d",&x);



    int i;
    for(i=0;i<27;i++)
    {
        s[i]=*st;
        st++;
    }
    printf("��ǰϵͳʱ��Ϊ");
    for(i=4;i<27;i++)
    {
        printf("%c",s[i]);
    }


    int count=0,count1=0,w;
    while(k!=0)
    {
        do
        {
            printf("��ִ����һ��ָ�\n");
            count++;
            printf("������һ�����֣�1��ʾ�жϣ�0��ʾ���жϣ���\n");
            scanf("%d",&w);

        }
        while(w==0);
        if(w==1)
            printf("�ֳ��������\n");
        count1++;
        k--;
        if(k!=0)
            printf("��������ɵ���\n");
    }
    p=count1*2;
    a=(int)count1*2;
    if((p-a)>=0.5)
        p=a+1;
    else p=a;
    m=(int)p;


    x1=x/1000;//��
    x2=(x/1000)/60;//fen


    printf("�����ѹ�ʱ��Ϊ��%d\n",m);

    r=s[17]-48;
    n=s[18]-48;
    e=r*10+n;
    p=p+e;

    if(p<60)
    {
        s[17]=((int)p/10)+48;
        s[18]=((int)p%10)+48;
        for(i=4;i<27;i++)
            printf("%c",s[i]);
    }
    else
    {
        s[14]=s[14]-48;
        s[15]=s[15]-48;
        o=s[14]*10+s[15];
        o=o+(int)p/60;
    }
printf("ϵͳ����ʱ��Ϊ��");
    if(o<60)
    {
        s[14]=(o/10)+48;
        s[15]=(o%10)+48;
        s[17]=((int)p%60)/10+48;
        s[18]=((int)p%60)%10+48;
        for(i=4;i<27;i++)
        {
            printf("%c",s[i]);
        }
    }
    else
    {
        s[11]=s[11]-48;
        s[12]=s[12]-48;
        u=s[11]*10+s[12];
        u=u+o/60;
        if(u<24)
        {
        s[11]=(u/10)+48;
        s[12]=(u%10)+48;
        s[14]=(u%60)/10+48;
        s[15]=(u%60)%10+48;
        s[17]=((int)p%60)/10+48;
        s[18]=((int)p%60)%10+48;
        for(i=4;i<27;i++)
        {
            printf("%c",s[i]);
        }

        }
    }
}
