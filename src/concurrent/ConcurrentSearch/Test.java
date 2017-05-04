package concurrent.ConcurrentSearch;

import java.util.concurrent.ExecutionException;

/**
 * Created by liur on 17-5-2.
 */
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ArraySearch.arr = new int[5];
        ArraySearch.arr[0]=1;
        ArraySearch.arr[1]=2;
        ArraySearch.arr[2]=3;
        ArraySearch.arr[3]=4;
        ArraySearch.arr[4]=5;

        int result = ArraySearch.pSearch(2);
        System.out.println(result);
    }
}
