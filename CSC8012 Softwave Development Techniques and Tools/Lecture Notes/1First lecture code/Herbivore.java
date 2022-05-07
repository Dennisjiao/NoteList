public class Herbivore extends Animal
{
    private int grassNeeded;
    public Herbivore()
    {
        super();
        grassNeeded = 0;
    }
    public Herbivore(String n, int g)
    {
        super(n);
        grassNeeded = g;
    }
    public void setInfo(String n, int g)
    {
        // example of overloading
        setInfo(n);
        grassNeeded = g;
    }
    public void printInfo()
    {
        // example of overriding
        super.printInfo();
        System.out.print(grassNeeded + " ");
    }
    public int getGrassWeight()
    {
        return grassNeeded;
    }
}
