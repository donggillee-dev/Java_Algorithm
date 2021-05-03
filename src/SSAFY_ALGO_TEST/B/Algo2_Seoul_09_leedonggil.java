package SSAFY_ALGO_TEST.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Algo2_Seoul_09_leedonggil {
    private static int n, m; //행, 열 정보
    private static int[] dir_r = {-1, 0, 1, 0}; //bfs탐색시에 사용된 방향 배열
    private static int[] dir_c = {0, -1, 0, 1};
    private static boolean isRange(int nr, int nc) { //bfs탐색시에 이동 가능한 위치인지 확인하기 위한 함수
        if(nr < 0 || nr >= m || nc < 0 || nc >= n) return false;
        return true;
    }
    private static int bfs(int[][] map) { //bfs함수
        int ans = 0;
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{0, 0, map[0][0]}); //시작점

        while(!q.isEmpty()) {
            int[] elem = q.poll();
            int row = elem[0];
            int col = elem[1];
            int val = elem[2];
            if(col == n - 1 && row == m - 1) //우측 하단에 도달하면 정답 1 증가
                ans++;
            for(int i = 0; i < 4; i++) { //현 위치에서 4방 탐색
                int nr = row + dir_r[i];
                int nc = col + dir_c[i];

                if(!isRange(nr, nc)) continue; //범위 벗어나면 스킵
                if(map[nr][nc] >= val) continue;
                //돼지가 이동할 수 있는 곳은 내리막길이기에 내리막길인지가 곧 방문여부 조건처리이다
                q.add(new int[]{nr, nc, map[nr][nc]});
            }
        }

        return ans;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;
        int t = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= t; tc++) { //각 TC별로 반복
            stk = new StringTokenizer(br.readLine()); //배열 가로 세로 길이 입력
            m = Integer.parseInt(stk.nextToken());
            n = Integer.parseInt(stk.nextToken());
            int[][] map = new int[m][n];

            for(int i = 0; i < m; i++) { //배열의 높낮이 정보 입력
                stk = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(stk.nextToken());
                }
            }
            //end of input

            int ans = bfs(map); //bfs통한 정답 도출
            sb.append("#" + tc + " ").append(ans).append("\n");
        }
        System.out.print(sb);
    }
}