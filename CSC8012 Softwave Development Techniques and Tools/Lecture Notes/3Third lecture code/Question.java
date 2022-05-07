public class Question
{
    public static void main(String[] args)
    {
        int a=1;
        // The + operator is evaluated left-to-right
        System.out.println( a + a + a + a );
        int b=2;
        int c=3;
        int d=4;
        a = b += c = d;
        // Assignment operators are evaluated right-to-left
        System.out.println("a=" + a + " b=" + b + " c=" + c + " d=" + d);
        System.out.println(1+(2*3));
        System.out.println(3*2+1);
    }
}
