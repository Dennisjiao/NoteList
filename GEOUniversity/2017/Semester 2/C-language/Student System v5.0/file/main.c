#include <stdio.h>
#include <stdlib.h>

#define NUM 50

int ReadFile(int stuID[],float stuScore[]);
void WriteFile(int stuID[],float stuScore[],int n);


int main()
{
    int stuID[NUM];
    float stuScore[NUM];
    int i,n;
    n=ReadFile(stuID,stuScore);
    for(i=0;i<n;i++)
     {
        printf("%d %f\n",stuID[i],stuScore[i]);
     }

    WriteFile(stuID,stuScore,n);
    printf("Hello world!\n");
    return 0;
}
int ReadFile(int stuID[],float stuScore[])
{
    FILE *fp;
    int i;
    if((fp=fopen("studentIN.txt","r"))==NULL)
    {
        printf("打开文件失败！");
        exit(0);
     }
     for(i=0;!feof(fp);i++)
     {
         fscanf(fp,"%d %f",&stuID[i],&stuScore[i]);
     }
     fclose(fp);
     return i;
}
void WriteFile(int stuID[],float stuScore[],int n)
{
    FILE *fp;
    int i;
    if((fp=fopen("studentOUT.txt","w"))==NULL)
    {
        printf("打开文件失败！");
        exit(0);
     }
     for(i=0;i<n;i++)
     {
         fprintf(fp,"%d %.2f\n",stuID[i],stuScore[i]);
     }
     fclose(fp);
}
