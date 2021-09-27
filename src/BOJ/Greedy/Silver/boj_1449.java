package BOJ.Greedy.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//Logic
//정렬된 위치를 가지고 그때그때 꺼내서 포지션이 이전 테이핑의 범위 내에 들어온다면 갯수 증가 x
//새로운 테이핑 시작 지점이 필요하면 객수 증가
//풀이 시간 : 10분

public class boj_1449 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int n = Integer.parseInt(stk.nextToken()), l = Integer.parseInt(stk.nextToken());
        int ans = 0;
        double start = 0, end = 0;
        stk = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            pq.add(Integer.parseInt(stk.nextToken()));
        }

        while(!pq.isEmpty()) {
            int elem = pq.poll();

            if (!(start <= elem - 0.5 && elem + 0.5 <= end)) {
                ans++;
                start = elem - 0.5;
                end = start + l;
            }
        }

        System.out.println(ans);
    }
}
