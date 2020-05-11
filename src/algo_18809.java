import java.io.*;
import java.util.*;

public class algo_18809 {
    static GardenInfo[][] GardenMap;
    static boolean[][] Visited;
    static int N, M;
    static int GreenCnt;
    static int RedCnt;
    static int[] dir_x = {0, 1, 0, -1};
    static int[] dir_y = {1, 0, -1, 0};
    static int ans = Integer.MIN_VALUE;
    static Queue<LiquidInfo> Q = new LinkedList<>();
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
        Visited = new boolean[N][M];
        for(int i = 0; i < N; i++) {//입력받는 부분
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                int tmp = Integer.parseInt(stk.nextToken());
                if(tmp == 0) {
                    GardenMap[i][j] = new GardenInfo(tmp, "", 0);
                } else if(tmp == 1) {
                    GardenMap[i][j] = new GardenInfo(tmp, "", 0);
                } else {
                    GardenMap[i][j] = new GardenInfo(tmp, "", 0);
                }
            }
        }
        if(GardenMap[0][0].Land == 2) {//0, 0이 배양액을 뿌릴 수 있는 칸이면 초록색 배영액을 뿌리고 SpreadGreen 함수 호출
            Q.offer(new LiquidInfo(0, 0));
            GardenMap[0][0] = new GardenInfo(2, "green", 0);
            SpreadGreen(0, 0, 1);
        } else {//그렇지 않을 경우에는 그냥 SpreadGreen 함수 호출
            SpreadGreen(0, 0, 0);
        }
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    public static void SpreadGreen(int x, int y, int depth) {
        if(depth == GreenCnt) {//초록색 배양액을 다 뿌렸을 경우에는 빨간색 배양액 뿌리는 함수 호출
            if(GardenMap[0][0].Land == 2) {//0, 0 땅이 뿌릴 수 있는 땅인데 아무것도 안뿌려져 있는 경우
                if(GardenMap[0][0].Color.equals("")) {
                    Q.offer(new LiquidInfo(0, 0));
                    GardenMap[0][0] = new GardenInfo(2, "red", 0);
                    SpreadRed(0,0, 1);
                } else {
                    SpreadRed(0, 0, 0);
                }
            } else {
                for(int i = 0; i < 4; i++) {
                    int nx = x + dir_x[i];
                    int ny = y + dir_y[i];
                }
            }
        } else {
            GardenInfo tmp = GardenMap[x][y];
        }
        return;
    }
    public static void SpreadRed(int x, int y, int depth) {
        if(depth == RedCnt) {//빨간색 배양액을 다 뿌렸을 경우에는
            BFS();
        } else {
            for(int i = 0; i < 4; i++) {
                int nx = x + dir_x[i];
                int ny = y + dir_y[i];
            }
        }
        return;
    }
    public static void BFS() {
        GardenInfo[][] CopyGarden = new GardenInfo[N][M];
        for(int i = 0; i < N; i++) {
            System.arraycopy(GardenMap[i], 0, CopyGarden[i], 0, M);
        }

        //현재 배양액이 뿌려져있는 gardenMap을 복사하여 배양액 퍼뜨려줌
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
    public LiquidInfo(int nx, int ny) {
        this.x = nx;
        this.y = ny;
    }
}
