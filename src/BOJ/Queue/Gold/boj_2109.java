package BOJ.Queue.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//Logic
//우선순위큐 + 그리디 문제
//가장 큰 벌이를 얻기 위해서는 가격순으로 내림차순이 핵심 => 이후 처리에 대해서 많이 헤맴
//풀이 시간 : 40분

public class boj_2109 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){ //가격 내림차순으로 정렬
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[10001];
        for(int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(stk.nextToken());
            int day = Integer.parseInt(stk.nextToken());

            pq.add(new int[]{cost, day});
        }

        int answer = 0;

        while(!pq.isEmpty()) { //가장 높은 가격 강연을 뽑아와서
            int[] elem = pq.poll();
            int day = elem[1];
            int cost = elem[0];

            for(int i = day; i >= 1; i--) { //그 강연 기한에서 가장 가까운 날짜에 강연 배치
                if(arr[i] == 0) {
                    arr[i] = cost;
                    answer += cost;
                    break;
                }
            }
        }
        System.out.println(answer);
    }
}
