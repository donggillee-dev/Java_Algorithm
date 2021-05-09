package BOJ.SegmentTree.Platinum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//Logic
//세그먼트 트리에는 구간에서 가장 작은 높이를 가지는 직사각형의 idx를 저잘 => initTree
//getMax함수를 호출하여 정답을 출력
//getMax함수에서는 getMin함수를 호출하여 현재 구간에서 가장 작은 높이를 가진 직사각형의 인덱스를 구한다 => 왜냐하면 그 구간에서의 직사각형의 높이는 가장 낮은 인덱스를 기준으로 되기 때문
//구한 인덱스를 이용해서 구간의 길이와 직사각형을 곱해준다
//그 다음에 getMin으로 뽑아온 최소 높이 직사각형의 인덱스를 기준으로 좌, 우를 탐색한다
//getMax를 재귀로 호출

//풀이 시간 : 90분
public class boj_1725 {
    private static void initTree(int[] arr, int[] tree, int node, int start, int end) {
        if (start == end) {
            tree[node] = start;
            return;
        }

        int mid = (start + end) / 2;

        initTree(arr, tree, node * 2, start, mid);
        initTree(arr, tree, node * 2 + 1, mid + 1, end);

        if (arr[tree[node * 2]] <= arr[tree[node * 2 + 1]]) {
            tree[node] = tree[node * 2];
        } else {
            tree[node] = tree[node * 2 + 1];
        }
    }

    private static int getMin(int[] arr, int[] tree, int start, int end, int left, int right, int node) {
        if (left > end || right < start) return 987654321;

        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;

        int m1 = getMin(arr, tree, start, mid, left, right, node * 2);
        int m2 = getMin(arr, tree, mid + 1, end, left, right, node * 2 + 1);

        if (m1 == 987654321) return m2;

        else if (m2 == 987654321) return m1;

        else {
            if (arr[m1] <= arr[m2]) {
                return m1;
            } else {
                return m2;
            }
        }
    }

    private static long getMax(int n, int[] arr, int[] tree, int start, int end) {
        int min = getMin(arr, tree, 1, n, start, end, 1);

        long ret = (long) arr[min] * (end - start + 1);

        if (start <= min - 1) { //해당 높이 인덱스 기준 왼쪽의 직사각형 탐색
            long temp = getMax(n, arr, tree, start, min - 1);

            if (temp > ret) ret = temp;
        }

        if (min + 1 <= end) { //해당 높이 인덱스 기준 오른쪽 직사각형 탐색
            long temp = getMax(n, arr, tree, min + 1, end);

            if (temp > ret) ret = temp;
        }

        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int depth = (int) Math.ceil(Math.log(n) / Math.log(2));
        int tree_size = (1 << (depth + 1));
        int[] arr = new int[n + 1];
        int[] tree = new int[tree_size];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        initTree(arr, tree, 1, 1, n);
        System.out.print(getMax(n, arr, tree, 1, n));
    }
}
