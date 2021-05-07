package BOJ.DFS.Silver;

import java.io.*;
import java.util.*;

public class boj_5568 {
    private static HashSet<Integer> set = new HashSet<>();
    private static int n, k;
    private static String[] arr;
    private static boolean[] visited;
    private static void comb(int cnt, int num) {
        if(cnt == k) {
            set.add(num);
            return;
        }

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                int tmp = num;
                for(int j = 0; j < arr[i].length(); j++) {
                    tmp *= 10;
                }
                tmp += Integer.parseInt(arr[i]);
                visited[i] = true;
                comb(cnt + 1, tmp);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        visited = new boolean[n];
        arr = new String[n];
        for(int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }
        comb(0, 0);

        System.out.println(set.size());
    }
}
