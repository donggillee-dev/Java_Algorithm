package BOJ.BOJ_Samsung_Category;
import java.io.*;
import java.util.*;

public class algo_17825 {
    private static class PointInfo {//현재 윺놓이 포인트에 대한 정보 객체
        boolean Blue;//현재 칸이 분기하는 파란색 칸인지 여부
        int move_x1, move_x2;//파란색 칸이 아닐 경우 사용되는 이동용 좌표
        int move_y1, move_y2; //파란색 칸일 경우 사용하는 정보들 파란색 칸이 아닐 경우 -1로 초기화 되어있다
        public PointInfo(boolean B, int x1, int y1, int x2, int y2) {
            this.Blue = B;
            this.move_x1 = x1;
            this.move_x2 = x2;
            this.move_y1 = y1;
            this.move_y2 = y2;
        }
    }
    private static class HorseInfo {
        int x;
        int y;

        public HorseInfo(int x1, int y1) {
            this.x = x1;
            this.y = y1;
        }
    }
    static int[][] Map = new int[11][11];
    static int[][] Visited = new int[11][11];
    static PointInfo[][] Point = new PointInfo[11][11];
    static HorseInfo[] Horse = new HorseInfo[4];
    static int ans = 0;
    static int[] Dice = new int[10];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        Init();
        int i = 0;
        while(stk.hasMoreTokens()) {
            Dice[i] = Integer.parseInt(stk.nextToken());
            i++;
        }
        DFS(0, 0);
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static void DFS(int nth_dice, int sum) {
        if(nth_dice > 9) {
            if(sum > ans) ans = sum;
            return;
        }

        for(int i = 0; i < 4; i++) {
            int before_x = Horse[i].x, after_x = Horse[i].x;
            int before_y = Horse[i].y, after_y = Horse[i].y;
            PointInfo tmp = Point[before_x][before_y];

            if(Point[before_x][before_y].Blue) {
                after_x += tmp.move_x2;
                after_y += tmp.move_y2;
            } else {
                after_x += tmp.move_x1;
                after_y += tmp.move_y1;
            }

            for(int j = 1; j < Dice[nth_dice]; j++) {
                tmp = Point[after_x][after_y];
                after_x += tmp.move_x1;
                after_y += tmp.move_y1;
                if(after_x == 1 && after_y == 5) break;
            }
            if(Visited[after_x][after_y] == 0) {//방문 가능한 지점에 대해서
                Visited[before_x][before_y]--;
                Visited[after_x][after_y]++;//방문한다고 정의하고
                Horse[i].x = after_x;
                Horse[i].y = after_y;
                DFS(nth_dice + 1, sum + Map[after_x][after_y]);
                Visited[after_x][after_y]--;
                Visited[before_x][before_y]++;
                Horse[i].x = before_x;
                Horse[i].y = before_y;
            } else {
                if(after_x == 1 && after_y == 5) {
                    Visited[before_x][before_y]--;
                    Visited[after_x][after_y]++;
                    Horse[i].x = after_x;
                    Horse[i].y = after_y;
                    DFS(nth_dice + 1, sum + Map[after_x][after_y]);
                    Visited[after_x][after_y]--;
                    Visited[before_x][before_y]++;
                    Horse[i].x = before_x;
                    Horse[i].y = before_y;
                }
            }
        }
    }
    private static void Init() {
        for(int i = 0; i < 10; i++) {
            Arrays.fill(Map[i], -1);
        }
        for(int i = 0; i < 4; i++) {
            Horse[i] = new HorseInfo(0, 0);
        }

        Map[0][0] = 0;
        Point[0][0] = new PointInfo(false, 1, 0, -1, -1);

        for(int i = 1; i <= 5; i++) {
            Map[i][0] = i * 2;
            if(i != 5) Point[i][0] = new PointInfo(false, 1, 0, -1, -1);
            else Point[i][0] = new PointInfo(true, 1, 1, 0, 1);
        }

        Map[5][1] = 13;
        Point[5][1] = new PointInfo(false, 0, 1, -1 , -1);

        for(int j = 2; j <= 3; j++) {
            Map[5][j] = Map[5][j - 1] + 3;
            if(j == 3) Point[5][j] = new PointInfo(false, 0, 2, -1, -1);
            else Point[5][j] = new PointInfo(false, 0, 1, -1, -1);

        }

        Map[6][1] = 12;
        Point[6][1] = new PointInfo(false, 1, 1, -1, -1);

        Map[7][2] = 14;
        Point[7][2] = new PointInfo(false, 1, 1, -1, -1);

        Map[8][3] = 16;
        Point[8][3] = new PointInfo(false, 0, 1, -1, -1);

        Map[8][4] = 18;
        Point[8][4] = new PointInfo(false, 0, 1, -1, -1);

        Map[8][5] = 20;
        Point[8][5] = new PointInfo(true, 0, 1, -1, 0);

        for(int i = 7; i >= 6; i--) {
            Map[i][5] = Map[i + 1][5] + 2;
            Point[i][5] = new PointInfo(false, -1, 0, -1, -1);
        }

        Map[5][5] = 25;
        Point[5][5] = new PointInfo(false, -1, 0, -1, -1);

        for(int i = 4; i >= 2; i--) {
            Map[i][5] = Map[i + 1][5] + 5;
            Point[i][5] = new PointInfo(false, -1, 0, -1, -1);
        }

        Map[8][6] = 22;
        Point[8][6] = new PointInfo(false, 0, 1, -1, -1);

        Map[8][7] = 24;
        Point[8][7] = new PointInfo(false, -1, 1, -1, -1);

        Map[7][8] = 26;
        Point[7][8] = new PointInfo(false, -1, 1, -1, -1);

        Map[6][9] = 28;
        Point[6][9] = new PointInfo(false, -1, 1, -1, -1);

        Map[5][10] = 30;
        Point[5][10] = new PointInfo(true, -1, -1, 0, -1);

        Map[5][9] = 28;
        Point[5][9] = new PointInfo(false, 0, -1, -1, -1);

        Map[5][8] = 27;
        Point[5][8] = new PointInfo(false, 0, -1, -1, -1);

        Map[5][7] = 26;
        Point[5][7] = new PointInfo(false, 0, -2, -1, -1);

        Map[4][9] = 32;
        Point[4][9] = new PointInfo(false, -1, -1, -1 ,-1);

        Map[3][8] = 34;
        Point[3][8] = new PointInfo(false, -1, -1, -1, -1);

        Map[2][7] = 36;
        Point[2][7] = new PointInfo(false, 0, -1, -1, -1);

        Map[2][6] = 38;
        Point[2][6] = new PointInfo(false, 0, -1, -1, -1);

        Map[1][5] = 0;
        Point[1][5] = new PointInfo(false, 0, 0, 0, 0);
        return;
    }

}
