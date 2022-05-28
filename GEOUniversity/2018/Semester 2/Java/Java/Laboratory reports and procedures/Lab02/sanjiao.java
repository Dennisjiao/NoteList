package sanjiao;

import java.util.Scanner;
import java.io.*;

class NotSanjiaoException extends Exception{
    NotSanjiaoException(){
      super("Exception happen!");
    }

};
class sanjiao
{
    double x,y,z;
    public sanjiao()
    {
       this(3,4,5);
    }
    public sanjiao(double a,double b,double c)
    {
       x=a;
       y=b;
       z=c;
    }
    public double getX()
    {
       return x;
    }
    public double getY()
    {
       return y;
    }
    public double getZ()
    {
       return z;
    }
    public void setX(double newx)
    {
       x=newx;
    }
    public void setY(double newy)
    {
       y=newy;
    }
    public void setZ(double newz)
    {
       z=newz;
    }
    public double getArea()throws NotSanjiaoException
    {
       double p,area;
       if(x+y<=z||x+z<=y||y+z<=x)
       {
           throw new NotSanjiaoException();
       }
       else
       {
           p=(x+y+z)/2;
           area = Math.sqrt(p*(p-x)*(p-y)*(p-z));
       }
       return area;
    }
    public String showInfo()throws NotSanjiaoException
    {
       if(x+y<=z||x+z<=y||y+z<=x)
       {
           throw new NotSanjiaoException();
       }
       return(x+" "+y+" "+z);
    }
}

