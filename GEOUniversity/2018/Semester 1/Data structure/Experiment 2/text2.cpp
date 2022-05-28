#include <stdio.h>
#include <stdlib.h>
#include<malloc.h>
#include<string.h>
#include<math.h>
#define OK        1
#define ERROR     0
#define OVERFLOW -1
typedef char ElemType;
typedef int  Status;
#define STACK_INIT_SIZE 1000
typedef struct{
    ElemType *base;
    ElemType *top;
    int       size;
    }SqStack;


Status InitStack(SqStack &S)//初始化
{
    S.base=(ElemType *)malloc(STACK_INIT_SIZE*sizeof(ElemType));
    if(!S.base)exit (OVERFLOW);
    S.top=S.base;
    S.size=STACK_INIT_SIZE;
    return OK;
}


Status GetTop(SqStack S)//取栈顶
{
    if(S.top!=S.base)//栈非空
    return *(S.top-1);
}


Status Push(SqStack &S,ElemType e)//入栈
{
    if(S.top-S.base>=S.size)
    return  ERROR;
    *S.top++=e;
    return OK;
}


Status Pop(SqStack &S,ElemType &e)//出栈
{
    if(S.top==S.base)//栈空
    return ERROR;
    e=*--S.top;
    return OK;
}


int In(ElemType e)//判断读入字符是否为运算符
{
	if(e<'9'&&e>='0')
	    return 0;
	else
	    return 1;
}
//比较运算符的优先级
 ElemType Precede(ElemType a,ElemType b){
  ElemType Yunsuanfu[7][7]={{'>','>','<','<','<','>','>'},
	                        {'>','>','<','<','<','>','>'},
                            {'>','>','>','>','<','>','>'},
	                        {'>','>','>','>','<','>','>'},
	                        {'<','<','<','<','<','=','\0'},
	                        {'>','>','>','>','\0','>','>'},
                            {'<','<','<','<','<','\0','='}
                           };
    int m,n;
    switch(a){
    case '+':m=0;
		     break;
    case '-':m=1;
             break;
    case '*':m=2;
             break;
    case '/':m=3;
             break;
    case '(':m=4;
             break;
    case ')':m=5;
             break;
    case '#':m=6;
             break;}
    switch(b){
    case '+':n=0;
		     break;
    case '-':n=1;
             break;
    case '*':n=2;
             break;
    case '/':n=3;
             break;
    case '(':n=4;
             break;
    case ')':n=5;
             break;
    case '#':n=6;
             break;}
    return Yunsuanfu[m][n];
}



ElemType Operate(ElemType a,ElemType theta,ElemType b)
   {ElemType c;
	a=a-'0';
	b=b-'0';
switch(theta)
    {
        case '+':
            c=a+b+'0';break;
        case '-':
            c=a-b+'0';break;
        case '*':
            c=a*b+'0';break;
        case '/':
            c=a/b+'0';break;
    }
    return c;
}

int  EvaluateExpression(){
	SqStack OPTR;
	SqStack OPND;
	char ch,a,b,theta,x;
	InitStack(OPTR);Push(OPTR,'#');
	InitStack(OPND); ch=getchar();
	while(ch!='#'||GetTop(OPTR)!='#'){
		if(!In(ch))
		{
			Push(OPND,ch);
			ch=getchar();
		}
		else
		{
           switch(Precede(GetTop(OPTR),ch))
           	{
              case '<':
                Push(OPTR,ch);
                ch=getchar();break;
              case '>':
                Pop(OPTR,theta);
                Pop(OPND,b);
                Pop(OPND,a);
                Push(OPND,Operate(a,theta,b));break;
              case '=':
                Pop(OPTR,x);
                ch=getchar();break;
            }
        }
    }
    return GetTop(OPND)-'0';
}

int main(){
	printf("请输入算术表达式并以#结束\n");
	printf("%d\n",EvaluateExpression());
	return 0;
}
