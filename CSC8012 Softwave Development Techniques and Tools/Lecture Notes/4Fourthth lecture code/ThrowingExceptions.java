import java.io.*;
public class ThrowingExceptions
{
    static BufferedReader k = new
            BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException
    {
        int number;
        int i = 3;
        boolean valid = false;

        while (!valid)
        {
            try
            {
                System.out.println("Enter integer: ");
                number = Integer.parseInt(k.readLine());
                System.out.println("You entered: " + number);
                valid = true;
            }
            catch (NumberFormatException e)
            {
                i--;
                if (i > 0)
                {
                    System.out.println("Incorrect format, try again");
                    System.out.println("You have " + i + " more chance(s)");
                }
                else
                    throw new NumberFormatException("after 3 attempts");
            }
        }
    }
}