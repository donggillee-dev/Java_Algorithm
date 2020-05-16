package Samsung_Algo;
import java.io.*;
import java.util.*;

public class algo_15684 {
    static int N, M, H, Width = 3;
    static int[][] Ladder;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        H = Integer.parseInt(stk.nextToken());
        Ladder = new int[H + 1][(N + 2) * Width];
        if(M == 0) sb.append(0).append("\n");
        else {
            for(int i = 0; i < M; i++) {
                stk = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(stk.nextToken());
                int y1 = Integer.parseInt(stk.nextToken()) * Width;
                int y2 = y1 + 1;
                Ladder[x][y1] = 1;
                Ladder[x][y2] = 1;
            }
        }

//        System.out.println("Original Ladder");
//        for(int i = 0; i < H + 1; i++) {
//            System.out.println(Arrays.toString(Ladder[i]));
//        }
                Build(1, 1, 0);
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static void Build(int x, int y, int BuiltCnt) {
        if(BuiltCnt >= 1 && BuiltCnt <= 3) {
//            System.out.println("========= " + BuiltCnt + " =========");
//            for(int i = 1; i <= H; i++) {
//                System.out.println(Arrays.toString(Ladder[i]));
//            }

            Play(BuiltCnt);
        }

        if(BuiltCnt <= 2) {
            for(int i = x; i <= H; i++) {
                for(int j = y; j < N; j++) {
                    if(CurrentPossible(i, j) && j > 1 && LeftPossible(i, j)) {
                        int idx = j * 3 - 2;
                        Ladder[i][idx] = 1;
                        Ladder[i][idx - 1] = 1;
                        Build(i, j, BuiltCnt + 1);
                        Ladder[i][idx] = 0;
                        Ladder[i][idx - 1] = 0;
                    }
                    else if(CurrentPossible(i, j) && j < N && RightPossible(i, j)) {
                        int idx = j * 3;
                        Ladder[i][idx] = 1;
                        Ladder[i][idx + 1] = 1;
                        Build(i, j, BuiltCnt + 1);
                        Ladder[i][idx] = 0;
                        Ladder[i][idx + 1] = 0;
                    }
                }
            }
        }
    }
    private static void Play(int BuiltCnt) {
        int cnt = 0;
        for(int i = 1; i < N; i++) {
            int x = i * Width - 1;
            int y = 0;
            while(y <= H) {
                if(Ladder[y][x - 1] == 1) {//내려오다가 왼쪽에 사다리가 놓아져있으면
                    x -= 3;
                } else if(Ladder[y][x + 1] == 1) {//내려오다가 오른쪽에 사다리가 놓아져있으면
                    x += 3;
                } else;
                y += 1;
            }
            if(((x / Width) + 1) == i) cnt++;
        }
        if(cnt == N && BuiltCnt < ans) ans = BuiltCnt;
    }
    private static boolean LeftPossible(int x, int y) {
        boolean ret = true;
        int y1 = (y - 1) * 3;
        int y2 = y1 - 1;
        int y3 = y2 - 1;

        if(Ladder[x][y1] == 1 || Ladder[x][y2] == 1 || Ladder[x][y3] == 1) ret = false;
        return ret;
    }
    private static boolean RightPossible(int x, int y) {
        boolean ret = true;
        int y1 = (y + 1) * 3;
        int y2 = y1 - 1;
        int y3 = y2 - 1;

        if(Ladder[x][y1] == 1 || Ladder[x][y2] == 1 || Ladder[x][y3] == 1) ret = false;
        return ret;
    }
    private static boolean CurrentPossible(int x, int y) {
        boolean ret = true;
        int y1 = y * 3;
        int y2 = y1 - 1;
        int y3 = y2 - 1;

        if(Ladder[x][y1] == 1 || Ladder[x][y2] == 1 || Ladder[x][y3] == 1) ret = false;

        return ret;
    }
}
