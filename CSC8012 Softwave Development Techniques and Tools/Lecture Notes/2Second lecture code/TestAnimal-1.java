public class TestAnimal
{
    public static void main(String[] args)
    {
        Animal a = new Animal("Lion");
        System.out.println(a.getName());
        Herbivore h = new Herbivore("Rhino", 200);
        System.out.println(h.getName());
        System.out.println(h.getGrassWeight());
        Elephant dumbo = new Elephant("Elephant", 350, 1.2);
        System.out.println(dumbo.getName());
        System.out.println(dumbo.getGrassWeight());
        dumbo.printCharacteristics();
        dumbo.printInfo();

        System.out.println();
        Animal dumboNew = new Elephant("Elephant", 350, 1.2);
        System.out.println(dumboNew.getName());
        //System.out.println(dumboNew.getGrassWeight()); // compile-time error
        System.out.println(((Elephant)dumboNew).getGrassWeight());
        System.out.println(((Herbivore)dumboNew).getGrassWeight());
        //dumboNew.printCharacteristics(); // compile-time error
        ((Elephant)dumboNew).printCharacteristics();
    }
}
