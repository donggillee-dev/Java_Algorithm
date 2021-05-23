package BOJ.Sort.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
//Logic
//정렬해서 선의 길이 겹치는거에 대해서 계산해서 더해주면 되는 문제
//오래걸린 이유 : 정렬 우선순위 실수
//풀이 시간 : 40분
public class boj_2170 {
    private static class Info implements Comparable<Info> {
        long start, end;

        public Info(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Info o) {
            if(this.start > o.start) return 1;
            else if(this.start == o.start) {
                if(this.end > o.end) return 1;
            }

            return -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Info> pq = new PriorityQueue<>();
        StringTokenizer stk;

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            pq.add(new Info(Long.parseLong(stk.nextToken()), Long.parseLong(stk.nextToken())));
        }


        long point_start = pq.peek().start;
        long point_end = pq.peek().end;
        long answer = point_end - point_start;

        pq.poll();

        while (!pq.isEmpty()) {
            Info tmp = pq.poll();

            if ((point_start <= tmp.start) && (tmp.end <= point_end)) continue;

            if (tmp.start < point_end) {
                answer += tmp.end - point_end;
            } else {
                answer += tmp.end - tmp.start;
            }
            point_start = tmp.start;
            point_end = tmp.end;
        }

        System.out.println(answer);
    }
}
