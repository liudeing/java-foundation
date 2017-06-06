package algorithm.search;

/**
 * 折半查找
 * Created by liur on 17-5-17.
 */
public class BinarySearchTest {
    public static <Integer extends Comparable<? super Integer>> int binarySearch(Integer[] a, Integer x) {
        int low = 0, high = a.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid].compareTo(x) < 0)
                low = mid + 1;
            else if (a[mid].compareTo(x) > 0)
                high = mid - 1;
            else
                return mid; //Found
        }
        return -1; //NOT FOUND
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 5, 6, 8, 9};

        int b = binarySearch(arr, 2);

        System.out.println(b);
    }
}
