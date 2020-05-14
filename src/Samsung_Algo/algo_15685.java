package Samsung_Algo;
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
//            for(int j = 0; j < 10; j++) {
//                for(int k = 0; k < 10; k++) {
//                    if(Map[j][k]) System.out.print("1 ");
//                    else System.out.print("0 ");
//                }
//                System.out.println();
//            }
//            System.out.println();
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
            while(!S.isEmpty()) {
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

//            System.out.println(cnt+"-th generation ==========");
//            System.out.println("Origin Direction");
            while(!Origin.isEmpty()) {
                int dir = Origin.poll();
//                System.out.println(dir);
                nx += dir_x[dir];
                ny += dir_y[dir];
//                System.out.println(nx + " " + ny);
//                if(nx < 0 || ny < 0 || nx >= 101 || ny >= 101) continue;
                Map[ny][nx] = true;
                S.push(dir);
            }//0세대, 1세대
//            System.out.println("Roated Direction");
            while(!Rotated.isEmpty()) {
                int dir = Rotated.poll();
//                System.out.println(dir);
                S.push(dir);
            }//1세대, 2세대
           cnt++;
        }
//        System.out.println(cnt+"-th generation ========");
//        while(!S.isEmpty()) {
//            System.out.println(S.pop());
//        }
    }
}
