public class Animal
{
    private String name;
    public Animal()
    {
        name = "";
    }

    public Animal(String n)
    {
        name = n;
    }


    public void setInfo(String n)
    {
        name = n;
    }

    public void printInfo()
    {
        System.out.print(name + " ");
    }

    public String getName()
    {
        return name;
    }

    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof Animal)) return false;
        Animal a = (Animal) obj;
        if (name == null)
            return a.name == null;
        else
            return name.equals(a.name);
    }

    public int hashCode()
    {
        int code = 17;
        code = 37 * code + name.hashCode();
        return code;
    }
}
