#include <stdio.h>
#include <stdlib.h>

int main()
{   int a[10],b,c,i;
    for(i=0;i<9;i++){
        scanf("%d",&a[i]);
    }
    scanf("%d",&b);
    for(i=0;i<9;i++)//���Ҳ���λ��
    {
        if (b<a[i])
            {
            break;
            }
    }
    for(c=8;c>=i;c--) //i�����������
    {
        a[c+1]=a[c];
    }
    a[i]=b;//�������ݵ�iλ
    for(i=0;i<10;i++){
            printf("%d ",a[i]);
    }
    return 0;
}
