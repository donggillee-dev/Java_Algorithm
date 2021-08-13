package BOJ.BFS.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//Logic
//시뮬레이션과 bfs를 합친문제
//문제를 제대로 안 읽어서 많이 틀렸다
//한번에 터질수 있는 모든 것들에 대해서는 1연쇄이다
//그다음에 떨어지는 것

//풀이 시간 : 35분

public class boj_11559 {

    private static int[] dir_x = {-1, 0, 1, 0};
    private static int[] dir_y = {0, -1, 0, 1};

    private static class Info {
        int row, col;

        public Info(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static boolean isRange(int row, int col) {
        if(row < 0 || row >= 12 || col < 0 || col >= 6) return false;

        return true;
    }

    private static void pullDown(char[][] arr) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 6; i++) {
            sb.setLength(0);
            for(int j = 11; j >= 0; j--) {
                if(arr[j][i] != '.') {
                    sb.append(arr[j][i]);
                }
                arr[j][i] = '.';
            }

            int length = sb.length();

            for(int idx = 11, j = 0; j < length; j++, idx--) {
                arr[idx][i] = sb.charAt(j);
            }
        }
    }

    private static int bfs(boolean flag, char ch, int row, int col, char[][] arr) {
        int cnt = 1;
        Queue<Info> q = new LinkedList<>();
        boolean[][] visited = new boolean[12][6];

        visited[row][col] = true;
        q.add(new Info(row, col));

        if(flag) {
            arr[row][col] = '.';
        }

        while(!q.isEmpty()) {
            Info elem = q.poll();

            for(int i = 0; i < 4; i++) {
                int nr = elem.row + dir_x[i];
                int nc = elem.col + dir_y[i];

                if(isRange(nr, nc) && !visited[nr][nc] && arr[nr][nc] == ch) {
                    visited[nr][nc] = true;
                    q.add(new Info(nr, nc));
                    cnt++;

                    if(flag) arr[nr][nc] = '.';
                }
            }
        }

        return cnt;
    }

    private static int simulation(char[][] arr) {
        int ans = 0;

        while(true) {
            boolean flag = false;

            for(int i = 0; i < 12; i++) {
                for(int j = 0; j < 6; j++) {

                    if(arr[i][j] == '.') continue;

                    int cnt = bfs(false, arr[i][j], i, j, arr);

                    if(cnt >= 4) {
                        flag = true;
                        bfs(true, arr[i][j], i, j, arr);
                    }
                }
            }
            pullDown(arr);

            if(flag)
                ans++;

            if(!flag) break;
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] arr = new char[12][6];

        for(int i = 0; i < 12; i++) {
            String str = br.readLine();
            for(int j = 0; j < 6; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        System.out.println(simulation(arr));
    }
}
