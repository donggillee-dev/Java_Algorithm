package BOJ.Hash.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

//Logic
//수학적으로 접근해서 풀 수 있는 문제

public class boj_9375 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while(T --> 0) {
            long ans = 1;
            int n = Integer.parseInt(br.readLine());
            HashMap<String, Integer> hash = new HashMap<>();

            for(int i = 0; i < n; i++) {
                String[] strArr = br.readLine().split(" ");

                hash.put(strArr[1], hash.getOrDefault(strArr[1], 0) + 1);
            }

            int size = hash.size(), idx = 0;
            int[] arr = new int[size];

            for(int num : hash.values()) {
                arr[idx++] = num;
            }

            for(int i = 0; i < size; i++) {
                ans *= (arr[i] + 1);
            }
            sb.append(ans - 1).append("\n");
        }

        System.out.print(sb);
    }
}
