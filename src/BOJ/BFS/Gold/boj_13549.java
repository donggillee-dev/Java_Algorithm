package BOJ.BFS.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//Logic
// pq에 들어갈 클래스의 Comparator를 잘못 구현해서 엄청 틀린 문제
// this.time - o.time으로 해야하는데 등호로 구현했다
// 코드 꼼꼼히 확인하자

public class boj_13549 {
    private static final int max = 100000;
    private static class Info implements Comparable<Info> {
        int pos, time;

        @Override
        public int compareTo(Info o) {
            return this.time - o.time;
        }

        public Info(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }
    }

    private static int bfs(int N, int K, int[] dist) {
        PriorityQueue<Info> pq = new PriorityQueue<>();

        dist[N] = 0;
        pq.add(new Info(N, 0));

        while(!pq.isEmpty()) {
            Info inf = pq.poll();

            if(inf.pos == K) break;
            if(inf.time > dist[inf.pos]) continue;

            int curTime = inf.time;
            int nextTime = inf.time + 1;

            int nextPos = inf.pos + 1;
            if(nextPos <= max && dist[nextPos] > nextTime) {
                dist[nextPos] = nextTime;
                pq.add(new Info(nextPos, nextTime));
            }

            nextPos = inf.pos - 1;
            if(nextPos >= 0 && dist[nextPos] > nextTime) {
                dist[nextPos] = nextTime;
                pq.add(new Info(nextPos, nextTime));
            }

            nextPos = inf.pos * 2;
            if(nextPos <= max && dist[nextPos] > curTime) {
                dist[nextPos] = curTime;
                pq.add(new Info(nextPos, curTime));
            }
        }

        return dist[K];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());
        int[] dist = new int[max + 1];

        Arrays.fill(dist, 987654321);

        System.out.println(bfs(N, K, dist));
    }
}
