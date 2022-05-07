import java.io.*;
import java.util.*;
public class SportsClubRunning
{
    static Scanner k = new Scanner(System.in);
    public static void main(String[] args) throws FileNotFoundException
    {
        PrintWriter outFile = new PrintWriter("H:\\csc8012\\clubR.txt");
        Person boss = new Person("Tom","Smith");
        Club sportClub = new Club(2, boss);
        printMenu();
        char ch = k.next().charAt(0);
        k.nextLine();
        while (ch != 'f')
        {
            switch(ch)
            {
                case 'l': sportClub.listMembers();
                    break;
                case 'n': sportClub.resetFeePaid();
                    break;
                case 'p': sportClub.payFee(readNames(),outFile);
                    break;
                case 'r': if (sportClub.placeAvailable())
                {
                    ClubMember member = readMemberData(sportClub);
                    if (member != null)
                        sportClub.register(member);
                    else
                        System.out.println("You are already registered as a member.");
                }
                else
                    System.out.println("Sorry, we cannot accept any more members.");
                    break;
                case 's': sportClub.sendReminders(outFile);
                    break;
                default: System.out.println("Invalid entry, try again");
            }
            printMenu();
            ch = k.next().charAt(0);
            k.nextLine();
        }
        outFile.close();
    }
    private static void printMenu()
    {
        System.out.println("------------------------------");
        System.out.println("MENU");
        System.out.println("f - finish");
        System.out.println("l - list all members");
        System.out.println("n - reset fee information");
        System.out.println("p - accept payment");
        System.out.println("r - register a new member");
        System.out.println("s - send reminders");
        System.out.println("------------------------------");
        System.out.println("Type a letter and press Enter");
    }
    private static Person readNames()
    {
        System.out.println("Enter person’s first name and surname,"
                + " and press Enter");
        String name1 = k.next();
        String name2 = k.next();
        k.nextLine();
        return new Person(name1, name2);
    }
    private static ClubMember readMemberData(Club club)
    {
        Person p = readNames();
        if (club.memberNumber(p) == -1)
        {
            System.out.println("Enter " + p + "’s age, and press Enter");
            int age = k.nextInt();
            k.nextLine();
            return new ClubMember(p.getFirstName(), p.getSurname(),
                    age,false);
        }
        else
        {
            return null;
        }
    }
}
