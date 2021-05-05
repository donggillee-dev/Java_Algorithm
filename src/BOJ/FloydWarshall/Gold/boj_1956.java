package BOJ.FloydWarshall.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//Logic
//플루이드를 이용해서 모든 경로의 사이클 포함 최적의 경로값을 구해놓고
//시작지와 도착지 왕복 값을 계산, 그 과정에서 최적의 값을 찾아낼 수 없으면 -1출력
//풀이 시간 : 10분

public class boj_1956 {
    private static final int inf = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(stk.nextToken());
        int e = Integer.parseInt(stk.nextToken());

        int[][] adj = new int[v + 1][v + 1];

        for(int i = 1; i <= v; i++) {
            for(int j = 1; j <= v; j++) {
                adj[i][j] = inf;
            }
        }

        for(int i = 0; i < e; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int c = Integer.parseInt(stk.nextToken());

            adj[a][b] = c;
        }

        for(int k = 1; k <= v; k++) {
            for(int i = 1; i <= v; i++) {
                for(int j = 1; j <= v; j++) {
                    adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
                }
            }
        }

        int ans = inf;
        for(int i = 1; i <= v; i++) {
            for(int j = 1; j <= v; j++) {
                if(i != j) {
                    ans = Math.min(ans, adj[i][j] + adj[j][i]);
                }
            }
        }

        if(ans == inf) System.out.println(-1);
        else System.out.println(ans);
    }
}
