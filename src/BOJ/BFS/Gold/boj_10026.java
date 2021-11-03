package BOJ.BFS.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//Logic
//적록색약의 경우 원본 배열의 R과 B를 같게 만들어준다
//그리고 bfs 탐색하면 해결할 수 있는 문제

public class boj_10026 {
    private static int N;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};

    private static class Info {
        int x, y;
        char ch;

        public Info(int x, int y, char ch) {
            this.x = x;
            this.y = y;
            this.ch = ch;
        }
    }

    private static boolean isRange(int nx, int ny) {
        if(nx < 0 || ny < 0 || nx >= N || ny >= N) return false;

        return true;
    }

    private static int getAns(char[][] arr) {
        int ans = 0;
        boolean[][] visited = new boolean[N][N];
        Queue<Info> q = new LinkedList<>();

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N; col++) {
                if(!visited[row][col]) {
                    ans++;

                    q.add(new Info(row, col, arr[row][col]));
                    visited[row][col] = true;

                    while(!q.isEmpty()) {
                        Info elem = q.poll();

                        for(int dir = 0; dir < 4; dir++) {
                            int nx = elem.x + dx[dir];
                            int ny = elem.y + dy[dir];

                            if(!isRange(nx, ny) || visited[nx][ny]) continue;

                            if(elem.ch == arr[nx][ny]) {
                                visited[nx][ny] = true;
                                q.add(new Info(nx, ny, arr[nx][ny]));
                            }
                        }
                    }
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        char[][] arr1 = new char[N][N];
        char[][] arr2 = new char[N][N];

        for(int row = 0; row < N; row++) {
            String line = br.readLine();
            for(int col = 0; col < N; col++) {
                arr1[row][col] = line.charAt(col);
                arr2[row][col] = arr1[row][col];

                if(arr2[row][col] == 'R') arr2[row][col] = 'G';
            }
        }

        System.out.printf("%d %d\n", getAns(arr1), getAns(arr2));
    }
}
