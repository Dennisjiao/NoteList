import java.io.*;
public class Club
{
    private int maxMembers;
    private Person manager;
    private int numberOfMembers;
    private ClubMember[] members;
    public Club(int maxNumber, Person m)
    {
        maxMembers = maxNumber;
        manager = m;
        members = new ClubMember[maxMembers];
        numberOfMembers = 0;
    }
    public boolean placeAvailable()
    {
        return (numberOfMembers < maxMembers);
    }
    public void register(ClubMember m)
    {
        members[numberOfMembers] = m;
        numberOfMembers++;
    }
    public void listMembers()
    {
        for(int i=0; i<numberOfMembers; i++)
        {
            System.out.println(members[i]);
        }
    }
    public void sendReminders(PrintWriter f)
    {
        for(int i=0; i<numberOfMembers; i++)
        {
            if (!members[i].checkFeePaid())
                members[i].printReminder(f,manager);
        }
    }
    public void resetFeePaid()
    {
        for(int i=0; i<numberOfMembers; i++)
        {
            members[i].setFeePaid(false);
        }
    }
    public int memberNumber(Person p)
    {
        for(int i=0; i<numberOfMembers; i++)
        {
            Person m = members[i];
            if (m.equals(p))
                return i;
        }
        return -1;
    }
    public void payFee(Person p, PrintWriter f)
    {
        int n = memberNumber(p);
        if (n == -1)
            System.out.println("Sorry, you are not a member.");
        else
            members[n].payAndPrintReceipt(f);
    }
}
