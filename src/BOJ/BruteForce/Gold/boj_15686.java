package BOJ.BruteForce.Gold;
import java.io.*;
import java.util.*;

public class boj_15686 {
    private static class LocInfo {
        int x;
        int y;
        public LocInfo(int nx, int ny) {
            this.x = nx;
            this.y = ny;
        }
    }
    private static class VisitInfo {
        int x;
        int y;
        int dis;
        public VisitInfo(int nx, int ny, int d) {
            this.x = nx;
            this.y = ny;
            this.dis = d;
        }
    }
    static int[][] Map;
    static boolean[][] Visited;
    static int N, M, ans = Integer.MAX_VALUE;
    static int[] dir_x = {1, 0, -1, 0};
    static int[] dir_y = {0, 1, 0, -1};
    static LinkedList<LocInfo> Chickens = new LinkedList<>();
    static LinkedList<LocInfo> TmpChickens = new LinkedList<>();
    static LinkedList<LocInfo> Houses = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        Map = new int[N][N];
//        Visited = new boolean[N][N];

        for(int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            int val;
            for(int j = 0; j < N; j++) {
                val = Integer.parseInt(stk.nextToken());
                if(val == 2) {
                    Chickens.add(new LocInfo(i, j));
                } else if(val == 1) {
                    Houses.add(new LocInfo(i, j));
                } else;
            }
        }
        BFS(0, 0);
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static void BFS(int start, int depth) {
        if(depth == M) {
            int val = 0;
            Queue<VisitInfo> Q = new LinkedList<>();
            LocInfo cur;
            for(int i = 0; i < TmpChickens.size(); i++) {
                cur = TmpChickens.get(i);
                Map[cur.x][cur.y] = 2;
            }
            for(int i = 0; i < Houses.size(); i++) {
                if(val > ans) break;
                boolean[][] Visited = new boolean[N][N];
                cur = Houses.get(i);
                Q.offer(new VisitInfo(cur.x , cur. y, 0));

                while(!Q.isEmpty()) {
                    VisitInfo inf = Q.poll();
                    Visited[inf.x][inf.y] = true;
                    if(Map[inf.x][inf.y] == 2) {
                        val += inf.dis;
                        Q.clear();
                        break;
                    }
                    for(int j = 0; j < 4; j++) {
                        int nx = inf.x + dir_x[j];
                        int ny = inf.y + dir_y[j];
                        if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                        if(!Visited[nx][ny]) {
                            Visited[nx][ny] = true;
                            Q.offer(new VisitInfo(nx, ny, inf.dis + 1));
                        }
                    }
                }
            }
            if(val < ans) ans = val;
            InitMap();
        } else if(depth < M){
            for(int i = start; i < Chickens.size(); i++) {
                TmpChickens.add(Chickens.get(i));
                BFS(i + 1, depth + 1);
                TmpChickens.removeLast();
            }
        } else return;
    }
    private static void InitVisited() {
        for(int i = 0; i < N; i++) {
            Arrays.fill(Visited[i], false);
        }
    }
    private static void InitMap() {
        for(int i = 0; i < N; i++) {
            Arrays.fill(Map[i], 0);
        }
    }
}
