package BOJ.SegmentTree.Platinum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//세그먼트 트리에 구간합이 아닌 최대값을 넣으면 되는 문제
//구하려는 구간에 대한 인덱싱만 잘해주면됨
//풀이 시간 : 20분

public class boj_1306 {
    private static int init(int[] arr, int[] tree, int node, int start, int end) {
        if(start == end) return tree[node] = arr[start];

        int mid = (start + end) / 2;

        return tree[node] = Math.max(init(arr, tree, node * 2, start, mid), init(arr, tree, node * 2 + 1, mid + 1, end));
    }
    private static int getMax(int[] tree, int node, int left, int right, int start, int end) {
        if(left > end || right < start) return -987654321;

        if(left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;

        return Math.max(getMax(tree, node * 2, left, right, start, mid), getMax(tree, node * 2 + 1, left, right, mid + 1, end));
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken()), m = Integer.parseInt(stk.nextToken());

        int depth = (int)Math.ceil(Math.log(n) / Math.log(2));
        int tree_size = (1 << (depth + 1));
        int[] arr = new int[n + 1];
        int[] tree = new int[tree_size];

        stk = new StringTokenizer(br.readLine());

        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        init(arr, tree, 1, 1, n);

        int width = m - 1;

        for(int i = m; i <= n - m + 1; i++) {
            int left = i - width;
            int right = i + width;

            sb.append(getMax(tree, 1, left, right, 1, n)).append("\n");
        }

        System.out.print(sb);
    }
}
