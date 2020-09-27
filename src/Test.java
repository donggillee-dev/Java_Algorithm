import java.io.*;
import java.util.*;

public class Test {
    public static void main(String[] args) throws IOException {
        int[] arr = {-7, 4, -3, 6, 3, -8, 3, 4};
        int ret = fastMaxSum(arr, 0, arr.length - 1);
        System.out.println(ret);
    }
    public static int fastMaxSum(int[] arr, int lo, int hi) {
        if(lo == hi) return arr[lo];

        int mid = (lo + hi) / 2;
        int left = Integer.MIN_VALUE, right = Integer.MIN_VALUE, sum = 0;

        for(int i = mid; i >= lo; i--) {
            sum += arr[i];
            left = Integer.max(left, sum);
        }
        sum = 0;

        for(int i = mid + 1; i <= hi; i++) {
            sum += arr[i];
            right = Integer.max(right, sum);
        }
        int single = Integer.max(fastMaxSum(arr, lo, mid), fastMaxSum(arr, mid + 1, hi));

        return Integer.max(left + right, single);
    }
}
