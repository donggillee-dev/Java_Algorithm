package BOJ.Tree.Silver;

import java.io.*;
import java.util.*;

public class boj_9934 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] tree = new ArrayList[N + 1];
        int[] arr = new int[(int)Math.pow(2, N)];

        for(int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }
        StringTokenizer stk = new StringTokenizer(br.readLine());

        for(int i = 1; i < arr.length; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        recur(1, arr.length - 1, 1, tree, arr);
        for(int i = 1; i <= N; i++) {
            for(int j = 0; j < tree[i].size(); j++) {
                sb.append(tree[i].get(j)).append(" ");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
    private static void recur(int left, int right, int level, ArrayList<Integer>[] tree, int[] arr) {
        int mid = (left + right) / 2;
        if(left >= right) {
            tree[level].add(arr[mid]);
            return;
        }
        tree[level].add(arr[mid]);
        recur(left, mid - 1, level + 1, tree, arr);
        recur(mid + 1, right, level + 1, tree, arr);
    }
}
