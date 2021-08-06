package BOJ.Greedy.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

//Logic
//스케쥴러를 짠다고 생각하고 풀면 될거같은 문제...?
//답보고 한거니까 다시 풀어보자

public class boj_1700 {
    private static int n, k;

    private static int schedule(HashSet<Integer> set, int[] arr, int idx) {
        int ans = 0;

        for (; idx < k; idx++) {
            if (set.contains(arr[idx])) {
                continue;
            }

            HashMap<Integer, Integer> hash = new HashMap<>();
            for (int i = idx; i < k; i++) {
                if (hash.get(arr[i]) == null) {
                    hash.put(arr[i], i);
                }
            }

            boolean flag = false;
            //앞으로 사용될일 없는 제품들은 제거 후 멀티탭에 제품 꽃기
            for (int elem : set) {
                if (hash.get(elem) == null) {
                    set.remove(elem);
                    set.add(arr[idx]);
                    ans++;
                    flag = true;
                    break;
                }
            }

            if (flag) continue;

            int prdIdx = -1, prdNum = 0;

            for (int key : hash.keySet()) {
                if (set.contains(key) && prdIdx < hash.get(key)) {
                    prdIdx = hash.get(key);
                    prdNum = key;
                }
            }

            if (n == 1) set.clear();

            set.remove(prdNum);
            set.add(arr[idx]);
            ans++;
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());

        int idx = 0;
        int[] arr = new int[k];
        HashSet<Integer> set = new HashSet<>();

        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        while (set.size() != n) {
            set.add(arr[idx++]);
        }

        System.out.print(schedule(set, arr, idx));
    }
}
