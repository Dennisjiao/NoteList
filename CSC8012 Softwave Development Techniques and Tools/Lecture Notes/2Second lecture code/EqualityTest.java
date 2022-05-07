public class EqualityTest
{
    public static void main(String[] args)
    {
        Person p1 = new Person("Anna","Smith");
        Person p2 = new Person("Anna","Smith");
        System.out.println(p1.equals(p2));
        Object o1 = new Person("Anna","Smith");
        Object o2 = new Person("Anna","Smith");
        System.out.println(o1.equals(o2));
    }
}
