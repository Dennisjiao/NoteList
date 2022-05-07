public class InstanceOf
{
    public static void main(String[] args){
        Animal a, aRef;
        Herbivore h;
        a = new Animal("Lion");
        h = new Herbivore("Rhino",200);
        aRef = h;
        if (a instanceof Herbivore)
            System.out.println("a is an instance of Herbivore");
        else
            System.out.println("a is not an instance of Herbivore");
        if (aRef instanceof Herbivore)
            System.out.println("aRef is an instance of Herbivore");
        else
            System.out.println("aRef is not an instance of Herbivore");
    }
}
