package BOJ.BOJ_BackTracking;

import java.io.*;
import java.util.*;

public class algo_18809 {
    static SpreadInfo[] SpreadMap = new SpreadInfo[10];
    static GardenInfo[][] GardenMap;

    static int N, M;
    static int GreenCnt;
    static int RedCnt;
    static int LandCnt = 0;
    static int[] dir_x = {0, 1, 0, -1};
    static int[] dir_y = {1, 0, -1, 0};
    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        GreenCnt = Integer.parseInt(stk.nextToken());
        RedCnt = Integer.parseInt(stk.nextToken());
        GardenMap = new GardenInfo[N][M];

        for(int i = 0; i < 10; i++) {
            SpreadMap[i] = new SpreadInfo("",-1, -1);
        }

        for(int i = 0; i < N; i++) {//입력받는 부분
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                int tmp = Integer.parseInt(stk.nextToken());
                if(tmp == 2) {
                    SpreadMap[LandCnt].x = i;
                    SpreadMap[LandCnt].y = j;
                    LandCnt++;
                }
                GardenMap[i][j] = new GardenInfo(tmp, "", 0);
            }
        }
        SpreadGreen(0, 0);
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    public static void SpreadGreen(int depth, int start) {
        if(depth == GreenCnt) {//초록색 배양액을 다 뿌렸을 경우에는 빨간색 배양액 뿌리는 함수 호출
            SpreadRed(0, 0);
        } else {
            for(int i = start; i < LandCnt; i++) {
                if(SpreadMap[i].Color.equals("")) {
                    SpreadInfo tmp = SpreadMap[i];
                    SpreadMap[i].Color = "green";
                    GardenMap[tmp.x][tmp.y].Color = "green";
                    SpreadGreen(depth + 1, i + 1);
                    GardenMap[tmp.x][tmp.y].Color = "";
                    SpreadMap[i].Color = "";
                }
            }
        }
        return;
    }
    public static void SpreadRed(int depth, int start) {
        if(depth == RedCnt) {//빨간색 배양액을 다 뿌렸을 경우에는
            BFS();
        } else {
            for(int i = start; i < LandCnt; i++) {
                if(SpreadMap[i].Color.equals("")) {
                    SpreadInfo tmp = SpreadMap[i];
                    SpreadMap[i].Color = "red";
                    GardenMap[tmp.x][tmp.y].Color = "red";
                    SpreadRed(depth + 1, i + 1);
                    GardenMap[tmp.x][tmp.y].Color = "";
                    SpreadMap[i].Color = "";
                }
            }
        }
        return;
    }
    public static void BFS() {
        int Flower = 0;
        Queue<LiquidInfo> Q = new LinkedList<>();
        GardenInfo[][] CopyGarden = new GardenInfo[N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                CopyGarden[i][j] = new GardenInfo(GardenMap[i][j].Land, GardenMap[i][j].Color, GardenMap[i][j].Time);
            }
        }
        for(int i = 0; i < LandCnt; i++) {
            if(!SpreadMap[i].Color.equals("")) {
                Q.offer(new LiquidInfo(SpreadMap[i].x, SpreadMap[i].y, SpreadMap[i].Color));
            }
        }
        while(!Q.isEmpty()) {
            LiquidInfo tmp = Q.poll();
            if(CopyGarden[tmp.x][tmp.y].Color.equals("Flower")) continue;
            int time = CopyGarden[tmp.x][tmp.y].Time;
            for(int i = 0; i < 4; i++) {
                int nx = tmp.x + dir_x[i];
                int ny = tmp.y + dir_y[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(CopyGarden[nx][ny].Land == 0) continue;
                if(CopyGarden[nx][ny].Color.equals("")) {
                    CopyGarden[nx][ny].Color = tmp.Color;
                    CopyGarden[nx][ny].Time = time + 1;
                    Q.offer(new LiquidInfo(nx, ny, tmp.Color));
                } else {
                    if(!CopyGarden[nx][ny].Color.equals(tmp.Color) && !CopyGarden[nx][ny].Color.equals("Flower")) {
                        if(CopyGarden[nx][ny].Time == time + 1) {
                            CopyGarden[nx][ny].Color = "Flower";
                            Flower++;
                        }
                    }
                }
            }
        }
        if(Flower > ans) {
            ans = Flower;
        }
    }
}
class SpreadInfo {
    String Color;
    int x;
    int y;
    public SpreadInfo(String c, int nx, int ny) {
        this.Color = c;
        this.x = nx;
        this.y = ny;
    }
}
class GardenInfo {
    String Color;//Red, Green, None
    int Land;
    int Time;//배양액 도달 시간

    public GardenInfo(int l, String c, int t) {
        this.Land = l;
        this.Color = c;
        this.Time = t;
    }
}
class LiquidInfo {
    int x;
    int y;
    String Color;
    public LiquidInfo(int nx, int ny, String c) {
        this.x = nx;
        this.y = ny;
        this.Color = c;
    }
}
