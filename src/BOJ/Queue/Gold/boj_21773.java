package BOJ.Queue.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//Logic
//우선순위큐를 사용하면 되는 문제
//조건 처리와 우선순위를 조절 잘해주면 된다

public class boj_21773 {
    private static class Info implements Comparable<Info> {
        int A, B, C;

        @Override
        public int compareTo(Info o) {
            if(this.C == o.C) {
                return this.A - o.A;
            }

            return o.C - this.C;
        }

        public Info(int a, int b, int c) {
            A = a;
            B = b;
            C = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(stk.nextToken()), n = Integer.parseInt(stk.nextToken());
        PriorityQueue<Info> pq = new PriorityQueue<>();

        for(int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(stk.nextToken());
            int B = Integer.parseInt(stk.nextToken());
            int C = Integer.parseInt(stk.nextToken());
            pq.add(new Info(A, B, C));
        }

        for(int i = 0; i < T; i++) {
            if(pq.isEmpty()) break;

            Info tmp = pq.poll();

            sb.append(tmp.A).append("\n");

            tmp.C -= 1;
            tmp.B -= 1;

            if(tmp.B == 0) continue;

            pq.add(tmp);
        }

        System.out.print(sb);
    }
}
