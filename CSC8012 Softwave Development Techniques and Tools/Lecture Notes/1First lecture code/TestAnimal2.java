public class TestAnimal2 {
    public static void main(String[] args)
    {
        Animal a, aRef, animalWithLongerName;
        Herbivore h, hRef;
        a = new Animal("Lion");
        h = new Herbivore("Rhino",200);
        aRef = h;
        // hRef = a; // compile-time error
        // hRef = (Herbivore) a; // run-time error (ClassCastException)
        hRef = (Herbivore) aRef;

        if (a.getName().length() > h.getName().length())
            animalWithLongerName = a;
        else
            animalWithLongerName = h;
        System.out.println(animalWithLongerName.getName());
    }
}
