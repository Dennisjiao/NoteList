package text;

public class account
{
    public static void main(String[] args)
    {
        BankAccount anAccount1,anAccount2,anAccount3;
        anAccount1 = new BankAccount("",0,Grade.General);
        anAccount2 = new BankAccount("",0,Grade.General);
        anAccount3 = new BankAccount("",0,Grade.General);

        anAccount1.setAccountname("Jiao");
        anAccount2.setAccountname("Rui");
        anAccount3.setAccountname("Peng");

        anAccount1.setBalance(anAccount1.getBalance()+100);
        System.out.println(anAccount1.toString());

        anAccount2.deposit(600.00f);
        System.out.println(anAccount2.toString());

        anAccount3.deposit(500.00f);
        anAccount3.withdraw(400.17f);
        System.out.println(anAccount3.toString());
    }
}