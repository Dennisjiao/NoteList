public class NumberPair<T extends Number, S extends Number>
{
    private T first;
    private S second;
    public NumberPair(T firstElement, S secondElement)
    {
        first = firstElement;
        second = secondElement;
    }
    public Double add()
    {
        return first.doubleValue() + second.doubleValue();
    }
}