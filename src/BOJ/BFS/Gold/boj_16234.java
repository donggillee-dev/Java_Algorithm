package BOJ.BFS.Gold;
import java.io.*;
import java.util.*;
public class boj_16234 {
    static private class Info {
        int x, y;
        public Info(int nx, int ny) {
            this.x = nx;
            this.y = ny;
        }
    }
    static int N, L, R, ans = 0;
    static boolean[][] Map;
    static int[][] A;
    static int[] dir_x = {0, 0, -1, 1};
    static int[] dir_y = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        L = Integer.parseInt(stk.nextToken());
        R = Integer.parseInt(stk.nextToken());
        Map = new boolean[N][N];
        A = new int[N][N];
        for(int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        solve();
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static void solve() {
        int tot = 0;
        while(true) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(!Map[i][j]) {
                        Map[i][j] = true;
                        BFS(i, j);
                        tot++;
                    }
                }
            }
            if(tot == N * N) {
                break;
            }
            tot = 0;
            ans++;
            Init();
        }

    }
    private static void BFS(int x, int y) {
        Queue<Info> Q = new LinkedList<>();
        Queue<Info> Union = new LinkedList<>();
        Union.offer(new Info(x, y));
        Q.offer(new Info(x, y));
        int cnt = 1;
        int val = A[x][y];
        while(!Q.isEmpty()) {
            Info tmp = Q.poll();
            int popular = A[tmp.x][tmp.y];
            for(int i = 0; i < 4; i++) {
                int nx = tmp.x + dir_x[i];
                int ny = tmp.y + dir_y[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                int abs = Math.abs(popular - A[nx][ny]);
                if(!Map[nx][ny] && (abs <= R) && (abs >= L)) {
                    Map[nx][ny] = true;
                    Q.offer(new Info(nx, ny));
                    Union.offer(new Info(nx, ny));
                    val += A[nx][ny];
                    cnt++;
                }
            }
        }
        if(cnt != 1) {
            int div = val / cnt;
            Move(Union, div);
        }
    }
    private static void Move(Queue<Info> Union, int val) {
        while(!Union.isEmpty()) {
            Info tmp = Union.poll();
            A[tmp.x][tmp.y] = val;
        }
    }
    private static void Init() {
        for(int i = 0; i < N; i++) {
            Arrays.fill(Map[i], false);
        }
    }
}
