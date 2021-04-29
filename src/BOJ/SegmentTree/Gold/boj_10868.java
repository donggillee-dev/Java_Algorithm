package BOJ.SegmentTree.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//Logic
//세그먼트 트리를 이용하되 그 안에 저장되는 값은 구간의 합이 아닌 구간에서 가장 작은 값이다
//init함수를 통해 구간의 최솟값을 구해놓고 getMin함수를 통해 원하는 구간의 최소값을 가져온다
//풀이 시간 : 28분
public class boj_10868 {
    private static int init(int[] arr, int[] tree, int node, int start, int end) {
        if (start == end) return tree[node] = arr[start];

        int mid = (start + end) / 2;
        return tree[node] = Math.min(init(arr, tree, node * 2, start, mid), init(arr, tree, node * 2 + 1, mid + 1, end));
    }

    private static int getMin(int[] tree, int node, int start, int end, int left, int right) {
        if (left <= start && end <= right) return tree[node];

        if (left > end || right < start) return Integer.MAX_VALUE;

        int mid = (start + end) / 2;

        return Math.min(getMin(tree, node * 2, start, mid, left, right), getMin(tree, node * 2 + 1, mid + 1, end, left, right));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken()), m = Integer.parseInt(stk.nextToken());
        int depth = (int) Math.ceil(Math.log(n) / Math.log(2));
        int tree_size = (1 << (depth + 1));

        int[] arr = new int[n + 1], tree = new int[tree_size];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        init(arr, tree, 1, 1, n);

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(stk.nextToken());
            int right = Integer.parseInt(stk.nextToken());
            sb.append(getMin(tree, 1, 1, n, left, right)).append("\n");
        }

        System.out.print(sb);
    }
}
