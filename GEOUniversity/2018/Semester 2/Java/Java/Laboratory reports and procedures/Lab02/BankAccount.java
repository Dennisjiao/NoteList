package text;
enum Grade
{
    VIP,
    General;
}

public class BankAccount 
{
    private static int LAST_ACCOUNT_NUMBER=0;
    String accountname;
    int accountNumber;
    float balance;
    Grade grade;

    public BankAccount()
    {
        accountname = "zhangsan";
        accountNumber=000000;
        balance=0;
        grade=Grade.General;
    }
    public BankAccount(String initname,float initbalance,Grade g)
    {
        accountname = initname;
        accountNumber = ++LAST_ACCOUNT_NUMBER;
        balance = initbalance;
        grade = g;
    }
    public String getAccountname()
    {
        return accountname;
    }
    public int getAccountNumber()
    {
        return accountNumber;
    }
    public float getBalance()
    {
        return balance;
    }
    public Grade getGrade()
    {
        return grade;
    }
    public void setAccountname(String accountname)
    {
        this.accountname = accountname;
    }
    public void setAccountNumber(int accountNumber)
    {
        this.accountNumber = accountNumber;
    }
    public void setBalance(float balance)
    {
        this.balance = balance;
    }
    public void setGrade(Grade grade)
    {
        this.grade = grade;
    }

    public String toString()
    {
        return(grade+" account "+accountname+" "+new java.text.DecimalFormat("000000").format(accountNumber)+" with balance $ "+balance);
    }
    public float deposit(float anAmount)
    {
        balance=balance+anAmount;
        return balance;
    }
    public float withdraw(float anAmount)
    {
        balance=balance-anAmount;
        return balance;
    }
}



