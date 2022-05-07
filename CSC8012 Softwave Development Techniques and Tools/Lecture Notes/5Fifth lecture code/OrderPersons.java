import java.util.*;
public class OrderPersons
{
    public static void main(String[] args)
    {
        Person mary = new Person("Mary", "Williams", 25);
        Person john = new Person("John", "Brown", 28);
        Person michael = new Person("Michael", "Brown", 72);
        Person littleMary = new Person("Mary", "Williams", 1);
        ArrayList<Person> persons= new ArrayList<Person>();
        persons.add(mary); persons.add(john);
        persons.add(michael); persons.add(littleMary);
        Collections.sort(persons);
        System.out.println(persons);
    }
}
