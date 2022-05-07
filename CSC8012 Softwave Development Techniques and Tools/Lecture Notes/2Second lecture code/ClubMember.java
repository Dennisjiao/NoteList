import java.io.*;
public class ClubMember extends Person {
    private static final int SENIORAGE = 65;
    private static final double NORMALFEE = 10;
    private static final double SENIORFEE = 5;
    private int personAge;
    private boolean feePaid;

    public ClubMember() {
        super();
        personAge = 0;
        feePaid = false;
    }

    public ClubMember(String name1, String name2, int age, boolean paid) {
        super(name1, name2);
        personAge = age;
        feePaid = paid;
    }
    public void setNameAge(String name1, String name2, int age)
    {
        setName(name1, name2);
        personAge = age;
    }
    public int getAge()
    {
        return personAge;
    }
    public boolean checkFeePaid()
    {
        return feePaid;
    }
    public void setAge(int age)
    {
        personAge = age;
    }
    public void setFeePaid(boolean b)
    {
        feePaid = b;
    }
    public String toString()
    {
        String message = " Paid";
        if (!feePaid)
            message = " Not" + message;
        return super.toString() + " " + personAge + message;
    }
    public boolean isSenior()
    {
        return (personAge >= SENIORAGE);
    }
    public double moneyDue()
    {
        if (!isSenior())
            return NORMALFEE;
        else
            return SENIORFEE;
    }
    public void payAndPrintReceipt(PrintWriter f)
    {
        if (!feePaid)
        {
            feePaid = true;
            printName(f);
            f.println(moneyDue() + " pounds received with thanks.");
        }
        else
            System.out.println("You have already paid your fee.");
    }
    public void printReminder(PrintWriter f, Person manager)
    {
        if (!feePaid)
        {
            f.print("Dear ");
            printShortName(f);
            f.println(",");
            f.println("Your fee of " + moneyDue() +
                    " pounds is now due.");
            f.print("Yours sincerely, ");
            manager.printName(f);
        }
    }
}