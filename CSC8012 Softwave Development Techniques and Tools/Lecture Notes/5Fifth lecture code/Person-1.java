public class Person implements Comparable<Person> {
    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        if (age < 0) throw new IllegalArgumentException();
        this.age = age;
    }

    public int compareTo(Person p) {
        int lnCmp = lastName.compareTo(p.lastName);
        if (lnCmp != 0) return lnCmp;
        int fnCmp = firstName.compareTo(p.firstName);
        if (fnCmp != 0) return fnCmp;
        else return age - p.age;
    }

    public String toString()
    {
        return firstName + " " + lastName + " - " + age;
    }
}