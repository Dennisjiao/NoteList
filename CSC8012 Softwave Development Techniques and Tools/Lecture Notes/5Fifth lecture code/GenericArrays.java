public class GenericArrays
{
    public static void main(String[] args)
    {
        String[] strings = {"Adam", "Mary", "William", "Julia"};
        Integer[] integers = {2, 67, 45, 3, 18, -36};
        printArray(strings);
        printArray(integers);
    }
    private static <E> void printArray(E[] a)
    {
        for (E elem: a)
            System.out.print(elem + " ");
        System.out.println();
    }
}
