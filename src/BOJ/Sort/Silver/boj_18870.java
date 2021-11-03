package BOJ.Sort.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//Logic
//처음에는 Arrays.sort로 해결했던 문제
//최악의 경우 Arrays.sort는 N^2 시간복잡도

public class boj_18870 {
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[(left + right) >> 1];

        while(left <= right) {
            while(arr[left] < pivot) left++;
            while(arr[right] > pivot) right--;

            if(left <= right) {
                swap(arr, left++, right--);
            }
        }

        return left;
    }
    private static void qSort(int[] arr, int low, int high) {
        if(low >= high) return;

        int pivot = partition(arr, low, high);
        qSort(arr, low, pivot - 1);
        qSort(arr, pivot, high);
    }
    private static int[] makeSortedArr(Set<Integer> set) {
        int idx = 0;
        int[] arr = new int[set.size()];

        for(int num : set) {
            arr[idx++] = num;
        }

        qSort(arr, 0, arr.length - 1);

        return arr;
    }
    private static HashMap<Integer, Integer> makeHashMap(int[] orderedArr) {
        int length = orderedArr.length;
        HashMap<Integer, Integer> hash = new HashMap<>();

        for(int i = 0; i < length; i++) {
            hash.put(orderedArr[i], i);
        }

        return hash;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        int[] origin = new int[N];
        StringTokenizer stk = new StringTokenizer(br.readLine());
        Set<Integer> set = new HashSet<>();

        for(int i = 0; i < N; i++) {
            origin[i] = Integer.parseInt(stk.nextToken());
            set.add(origin[i]);
        }

        int[] orderedArr = makeSortedArr(set);
        HashMap<Integer, Integer> map = makeHashMap(orderedArr);

        for(int i = 0; i < N; i++) {
            sb.append(map.get(origin[i])).append(" ");
        }

        System.out.println(sb);
    }
}
