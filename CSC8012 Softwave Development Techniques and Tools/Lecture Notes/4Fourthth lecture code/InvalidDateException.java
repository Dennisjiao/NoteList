public class InvalidDateException extends Exception
{
    public InvalidDateException()
    {
        super("Invalid date");
    }
    public InvalidDateException(String s)
    {
        super(s);
    }
}