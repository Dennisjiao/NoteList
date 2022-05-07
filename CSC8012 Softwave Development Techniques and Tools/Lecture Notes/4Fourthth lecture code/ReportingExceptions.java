import java.io.*;
public class ReportingExceptions
{
    static BufferedReader k = new
            BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException
    {
        int number;
        System.out.println("Enter integer: ");
        number = Integer.parseInt(k.readLine());
        System.out.println("You entered: " + number);
    }
}