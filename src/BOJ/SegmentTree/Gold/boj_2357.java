package BOJ.SegmentTree.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//최대, 최소 세그먼트 트리를 각각 만들어서 각각에 대한 쿼리를 던져주면 되는 문제
//풀이 시간 : 10분

public class boj_2357 {

    private static int stio(String str) {
        return Integer.parseInt(str);
    }

    private static long initMax(int[] arr, long[] maxTree, int node, int start, int end) {
        if(start == end) return maxTree[node] = arr[start];

        int mid = (start + end) / 2;

        return maxTree[node] = Math.max(initMax(arr, maxTree, node * 2, start, mid), initMax(arr, maxTree, node * 2 + 1, mid + 1, end));
    }

    private static long initMin(int[] arr, long[] minTree, int node, int start, int end) {
        if(start == end) return minTree[node] = arr[start];

        int mid = (start + end) / 2;

        return minTree[node] = Math.min(initMin(arr, minTree, node * 2, start, mid), initMin(arr, minTree, node * 2 + 1, mid + 1, end));
    }

    private static long getMax(long[] maxTree, int node, int left, int right, int start, int end) {
        if(left > end || right < start) return Long.MIN_VALUE;

        if(left <= start && end <= right) return maxTree[node];

        int mid = (start + end) / 2;

        return Math.max(getMax(maxTree, node * 2, left, right, start, mid), getMax(maxTree, node * 2 + 1, left, right, mid + 1, end));
    }

    private static long getMin(long[] minTree, int node, int left, int right, int start, int end) {
        if(left > end || right < start) return Long.MAX_VALUE;

        if(left <= start && end <= right) return minTree[node];

        int mid = (start + end) / 2;

        return Math.min(getMin(minTree, node * 2, left, right, start, mid), getMin(minTree, node * 2 + 1, left, right, mid + 1, end));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = stio(stk.nextToken());
        int m = stio(stk.nextToken());
        int k = (int)Math.ceil(Math.log(n)/Math.log(2));
        int tree_depth = (1 << (k + 1));
        int[] arr = new int[n + 1];
        long[] maxTree = new long[tree_depth];
        long[] minTree = new long[tree_depth];

        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        initMax(arr, maxTree, 1, 1, n);
        initMin(arr, minTree, 1, 1, n);

        while(m --> 0) {
            stk = new StringTokenizer(br.readLine());
            int a = stio(stk.nextToken());
            int b = stio(stk.nextToken());

            long min = getMin(minTree, 1, a, b, 1, n);
            long max = getMax(maxTree, 1, a, b, 1, n);

            sb.append(min).append(" ").append(max).append("\n");
        }

        System.out.print(sb);
    }
}
