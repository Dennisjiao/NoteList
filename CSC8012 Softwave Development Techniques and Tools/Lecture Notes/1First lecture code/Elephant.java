public class Elephant extends Herbivore
{
    private double tuskLength;
    public Elephant(String n, int w, double l)
    {
        super(n, w);
        tuskLength = l;
    }
    public void printCharacteristics()
    {
        System.out.print(getName() + " ");
        System.out.print(getGrassWeight() + " ");
        System.out.println(tuskLength + " ");
    }
    public void printInfo()
    {
        super.printInfo();
        System.out.println(tuskLength + " ");
    }
}
