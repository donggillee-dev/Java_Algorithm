package BOJ.SegmentTree.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//최대, 최소 세그먼트 트리를 각각 만들어서 각각에 대한 쿼리를 던져주면 되는 문제
//풀이 시간 : 10분

public class boj_2357 {
    private static long initMax(int[] arr, long[] max_tree, int node, int start, int end) {
        if(start == end) return max_tree[node] = arr[start];

        int mid = (start + end) / 2;

        return max_tree[node] = Math.max(initMax(arr, max_tree, node * 2, start, mid), initMax(arr, max_tree, node * 2 + 1, mid + 1, end));
    }
    private static long initMin(int[] arr, long[] min_tree, int node, int start, int end) {
        if(start == end) return min_tree[node] = arr[start];

        int mid = (start + end) / 2;

        return min_tree[node] = Math.min(initMin(arr, min_tree, node * 2, start, mid), initMin(arr, min_tree, node * 2 + 1, mid + 1, end));
    }
    private static long getMax(long[] max_tree, int node, int left, int right, int start, int end) {
        if(left > end || right < start) return 0;

        if(left <= start && end <= right) return max_tree[node];

        int mid = (start + end) / 2;

        return Math.max(getMax(max_tree, node * 2, left, right, start, mid), getMax(max_tree, node * 2 + 1, left, right, mid + 1, end));
    }
    private static long getMin(long[] min_tree, int node, int left, int right, int start, int end) {
        if(left > end || right < start) return Long.MAX_VALUE;

        if(left <= start && end <= right) return min_tree[node];

        int mid = (start + end) / 2;

        return Math.min(getMin(min_tree, node * 2, left, right, start, mid), getMin(min_tree, node * 2 + 1, left, right, mid + 1, end));
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        int depth = (int)Math.ceil(Math.log(n)/Math.log(2));
        int tree_depth = (1 << (depth + 1));
        int[] arr = new int[n + 1];
        long[] max_tree = new long[tree_depth];
        long[] min_tree = new long[tree_depth];

        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        initMax(arr, max_tree, 1, 1, n);
        initMin(arr, min_tree, 1, 1, n);

        while(m --> 0) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());

            long min = getMin(min_tree, 1, a, b, 1, n);
            long max = getMax(max_tree, 1, a, b, 1, n);

            sb.append(min).append(" ").append(max).append("\n");
        }
        System.out.print(sb);
    }
}
