package SWEA.D4;

import java.io.*;
import java.util.*;
//Logic
//BFS를 돌리되 visited와 map의 해당 . 부분 적절히 사용
//visited 배열은 큐(사방에 지뢰가 존제하지 않는 좌표가 들어가는 큐)에서 좌표를 꺼내와서 그 좌표 사방에 대해서 BFS로 탐색할때 가지치기로 사용
//map의 특정 좌표에 O 처리해주는 것은 해당 좌표의 사방이 0 처리되는지 안되는지 여부를 위함

//풀이 시간 : 40분
public class Mine {
    private static final int[] dir_x = {-1, -1, -1, 0, 1, 1, 1, 0};
    private static final int[] dir_y = {-1, 0, 1, 1, 1, 0, -1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            int ans = 0;
            int n = Integer.parseInt(br.readLine());
            char[][] board = new char[n][n];
            boolean[][] visited = new boolean[n][n];
            Queue<int[]> q = new LinkedList<>();

            for(int i = 0; i < n; i++) {
                board[i] = br.readLine().toCharArray();
            }

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(board[i][j] != '.') continue;
                    boolean flag = true;

                    for(int dir = 0; dir < 8; dir++) {
                        int nx = i + dir_x[dir];
                        int ny = j + dir_y[dir];

                        if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;

                        if(board[nx][ny] == '*') {
                            flag = false;
                            break;
                        }
                    }

                    if(flag) {
                        ans++;
                        visited[i][j] = true;
                        q.add(new int[]{i, j});
                        bfs(n, board, visited, q);
                    }
                }
            }

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(board[i][j] == '.') ans++;
                }
            }

            sb.append("#" + tc + " " + ans).append("\n");
        }
        System.out.print(sb);
    }

    private static void bfs(int n, char[][] board, boolean[][] visited, Queue<int[]> q) {
        while(!q.isEmpty()) {
            int[] elem = q.poll();
            int x = elem[0];
            int y = elem[1];
            boolean flag = true;

            for(int dir = 0; dir < 8; dir++) {
                int nx = x + dir_x[dir];
                int ny = y + dir_y[dir];

                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;

                if(board[nx][ny] == '*') {
                    flag = false;
                    break;
                }
            }
            board[x][y] = 'O';
            if(flag) {
                for(int dir = 0; dir < 8; dir++) {
                    int nx = x + dir_x[dir];
                    int ny = y + dir_y[dir];

                    if(nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny] || board[nx][ny] != '.') continue;

                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }
}
