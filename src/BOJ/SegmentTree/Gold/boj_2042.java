package BOJ.SegmentTree.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//Logic
//세그먼트 트리의 정석
//init, update, sum함수 모두를 사용
//잔잔한 실수들만 하지말자
    //init에서 return tree[node]해주는거
    //update에 int node넣어주는거

//풀이 시간 : 25분
public class boj_2042 {
    private static long init(long[] arr, long[] tree, int node, int start, int end) {
        if(start == end) return tree[node] = arr[start];

        int mid = (start + end) / 2;

        return tree[node] = init(arr, tree, node * 2, start, mid) + init(arr, tree, node * 2 + 1, mid + 1, end);
    }
    private static void update(long[] tree, int start, int end, int node, int index, long diff) {
        if(!(start <= index && index <= end)) return;

        tree[node] += diff;

        if(start != end) {
            int mid = (start + end) / 2;
            update(tree, start, mid, node * 2, index, diff);
            update(tree, mid + 1, end, node * 2 + 1, index, diff);
        }
    }
    private static long sum(long[] tree, int start, int end, int node, int left, int right) {
        if(left > end || right < start) return 0;

        if(left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;

        return sum(tree, start, mid, node * 2, left, right) + sum(tree, mid + 1, end, node * 2 + 1, left, right);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stk.nextToken()), m = Integer.parseInt(stk.nextToken()), k = Integer.parseInt(stk.nextToken());

        int depth = (int)Math.ceil(Math.log(n) / Math.log(2));
        int tree_size = (1 << (depth + 1));
        long[] arr = new long[n + 1], tree = new long[tree_size];

        for(int i = 1; i <= n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        init(arr, tree, 1, 1, n);
        for(int i = 0; i < m + k; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            long c = Long.parseLong(stk.nextToken());

            if(a == 1) {
                long diff = c - arr[b];
                arr[b] = c;
                update(tree, 1, n, 1, b, diff);
            } else {
                sb.append(sum(tree, 1, n, 1, b, (int)c)).append("\n");
            }
        }

        System.out.print(sb);
    }
}
