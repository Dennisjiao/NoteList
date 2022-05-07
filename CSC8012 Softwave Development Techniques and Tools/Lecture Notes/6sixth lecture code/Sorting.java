public class Sorting {
    public static void main(String[] args){

    }
    public static <E extends Comparable<? super E>> void selectionSort(E[] a)
    {
        E temp;
        int minIndex;
        for (int j=0; j<a.length-1; j++) {
            // this is the outer loop
            minIndex = j;
            for (int k = j + 1; k < a.length; k++) {
                if (a[k].compareTo(a[minIndex]) < 0) {
                    minIndex = k;
                }
            }
            temp = a[minIndex];
            a[minIndex] = a[j];
            a[j] = temp;
        }
    }
    public static <E extends Comparable<? super E>> void insertionSort(E[] a)
    {
        for (int i = 1; i < a.length; i++)
        {
            E value = a[i];
            int j;
            for (j = i; j > 0; j--)
            {
                if (a[j-1].compareTo(value)<0)
                {
                    break;
                }
                else
                {
                    a[j] = a[j-1];
                }
            }
            a[j] = value;
        }
    }
    public static <E extends Comparable<? super E>>  int seqSearch(E[] a, E searchItem) {
        int loc;
        boolean found = false;
        for (loc = 0; loc < a.length; loc++) {
            if (a[loc].compareTo(searchItem) == 0) {
                found = true;
                break;
            }
        }

        if (found)
            return loc;
        else
            return -1;
    }
    public static <E extends Comparable<? super E>>
    int binarySearch(E[] a, E searchItem) {
        int first = 0;
        int last = a.length - 1;
        int mid = -1;
        boolean found = false;
        while (first <= last && !found)
        {
            // comparing searchItem with the middle element of the
            // currently examined portion of the array
            mid = (first + last) / 2;
            int result = a[mid].compareTo(searchItem);
            if (result == 0)
                found = true;
            else
            if (result > 0)
                last = mid - 1;
            else
                first = mid + 1;
        }
        if (found)
            return mid;
        else
            return -1;
    }
}