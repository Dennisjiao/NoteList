public class Animal
{
    private String name;
    public Animal()
    {
        name = "";
    }

    public Animal(String n)
    {
        name = n;
    }

    public void setInfo(String n)
    {
        name = n;
    }

    public void printInfo()
    {
        System.out.print(name + " ");
    }

    public String getName()
    {
        return name;
    }
}
