public class Pair<T,S>
{
    private T first;
    private S second;
    public Pair(T firstElement, S secondElement)
    {
        first = firstElement;
        second = secondElement;
    }
    public T getFirst()
    {
        return first;
    }
    public S getSecond()
    {
        return second;
    }
    public <E extends Number> void operation(E value)
    {
        if (value instanceof Integer)
            System.out.println(first);
        else
        if (value instanceof Double)
            System.out.println(second);
        else
            System.out.println(first + " " + second);
    }
}
