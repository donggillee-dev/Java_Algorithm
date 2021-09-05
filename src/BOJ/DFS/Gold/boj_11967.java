package BOJ.DFS.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//Logic
//잘못생각해서 계속 틀렸던 문제
//스위치를 킴과 동시에 그곳으로 이동할 수 있는 것이 아니라
//움직이는 것과 불이 켜져있는 곳과 이미 방문했던곳을 다 따로 생각해줘야함

public class boj_11967 {
    private static int n;
    private static int ans = 1;
    private static ArrayList<Info>[][] map;
    private static boolean[][] isOn;
    private static boolean[][] visited;
    private static boolean[][] canMove;
    private static int[] dir_r = {-1, 0, 1, 0};
    private static int[] dir_c = {0, -1, 0, 1};

    private static class Info {
        int row, col;

        public Info(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static void bfs() {
        Queue<Info> q = new LinkedList<>();
        q.add(new Info(1, 1));
        visited[1][1] = isOn[1][1] = true;

        while (!q.isEmpty()) {
            Info inf = q.poll();

            for (int i = 0; i < 4; i++) { //현재위치에서 상하좌우로 움직일 수 있다
                int nr = inf.row + dir_r[i];
                int nc = inf.col + dir_c[i];

                if (nr < 1 || nc < 1 || nr > n || nc > n) continue;
                canMove[nr][nc] = true;
            }

            for (Info elem : map[inf.row][inf.col]) { //현재위치에서 킬 수 있는 스위치는 다 킨다
                if (!isOn[elem.row][elem.col]) { //해당 위치가 켜져있지 않다면 킨다
                    ans++;
                    isOn[elem.row][elem.col] = true;
                }

                if (canMove[elem.row][elem.col] && !visited[elem.row][elem.col]) { //그 위치가 불이 켜져있고 움직일 수 있는 위치이며 방문한적이 없다면 큐에 넣어줌
                    visited[elem.row][elem.col] = true;
                    q.add(new Info(elem.row, elem.col));
                }
            }

            for (int i = 0; i < 4; i++) { //위의 조건들을 다 처리하고 나서도 현재 위치에서 4방탐색하면 업데이트 된 것들이 존재할 수 있기에 처리
                int nr = inf.row + dir_r[i];
                int nc = inf.col + dir_c[i];

                if (nr < 1 || nc < 1 || nr > n || nc > n) continue;

                if (visited[nr][nc] || !canMove[nr][nc] || !isOn[nr][nc]) continue;
                visited[nr][nc] = true;
                q.add(new Info(nr, nc));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        isOn = new boolean[n + 1][n + 1];
        visited = new boolean[n + 1][n + 1];
        canMove = new boolean[n + 1][n + 1];
        map = new ArrayList[n + 1][n + 1];


        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int srow = Integer.parseInt(stk.nextToken());
            int scol = Integer.parseInt(stk.nextToken());
            int erow = Integer.parseInt(stk.nextToken());
            int ecol = Integer.parseInt(stk.nextToken());

            map[srow][scol].add(new Info(erow, ecol));
        }

        bfs();

        System.out.println(ans);
    }
}