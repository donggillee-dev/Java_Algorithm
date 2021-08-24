package BOJ.BFS.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//Logic
//내가 낸 문제지만 정말...똥같다
//가장 오래 헷갈렸던 부분은 얼음을 녹일때이다
//탐색하면서 그때그때 녹이면 안되고 조건을 충족하는 칸을 하나의 자료구조에 다 넣어서 거기서 처리해버려야한다

//풀이 시간 : 1시간

public class boj_20058 {
    private static int n, q, size, sum, cnt;
    private static int[] dir_r = {-1, 0, 1, 0};
    private static int[] dir_c = {0, -1, 0, 1};
    private static boolean[][] visited;

    private static class Info {
        int row, col;

        public Info(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static int stoi(String str) {
        return Integer.parseInt(str);
    }

    private static void initVisit() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                visited[i][j] = false;
            }
        }
    }

    private static void rotate(int[][] arr, int l, int sr, int sc, int er, int ec) {
        int[][] cpy = new int[l][l];

        for (int i = sc, idxr = 0; i < ec; i++, idxr++) {
            for (int j = er - 1, idxc = 0; j >= sr; j--, idxc++) {
                cpy[idxr][idxc] = arr[j][i];
            }
        }

        for (int i = 0, idxr = sr; i < l; i++, idxr++) {
            for (int j = 0, idxc = sc; j < l; j++, idxc++) {
                arr[idxr][idxc] = cpy[i][j];
            }
        }
    }

    private static void simulation(int l, int[][] arr) {
        Queue<Info> q = new LinkedList<>();

        for (int i = 0; i < size; i += l) {
            for (int j = 0; j < size; j += l) {
                rotate(arr, l, i, j, i + l, j + l);
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int cnt = 0;
                if (arr[i][j] == 0) continue;
                for (int k = 0; k < 4; k++) {
                    int nr = i + dir_r[k];
                    int nc = j + dir_c[k];

                    if (nr < 0 || nc < 0 || nr >= size || nc >= size) {
                        cnt++;
                        continue;
                    }

                    if (arr[nr][nc] == 0) {
                        cnt++;
                    }
                }

                if (cnt > 1) {
                    q.add(new Info(i, j));
                }
            }
        }

        while (!q.isEmpty()) {
            Info inf = q.poll();

            if (arr[inf.row][inf.col] == 1) arr[inf.row][inf.col] = 0;
            else arr[inf.row][inf.col]--;
        }
    }

    private static void bfs(int[][] arr) {

        initVisit();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sum += arr[i][j];
                int mass = 0;
                if (!visited[i][j] && arr[i][j] != 0) {
                    visited[i][j] = true;
                    Queue<Info> q = new LinkedList<>();
                    q.add(new Info(i, j));

                    while (!q.isEmpty()) {
                        Info elem = q.poll();

                        mass++;

                        for (int dir = 0; dir < 4; dir++) {
                            int nr = elem.row + dir_r[dir];
                            int nc = elem.col + dir_c[dir];

                            if (nr < 0 || nc < 0 || nr >= size || nc >= size || visited[nr][nc] || arr[nr][nc] == 0)
                                continue;

                            visited[nr][nc] = true;
                            q.add(new Info(nr, nc));
                        }
                    }
                }
                cnt = Math.max(cnt, mass);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        n = stoi(stk.nextToken());
        q = stoi(stk.nextToken());
        size = (int) Math.pow(2, n);
        visited = new boolean[size][size];
        int[][] arr = new int[size][size];

        for (int i = 0; i < size; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                arr[i][j] = stoi(stk.nextToken());
            }
        }

        stk = new StringTokenizer(br.readLine());

        for (int i = 0; i < q; i++) {
            int l = (int) Math.pow(2, stoi(stk.nextToken()));
            simulation(l, arr);
        }

        bfs(arr);

        System.out.println(sum);
        System.out.println(cnt);
    }
}
