import java.io.*;
public class Person {
    private String firstName;
    private String surname;

    public Person() {
        firstName = "";
        surname = "";
    }

    public Person(String name1, String name2) {
        firstName = name1;
        surname = name2;
    }
    public void setName(String name1, String name2)
    {
        firstName = name1;
        surname = name2;
    }
    public String getFirstName()
    {
        return firstName;
    }
    public String getSurname()
    {
        return surname;
    }
    public String toString()
    {
        return firstName + " " + surname;
    }
    public void printName(PrintWriter f)
    {
        f.println(firstName + " " + surname);
    }
    public char initial()
    {
        return firstName.charAt(0);
    }
    public void printShortName(PrintWriter f)
    {
        f.print(initial() + ". " + surname);
    }
    public boolean equals(Person otherPerson)
    {
        return (firstName.equals(otherPerson.firstName)
                && surname.equals(otherPerson.surname));
    }
    //public boolean equals(Object obj)
    //{
    //    if (this == obj) return true;
    //    if (!(obj instanceof Person)) return false;
    //    Person p = (Person) obj;
    //    return (firstName.equals(p.firstName)
    //            && surname.equals(p.surname));
    //}
}