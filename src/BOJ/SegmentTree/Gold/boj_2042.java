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

    private static long stol(String str) {
        return Long.parseLong(str);
    }

    private static long init(long[] arr, long[] tree, int start, int end, int node) {
        if(start == end) return tree[node] = arr[start];

        int mid = (start + end) / 2;

        return tree[node] = init(arr, tree, start, mid, node * 2) + init(arr, tree, mid + 1, end, node * 2 + 1);
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

    private static long getSum(long[] tree, int start, int end, int left, int right, int node) {
        if(start > right || left > end) return 0;

        if(left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;

        return getSum(tree, start, mid, left, right, node * 2) + getSum(tree, mid + 1, end, left, right, node * 2 + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = (int)stol(stk.nextToken());
        int m = (int)stol(stk.nextToken());
        int k = (int)stol(stk.nextToken());
        int depth = (int)Math.ceil(Math.log(n) / Math.log(2));
        int treeSize = (1 << (depth + 1));
        long[] arr = new long[n + 1];
        long[] tree = new long[treeSize];

        for(int i = 1; i <= n; i++) {
            arr[i] = stol(br.readLine());
        }

        init(arr, tree, 1, n, 1);

        for(int i = 0; i < m + k; i++) {
            stk = new StringTokenizer(br.readLine());
            boolean isUpdate = stol(stk.nextToken()) == 1;

            if(isUpdate) {
                int index = (int)stol(stk.nextToken());
                long updateNum = stol(stk.nextToken());
                long diff = updateNum - arr[index];

                arr[index] = updateNum;

                update(tree, 1, n, 1, index, diff);
            } else {
                int left = (int)stol(stk.nextToken());
                int right = (int)stol(stk.nextToken());

                sb.append(getSum(tree, 1, n, left, right, 1)).append("\n");
            }
        }

        System.out.print(sb);
    }
}
