package BOJ.DP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//DP ver
//특정 i, j번쨰 위치에 0, 1, 2모양의 파이프의 끝이 놓여져 있다는 것
//0 : 가로, 1 : 세로, 2 : 대각
public class boj_17070 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stk;
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        int[][][] dp = new int[n][n][3];

        for(int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        //처음에는 0행, 1열에 가로모양 파이프의 끝이 놓여져있다
        dp[0][1][0] = 1;

        //그러므로 0행 2열부터 파이프를 놓을 수 있기에 반복문 아래와 같이 수행
        for(int i = 0; i < n; i++) {
            for(int j = 2; j < n; j++) {
                //놓으려는 위치에 벽이 있다면 스킵
                if(map[i][j] == 1) continue;
                //i번쨰 행, j번째 열에 가로로 파이프가 놓여지기 위해서는
                //i번쨰 행, j-1번째 열에 가로, 대각으로 파이프가 놓여있을 수 있는 경우 두개를 더해주어야함
                dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2];

                //세로, 대각의 경우 i가 일떄 이전 dp값을 불러올 수 없으므로 스킵
                if(i == 0) continue;
                //i번쨰 행, j번째 열에 세로로 파피프가 놓여지기 위해서는
                //i - 1번쨰 행, j번쨰 열에 세로, 대각으로 파이프가 놓여있을 수 있는 경우 두개를 더해주어야함
                dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2];

                //대각의 경우 놓는 위치 , 놓는 위치의 위, 놓는 위치의 왼쪽이 비어있어야함
                if(map[i - 1][j] == 1 || map[i][j - 1] == 1) continue;
                //대각은 이전 가로, 세로, 대각으로부터 놓아질 수 있으므로 다 더해줌
                dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
            }
        }
        //최종적으로 n-1 행 열에 놓여진 0, 1, 2 모양의 모든 파이프 경우의 수를 더해주면 된다
        System.out.println(dp[n - 1][n - 1][0] + dp[n - 1][n - 1][1] + dp[n - 1][n - 1][2]);
    }
}

//탐색 ver
//public class boj_17070 {
//    private static int answer;
//    private static int n;
//    private static boolean[][] map;
//
//    //deg 0 : 가로
//    //deg 1 : 세로
//    //deg 2 : 대각
//    private static void solve(int row, int col, int deg) {
//        if(row == n - 1 && col == n - 1) {
//            answer++;
//            return;
//        }
//
//        if(deg == 0) {// 가로
//            //가로 이동
//            if(col + 1 < n && !map[row][col + 1]) {
//                solve(row, col + 1, 0);
//            }
//            //대각 이동
//            if(col + 1 < n && row + 1 < n && !map[row][col + 1] && !map[row + 1][col] && !map[row + 1][col + 1]) {
//                solve(row + 1, col + 1, 2);
//            }
//        } else if(deg == 1) {// 세로
//            //세로 이동
//            if(row + 1 < n && !map[row + 1][col]) {
//                solve(row + 1, col, 1);
//            }
//            //대각 이동
//            if(col + 1 < n && row + 1 < n && !map[row][col + 1] && !map[row + 1][col] && !map[row + 1][col + 1]) {
//                solve(row + 1, col + 1, 2);
//            }
//        } else {// 대각
//            //가로 이동
//            if(col + 1 < n && !map[row][col + 1]) {
//                solve(row, col + 1, 0);
//            }
//            //세로 이동
//            if(row + 1 < n && !map[row + 1][col]) {
//                solve(row + 1, col, 1);
//            }
//            //대각 이동
//            if(col + 1 < n && row + 1 < n && !map[row][col + 1] && !map[row + 1][col] && !map[row + 1][col + 1]) {
//                solve(row + 1, col + 1, 2);
//            }
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer stk;
//        n = Integer.parseInt(br.readLine());
//        map = new boolean[n][n];
//
//        for(int i = 0; i < n; i++) {
//            stk = new StringTokenizer(br.readLine());
//            for(int j = 0; j < n; j++) {
//                int num = Integer.parseInt(stk.nextToken());
//
//                if(num == 1) {
//                    map[i][j] = true;
//                }
//            }
//        }
//
//        solve(0, 1, 0);
//
//        System.out.println(answer);
//    }
//}
