package Samsung_Algo;
import java.io.*;
import java.util.*;

public class algo_17142 {
    static int N, M, VirusCnt;
    static int[][] VirusLoc;
//    static char[][] Lab;
    static int[][] Lab;
    static Stack<SpreadInfo> S = new Stack<>();
    static int ans = Integer.MAX_VALUE;
    static int[] dir_x = {1, 0, -1, 0};
    static int[] dir_y = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine()); //Input N, M;
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        Lab = new int[N][N];
        VirusLoc = new int[10][2];

        for(int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                Lab[i][j] = Integer.parseInt(stk.nextToken());
                if(Lab[i][j] == 2) {
                    VirusLoc[VirusCnt][0] = i;
                    VirusLoc[VirusCnt][1] = j;
                    VirusCnt++;
                }
            }
        }
        PutVirus(0, 0);
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
                Lab[VirusLoc[i][0]][VirusLoc[i][1]] = -1;
                S.push(new SpreadInfo(VirusLoc[i][0], VirusLoc[i][1]));
                PutVirus(i + 1, cnt + 1);
                Lab[VirusLoc[i][0]][VirusLoc[i][1]] = 2;
                S.pop();
            }
        }
    }

    private static void BFS() {
        int cnt = 0;
//        boolean[][] Visited = new boolean[N][N];
        int[][] CopyLab = new int[N][N];
        Stack<SpreadInfo> Stack = new Stack<>();
        Stack.addAll(S);
        Queue<VirusInfo> Queue = new LinkedList<>();

        for(int i = 0; i < N; i++) {
            System.arraycopy(Lab[i], 0, CopyLab[i], 0, N);
        }
        for(int i = 0; i < M; i++) {
            SpreadInfo tmp = Stack.pop();
//            Visited[tmp.x][tmp.y] = true;
            Queue.offer(new VirusInfo(tmp.x, tmp.y, 0));
        }
        while(!Queue.isEmpty()) {
            VirusInfo tmp = Queue.poll();
//            if(tmp.t > ans) break;
            for(int i = 0; i < 4; i++) {
                int Time = tmp.t + 1;
                int nx = tmp.x + dir_x[i];
                int ny = tmp.y + dir_y[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
//                if(CopyLab[nx][ny] != 1 && !Visited[nx][ny]) {
//                    CopyLab[nx][ny] = Time;
//                    Visited[nx][ny] = true;
//                    Queue.offer(new VirusInfo(nx, ny, Time));
//                    if(cnt < Time) cnt = Time;
//                }
            }
        }
        for(int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(CopyLab[i]));
        }
        System.out.println();
        if(cnt < ans) ans = cnt;
    }
}
class SpreadInfo {
    int x;
    int y;
    public SpreadInfo(int nx, int ny) {
        this.x = nx;
        this.y = ny;
    }
}
class VirusInfo {
    int x;
    int y;
    int t;
    public VirusInfo(int nx, int ny, int Time) {
        this.x = nx;
        this.y = ny;
        this.t = Time;
    }
}