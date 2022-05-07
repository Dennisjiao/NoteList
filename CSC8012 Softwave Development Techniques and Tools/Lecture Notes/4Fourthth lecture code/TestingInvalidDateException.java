import java.util.*;
public class TestingInvalidDateException
{
    static Scanner k = new Scanner(System.in);
    public static void main(String[] args)
    {
        int number;
        try
        {
            System.out.print("Enter integer: ");
            number = k.nextInt();
            System.out.println("Month name: " + monthName(number));
        }
        catch (InvalidDateException e)
        {
            System.out.println(e);
        }
    }
    private static String monthName(int monthNumber) throws InvalidDateException
    {
        String name;
        switch(monthNumber)
        {
            case 1: name = "January";
                break;
            case 2: name = "February";
                break;
            case 3: name = "March";
                break;
            case 4: name = "April";
                break;
            case 5: name = "May";
                break;
            case 6: name = "June";
                break;
            case 7: name = "July";
                break;
            case 8: name = "August";
                break;
            case 9: name = "September";
                break;
            case 10: name = "October";
                break;
            case 11: name = "November";
                break;
            case 12: name = "December";
                break;
            default: throw new InvalidDateException("Invalid month number: " + monthNumber);
        }
        return name;
    }
}