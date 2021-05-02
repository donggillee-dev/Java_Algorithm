package BOJ.SegmentTree.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//구간의 곱을 세그먼트 트리로 구현
//구간의 곱에 대한 쿼리를 수행할때 구간 외에 대해서 1을 리턴해주는게 관건
//풀이 시간 : 15분

public class boj_11505 {
    private static final int mod = 1000000007;

    private static long initTree(int[] arr, long[] tree, int node, int start, int end) {
        if (start == end) return tree[node] = arr[start] % mod;

        int mid = (start + end) / 2;

        return tree[node] = (initTree(arr, tree, node * 2, start, mid) * initTree(arr, tree, node * 2 + 1, mid + 1, end)) % mod;
    }

    private static void update(int[] arr, long[] tree, int node, int index, int start, int end) {
        if (!(start <= index && index <= end)) return;

        if (start == end) {
            tree[node] = arr[index];
            return;
        }

        int mid = (start + end) / 2;

        update(arr, tree, node * 2, index, start, mid);
        update(arr, tree, node * 2 + 1, index, mid + 1, end);

        tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % mod;
    }

    private static long getMul(long[] tree, int start, int end, int node, int left, int right) {
        if (left > end || right < start) return 1;

        if (left <= start && end <= right) return tree[node] % mod;

        int mid = (start + end) / 2;

        return (getMul(tree, start, mid, node * 2, left, right) * getMul(tree, mid + 1, end, node * 2 + 1, left, right)) % mod;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        int k = Integer.parseInt(stk.nextToken());
        int mk = m + k;

        int depth = (int) Math.ceil(Math.log(n) / Math.log(2));
        int tree_size = (1 << (depth + 1));
        int[] arr = new int[n + 1];
        long[] tree = new long[tree_size];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        initTree(arr, tree, 1, 1, n);

        while (mk-- > 0) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int c = Integer.parseInt(stk.nextToken());

            if (a == 1) {
                arr[b] = c;
                update(arr, tree, 1, b, 1, n);
            } else {
                sb.append(getMul(tree, 1, n, 1, b, c)).append("\n");
            }
        }

        System.out.print(sb);
    }
}
