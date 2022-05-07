import java.io.*;
public class HandlingExceptions2
{
    static BufferedReader k = new
            BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException
    {
        int number; boolean valid = false;
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
                System.out.println("Incorrect format, try again");
            }
        }
    }
}