import java.io.*;
public class HandlingExceptions3
{
    static BufferedReader k = new
    BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args)
    {
        int number;
        try
        {
            System.out.println("Enter integer: ");
            number = Integer.parseInt(k.readLine());
            System.out.println("You entered: " + number);
        }
        catch (NumberFormatException e1)
        {
            System.out.println(e1);
        }
        catch (IOException e2)
        {
            System.out.println(e2);
        }
    }
}