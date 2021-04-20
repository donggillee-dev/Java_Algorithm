package BOJ.TwoPointer.Platinum;

import java.io.*;
import java.util.*;
//Logic
//내 생각의 방향
//1. 우선 순위큐와 슬라이딩 윈도우를 사용해서 start포인트에 대해 우선순위큐 peek랑 동일하면 빼주면 되겠지?
    // => 우선 순위큐의 중간 값들 중에 start와 end 인덱스를 벗어난 값들이 존재하게 됨 => 제대로 제거가 안됨
    // 해결 방법 : 우선 순위큐에 넣어주는 값을 객체로 바꿔서 값과 해당 값의 원본 인덱스를 저장, 큐의 가장 첫번째에
                // 존재하는 값이 idx범위를 넘어가면은 while돌려서 빼줌
//2. 이렇게 빼주면서 하니까 시간초과가 남
    // 해결 방법 : 큐의 가장 작은 최소값이 지금 들어오려는 값보다 크면은 큐를 비워줌으로써 시간 복잡도를 줄임
                //위 1에서의 시간 복잡도를 줄일 수 있음 => 아예 큐를 날려버리니까 idx비교해가면서 일일히 뺄 필요 없어짐

//풀이 시간 : 120분
public class boj_11003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int n = Integer.parseInt(stk.nextToken());
        int l = Integer.parseInt(stk.nextToken());
        int[] arr = new int[n];

        stk = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }
        int start = 1 - l, end = 0;

        while(start < 0) {
            pq.add(new int[]{arr[end], end});
            sb.append(pq.peek()[0]).append(" ");
            start++;
            end++;
        }
        pq.offer(new int[]{arr[end], end});

        for(; end < n;) {
            sb.append(pq.peek()[0]).append(" ");
            if(end++ + 1 >= n) break;
            if(!pq.isEmpty() && pq.peek()[0] >= arr[end]) pq.clear();
            pq.add(new int[]{arr[end], end});
            start++;
            while(!pq.isEmpty() && pq.peek()[1] < start) pq.remove();
        }
        System.out.print(sb);
    }
}