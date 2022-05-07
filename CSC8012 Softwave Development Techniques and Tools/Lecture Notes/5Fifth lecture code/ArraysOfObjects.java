public class ArraysOfObjects
{
    public static void main(String[] args)
    {
        String[] strings = {"Adam", "Mary", "William", "Julia"};
        Integer[] integers = {2, 67, 45, 3, 18, -36};
        printObjectArray(strings);
        printObjectArray(integers);
        Object[] objects = {"Mary", 5, 7, "Robert"};
        printObjectArray(objects);
    }

    private static void printObjectArray(Object[] objects)
    {
        for (int i = 0; i < objects.length; i++)
            System.out.print(objects[i] + " ");
        System.out.println();
    }
}
