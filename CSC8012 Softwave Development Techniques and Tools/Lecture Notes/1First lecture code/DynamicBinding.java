public class DynamicBinding
{
    public static void main(String[] args)
    {
        Animal a, aRef;
        Herbivore h;
        a = new Animal("Lion");
        a.printInfo(); // calls Animal version of printInfo
        h = new Herbivore("Rhino",200);
        h.printInfo(); // calls Herbivore version of printInfo
        aRef = h;
        aRef.printInfo(); // calls Herbivore version of printInfo
    }
}
