package BOJ.BOJ_StepByStep;
import java.io.*;
import java.util.*;

public class algo_1012 {
    private static class Info {
        int x;
        int y;
        public Info(int nx, int ny) {
            this.x = nx;
            this.y = ny;
        }
    }
    static int[][] Ground;
    static int[] dir_x = {1, 0, -1, 0};
    static int[] dir_y = {0, 1, 0, -1};
    static int T, N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int Case = 0;
        T = Integer.parseInt(br.readLine());

        while(Case < T) {
            int K, ans = 0;

            ArrayList<Info> Loc = new ArrayList<>();
            StringTokenizer stk = new StringTokenizer(br.readLine());
            M = Integer.parseInt(stk.nextToken());
            N = Integer.parseInt(stk.nextToken());
            K = Integer.parseInt(stk.nextToken());
            Ground = new int[N][M];

            for(int i = 0; i < K; i++) {
                stk = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(stk.nextToken());
                int x = Integer.parseInt(stk.nextToken());

                Ground[x][y] = 1;
                Loc.add(new Info(x, y));
            }

            for(int i = 0; i < Loc.size(); i++) {
                int x = Loc.get(i).x;
                int y = Loc.get(i).y;

                if(Ground[x][y] == 1) {
                    ans++;
                    Ground[x][y] = -1;
                    BFS(x, y);
                }
            }

            Case++;
            sb.append(ans).append("\n");
        }
        bw.write(String.valueOf(sb));
        br.close();
        bw.flush();
        bw.close();
        return;
    }
    private static void BFS(int x, int y) {
        Queue<Info> Q = new LinkedList<>();
        Q.offer(new Info(x, y));

        while(!Q.isEmpty()) {
            Info tmp = Q.poll();

            for(int i = 0; i < 4; i++) {
                int nx = tmp.x + dir_x[i];
                int ny = tmp.y + dir_y[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                if(Ground[nx][ny] == 1) {
                    Ground[nx][ny] = -1;
                    Q.offer(new Info(nx, ny));
                }
            }
        }
    }
}
