package BOJ.DP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
//Logic
//visited배열은 행 => 화면에 있는 이모티콘 개수, 열 => 클립보드에 있는 이모티콘 개수
//큐에 들어가는 Info에는 현재 화면에 있는 이모티콘 개수, 클립보드에 있는 이모티콘 개수에 대해서 걸린 시간을 저장하였다
//우선순위 큐로 구현

//풀이 시간 : 40분
public class boj_14226 {
    private static class Info implements Comparable<Info>{
        int s, c, t; //화면, 클립보드, 걸린 시간

        public Info(int s, int c, int t) {
            this.s = s;
            this.c = c;
            this.t = t;
        }

        @Override
        public int compareTo(Info o) {
            return this.t - o.t;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int s = Integer.parseInt(br.readLine());
        boolean[][] visited = new boolean[1001][1001];

        PriorityQueue<Info> q = new PriorityQueue<>();
        q.add(new Info(1, 0, 0));
        visited[1][0] = true;

        while(!q.isEmpty()) {
            Info cur = q.poll();

            if(cur.s == s) {
                System.out.println(cur.t);
                break;
            }

            //화면에 있는 이모티콘 모두를 클립보드에 저장
            q.offer(new Info(cur.s, cur.s, cur.t + 1));

            //클립보드에 저장된 이모티콘을 화면에 붙여넣기
            if(cur.c != 0 && cur.s + cur.c <= s && !visited[cur.s + cur.c][cur.c]) {
                visited[cur.s + cur.c][cur.c] = true;
                q.add(new Info(cur.s + cur.c, cur.c, cur.t + 1));
            }

            //화면에 있는 이모티콘 하나 지우기
            if(1 <= cur.s && !visited[cur.s - 1][cur.c]) {
                visited[cur.s - 1][cur.c] = true;
                q.add(new Info(cur.s - 1, cur.c, cur.t + 1));
            }
        }
    }
}
