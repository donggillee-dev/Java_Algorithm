package BOJ.BFS.Gold;
import java.io.*;
import java.util.*;

public class boj_14502 {
    private static class Info{
        int x;
        int y;
        Info(int pos_x, int pos_y) {
            this.x = pos_x;
            this.y = pos_y;
        }
    }
    static int N, M;
    static int[] arr_x = {1,0,-1,0};
    static int[] arr_y = {0,1,0,-1};
    static int[][] Map;
    static int[][] VirusMap;
    static ArrayList<Info> VirusPos = new ArrayList<Info>();
    static int ans = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        Map = new int[N][M];
        VirusMap = new int[N][M];
        for(int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j= 0; j < M; j++) {
                Map[i][j] = Integer.parseInt(stk.nextToken());
                if(Map[i][j] == 2) {
                    VirusPos.add(new Info(i, j));
                }
            }
        }
        DFS(0, 0);
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static void DFS(int depth, int x) {
        if(depth == 3) {
            int ret = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    VirusMap[i][j] = Map[i][j];
                }
            }
            for(int i = 0; i < VirusPos.size(); i++) {
                Info pos = VirusPos.get(i);
                SpreadVirus(pos.x, pos.y);
            }
            ret = CheckRemain();
            if(ret > ans) ans = ret;
        } else {
            for(int i = x; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(Map[i][j] == 0) {
                        Map[i][j] = 1;
                        DFS(depth + 1, i);
                        Map[i][j] = 0;
                    }
                }
            }
        }
    }
    private static void SpreadVirus(int x, int y) {
        for(int i = 0; i < 4; i++) {
            int tmp_x = x + arr_x[i];
            int tmp_y = y + arr_y[i];
            if(tmp_x >= 0 && tmp_y >= 0 && tmp_x < N && tmp_y < M && VirusMap[tmp_x][tmp_y] == 0) {
                VirusMap[tmp_x][tmp_y] = 2;
                SpreadVirus(tmp_x, tmp_y);
            }
        }
    }
    private static int CheckRemain() {
        int result = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(VirusMap[i][j] == 0) {
                    result++;
                }
            }
        }
        return result;
    }
}

