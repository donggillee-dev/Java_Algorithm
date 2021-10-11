package BOJ.DFS.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

//Logic
//DFS로 문자열 탐색을 풀면되는 문제이다
//시간 초과가 난 이유는 신의 문자열은 중복될 수 있기때문
//모든 배열이 a로 가득차있고 aaaaa로 K개만큼 탐색하게되면 시간초과가 발생할 수 있다
//이를 위해서 HashMap을 이용해 중복되는 신의 문자열 경우에는 HashMap에서 꺼내서 탐색 안하고 바로 처리

public class boj_20166 {
    private static int N, M, K, ans = 0;
    private static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
    private static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};

    private static void dfs(int row, int col, int depth, String word, char[][] map) {
        if(depth == word.length()) {
            ans++;
            return;
        }

        for(int dir = 0; dir < 8; dir++) {
            int nr = row + dr[dir];
            int nc = col + dc[dir];

            if(nr < 0) {
                nr = N - 1;
            } else if(nr >= N) {
                nr = 0;
            }

            if(nc < 0) {
                nc = M - 1;
            } else if(nc >= M) {
                nc = 0;
            }

            if(word.charAt(depth) == map[nr][nc]) {
                dfs(nr, nc, depth + 1, word, map);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        HashMap<String, Integer> ansHash = new HashMap<>();
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(stk.nextToken());

        char[][] map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < K; i++) {
            ans = 0;
            String word = br.readLine();

            if(ansHash.get(word) != null) {
                sb.append(ansHash.get(word)).append("\n");
                continue;
            }

            for(int row = 0; row < N; row++) {
                for(int col = 0; col < M; col++) {
                    if(map[row][col] == word.charAt(0)) {
                        dfs(row, col, 1, word, map);
                    }
                }
            }
            sb.append(ans).append("\n");
            ansHash.put(word, ans);
        }

        System.out.print(sb);
    }
}
