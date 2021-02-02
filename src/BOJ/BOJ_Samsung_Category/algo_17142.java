package BOJ.BOJ_Samsung_Category;
import java.io.*;
import java.util.*;

public class algo_17142 {
    private static class SpreadInfo {
        int x;
        int y;
        public SpreadInfo(int nx, int ny) {
            this.x = nx;
            this.y = ny;
        }
    }
    private static class VirusInfo {
        int x;
        int y;
        int t;
        public VirusInfo(int nx, int ny, int Time) {
            this.x = nx;
            this.y = ny;
            this.t = Time;
        }
    }
    static int N, M, VirusCnt;
    static int[][] VirusLoc;
    static char[][] Lab;
    static Stack<SpreadInfo> S = new Stack<>();
    static int ans = Integer.MAX_VALUE;
    static int[] dir_x = {1, 0, -1, 0};
    static int[] dir_y = {0, 1, 0, -1};
    static int BlankSpace = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine()); //Input N, M;
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        Lab = new char[N][N];
        VirusLoc = new int[10][2];

        for(int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                Lab[i][j] = stk.nextToken().charAt(0);
                if(Lab[i][j] == '2') {
                    Lab[i][j] = '*';
                    VirusLoc[VirusCnt][0] = i;
                    VirusLoc[VirusCnt][1] = j;
                    VirusCnt++;
                }
                if(Lab[i][j] != '1')
                    BlankSpace++;
            }
        }
        PutVirus(0, 0);
        if(ans == Integer.MAX_VALUE) ans = -1;
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }

    private static void PutVirus(int start, int cnt) {
        if(cnt == M) {
            BFS();
        } else {
            for(int i = start; i < VirusCnt; i++) {
                Lab[VirusLoc[i][0]][VirusLoc[i][1]] = '0';
                S.push(new SpreadInfo(VirusLoc[i][0], VirusLoc[i][1]));
                PutVirus(i + 1, cnt + 1);
                Lab[VirusLoc[i][0]][VirusLoc[i][1]] = '*';
                S.pop();
            }
        }
    }

    private static void BFS() {
        int maxTime = 0;
        int cnt = 0;
        int[][] Visited = new int[N][N];

        Stack<SpreadInfo> Stack = new Stack<>();
        Stack.addAll(S);
        Queue<VirusInfo> Queue = new LinkedList<>();

        for(int i = 0; i < M; i++) {
            SpreadInfo tmp = Stack.pop();
            cnt++;
            Visited[tmp.x][tmp.y] = -1;
            Queue.offer(new VirusInfo(tmp.x, tmp.y, 0));
        }
        while(!Queue.isEmpty()) {
            VirusInfo tmp = Queue.poll();
            int Time = tmp.t + 1;
            for(int i = 0; i < 4; i++) {
                int nx = tmp.x + dir_x[i];
                int ny = tmp.y + dir_y[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(Lab[nx][ny] != '1' && Visited[nx][ny] == 0) {
                    cnt++;
                    if(Lab[nx][ny] == '*') {
                        Visited[nx][ny] = -1;
                    } else {
                        Visited[nx][ny] = Time;
                        if(maxTime < Time) maxTime = Time;
                    }
                    Queue.offer(new VirusInfo(nx, ny, Time));
                }
            }
        }
        if(maxTime < ans && cnt == BlankSpace) ans = maxTime;
    }
}
