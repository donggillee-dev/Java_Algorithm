package BOJ.DivideAndConquer;
import java.io.*;
import java.util.*;

public class MergeSortTest {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int[] arr = {10,9,8,7,6,5,4,3,2,1};
        Merge(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static void Merge(int[] arr, int start, int end) {
        if(start < end) {
            int mid = (start + end) / 2;
            Merge(arr, start, mid);
            Merge(arr, mid + 1, end);
            MergeSort(arr, start, mid, end);
        }
    }
    private static void MergeSort(int[] arr, int start, int mid, int end) {
        int s1 = start;
        int s2 = mid + 1;
        int k = start;
        int[] tmp = new int[arr.length];

        while(s1 <= mid && s2 <= end) {
            if(arr[s1] < arr[s2]) {
                tmp[k++] = arr[s1++];
            } else {
                tmp[k++] = arr[s2++];
            }
        }

        if(s1 <= mid) {
            while(k <= end) {
                tmp[k++] = arr[s1++];
            }
        }

        if(s2 <= end) {
            while(k <= end) {
                tmp[k++] = arr[s2++];
            }
        }

        for(int i = start; i <= end; i++) {
            arr[i] = tmp[i];
        }
    }
}
