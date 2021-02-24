package BOJ.Greedy.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2437 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TreeMap<Integer, Integer> tree = new TreeMap<>();
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer stk = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }
        Arrays.sort(arr);
        perm(arr, 0, 0, 0, N, tree);

    }
    private static void perm(int[] arr, int cnt, int idx, int sum, int N, TreeMap<Integer, Integer> tree) {
        if(cnt > 0) {
            tree.put(sum, 0);
        }
        if(cnt == N) return;

        for(int i = idx; i < N; i++) {
            perm(arr, cnt + 1, i + 1, sum + arr[i], N, tree);
        }
    }
}
