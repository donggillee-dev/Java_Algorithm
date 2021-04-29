package BOJ.SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//Logic
//세그먼트 트리의 기본 구조 및 함수

public class segmentTree {
    //파라미터
    //int[] arr : 원본 값이 저장되어있는 배열
    //int[] tree : 세그먼트 트리의 배열화
    //int node : 세그먼트 트리의 노드에 구간 합 값을 넣어주기 위한 노드 인덱스 번호
    //int start : 구간합을 구할 시작 인덱스
    //int end : 구간합을 구할 끝 인덱스
    private static int init(int[] arr, int[] tree, int node, int start, int end) {
        if(start == end) { //리프 노드에 도달한 경우
            return tree[node] = arr[start]; //해당 위치에 arr의 값을 넣어주고 리턴
        }

        //그 외의 경우에는 트리의 범위에 대해서 왼쪽 오른쪽으로 분기하기 위해 mid 구한다
        int mid = (start + end) / 2;

        //init(0 ~ 12)의 경우 init(0 ~ 6) + init(7 ~ 12)로 분기 => 최종적으로 루트 노드에는 0 ~ 12의 구간합이 저장되게 되어있음
        return init(arr, tree, node * 2, start, mid) + init(arr, tree, node * 2 + 1, mid + 1, end);
    }

    //파라미터
    //int[] tree : 세그먼트 트리의 배열화
    //int node : 세그먼트 트리의 노드에 구간 합 갱신 값을 넣어주기 위한 노드 인덱스 번호
    //int start : 갱신을 할 구간의 시작 인덱스
    //int end : 갱신을 할 구간의 끝 인덱스
    //int index : 갱신을 할 index 노드번호
    //int diff : 변경될 값 차이
    private static void update(int[] tree, int node, int start, int end, int index, int diff) {
        //변경할 노드번호가 범위 인덱스에 존재하지 않는다면 갱신할 필요가 없는 구간이므로 리턴
        if(!(start <= index && index <= end)) return;

        //해당 구간합 노드에 diff만큼 더해줌
        tree[node] += diff;

        //start == end의 경우에는 리프 노드에 도달했다는 경우이므로 더이상 재귀를 돌 필요가 없음
        //그런 경우가 아니므로 현재 노드의 좌, 우로 분기해서 재귀 돈다
        if(start != end) {
            int mid = (start + end) / 2;
            update(tree, node * 2, start, mid, index, diff);
            update(tree, node * 2 + 1, mid + 1, end, index, diff);
        }
    }

    //파라미터
    //int[] tree : 세그먼트 트리
    //int node : 세그먼트 트리의 구간합 노드에 접근하기 위한 인덱스
    //int start, end : 세그먼트 트리의 구간
    //int left, right : 구간합을 구할 구간
    private static int sum(int[] tree, int node, int start, int end, int left, int right) {
        //현재 트리의 범위와 구간합을 구하고자 하는 범위가 아예 겹치지 않는 경우 0을 리턴
        if(left > end && right < start) return 0;

        //구간합을 구하고자 하는 범위가 현재 트리의 범위를 덮는 경우 더이상 분기하지 않고 현재 node번호, 즉 구간합을 리턴해버린다
        if(left <= start && right >= end) return tree[node];

        //그 외의 경우에는 좌, 우로 분기해서 구간합을 구한다
        int mid = (start + end) / 2;

        return sum(tree, node * 2, start, mid, left, right) + sum(tree, node * 2 + 1, mid + 1, end, left, right);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int depth = (int)Math.ceil(Math.log(n) / Math.log(2));
        int tree_size = (1 << (depth + 1));

        int[] arr = new int[n];
        int[] tree = new int[tree_size];

        StringTokenizer stk = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        init(arr, tree, 0, 0, tree_size);
    }
}
