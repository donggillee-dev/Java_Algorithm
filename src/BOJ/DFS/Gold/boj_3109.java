package BOJ.DFS.Gold;
import java.io.*;
import java.util.StringTokenizer;

//Logic

//1. 맨 왼쪽 열의 각 행마다 순차적으로 DFS로 맨 오른쪽 열까지 탐색한다.
//2. 각 노드에서 탐색 순서는 오위, 오중, 오아 이 방향으로 진행한다
//3. 진행하는 노드가 맨 마지막 열에 도착하게 되면 true를 리턴해주고 이전 재귀에서는 이 리턴값을 받아 true면 더이상 탐색 안함
//4. 위 1~3의 로직을 수행하면 연결할 수 있는 모든 가스관의 길이가 나온


public class boj_3109 {
    private static int R, C, answer;
    private static char[][] map;
    private static boolean[][] visited;
    private static int[] dir_x = {-1, 0, 1}, dir_y = {1, 1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        R = Integer.parseInt(stk.nextToken());
        C = Integer.parseInt(stk.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];

        for(int i = 0; i < R; i++) {
            sb.append(br.readLine());
            for(int j = 0; j < C; j++) {
                map[i][j] = sb.charAt(j);
            }
            sb.setLength(0);
        }

        for(int i = 0; i < R; i++) {
            visited[i][0] = true;
            DFS(i, 0);
        }
        System.out.println(answer);
    }
    private static boolean DFS(int row, int col) {
        visited[row][col] = true;
        if(col == C - 1) {
            answer++;
            return true;
        }

        for(int i = 0; i < 3; i++) {
            int nr = row + dir_x[i];
            int nc = col + dir_y[i];

            if(nr < 0 || nc < 0 || nr >= R || nc >= C || map[nr][nc] == 'x') continue;

            if(!visited[nr][nc]) {
                if(DFS(nr, nc)) return true;
            }
        }
        return false;
    }
}
