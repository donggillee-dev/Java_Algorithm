package BOJ.BOJ_Samsung_Category;
import java.io.*;
import java.util.*;

public class algo_15685 {
    static int MAX = 101;
    static int ans = 0;
    static boolean[][] Map = new boolean[MAX][MAX];
    static Stack<Integer> S = new Stack<>();
    static int[] dir_y = {0, -1, 0, 1};
    static int[] dir_x = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            int dir = Integer.parseInt(stk.nextToken());
            int gen = Integer.parseInt(stk.nextToken());
            Map[y][x] = true;
            S.push(dir);
            K_Dragon(x, y, gen);
            S.clear();
        }
        for(int i = 0; i < MAX - 1; i++) {
            for(int j = 0; j < MAX - 1; j++) {
                if(Map[i][j] && Map[i + 1][j] && Map[i][j + 1] && Map[i + 1][j + 1]) ans++;
            }
        }
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static void K_Dragon(int x, int y, int gen) {
        int cnt = 0;

        while(cnt <= gen) {
            int nx = x;
            int ny = y;
            Stack<Integer> TmpS = new Stack<>();
            Queue<Integer> Origin = new LinkedList<>();
            Queue<Integer> Rotated = new LinkedList<>();
            while(!S.isEmpty()) {//Rotate된 direction에 대해 Rotate 큐에 넣어줌
                int dir = S.pop();
                TmpS.push(dir);
                dir++;
                if(dir > 3) dir = 0;
                Rotated.offer(dir);
            }
            while(!TmpS.isEmpty()) {
                int tmp = TmpS.pop();
                Origin.offer(tmp);//원본 방향을 Origin큐에 복사
            }
            while(!Origin.isEmpty()) {//원본 방향에 대해서 진행 후 스택에 넣어줌
                int dir = Origin.poll();
                nx += dir_x[dir];
                ny += dir_y[dir];
                Map[ny][nx] = true;
                S.push(dir);
            }
            while(!Rotated.isEmpty()) {//Rotate에 대해서 스택에 넣어줌
                int dir = Rotated.poll();
                S.push(dir);
            }
           cnt++;
        }
    }
}
