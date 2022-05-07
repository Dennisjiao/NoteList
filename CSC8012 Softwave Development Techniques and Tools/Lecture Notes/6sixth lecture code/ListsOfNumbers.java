import java.util.*;
public class ListsOfNumbers {
    public static void main(String[] args) {
        List<Integer> myInts = Arrays.asList(1, 2, 3, 4);
        List<Double> myDoubles = Arrays.asList(3.14, 6.28);
        List<Object> myObjs = new ArrayList<Object>();
        copy(myInts, myObjs);
        copy(myDoubles, myObjs);
        printList(myObjs);
    }

    private static void copy(List<? extends Number> source,
                             List<? super Number> destination) {
        for (Number number : source) destination.add(number);
    }

    private static <E> void printList(List<E> a)
    {
        for (E elem: a)
            System.out.print(elem + " ");
        System.out.println();
    }
}