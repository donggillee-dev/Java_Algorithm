package BOJ.Sort.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//Logic
//그냥 정렬하면 된다

public class boj_11651 {
    private static class Pos implements Comparable<Pos> {
        int x, y;

        @Override
        public int compareTo(Pos o) {
            if(this.y == o.y) {
                return x - o.x;
            }

            return this.y - o.y;
        }

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        StringTokenizer stk;
        int N = Integer.parseInt(br.readLine());

        while(N --> 0) {
            stk = new StringTokenizer(br.readLine());
            pq.add(new Pos(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken())));
        }

        while(!pq.isEmpty()) {
            sb.append(pq.peek().x + " " + pq.peek().y).append("\n");
            pq.poll();
        }

        System.out.print(sb);
    }
}
