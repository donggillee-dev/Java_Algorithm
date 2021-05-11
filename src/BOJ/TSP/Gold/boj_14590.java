package BOJ.TSP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//Logic
//TSP알고리즘을 이용해서 해결하면 되는 문제
//다만 가장 긴 수열을 찾아야하기 max를 이용해서 가장 긴 길이를 탐색
//여기서 중요한건 TSP처럼 원래 위치로 돌아올 필요가 없기때문에 갈 수 있는 가장 긴 곳까지 가야함
//그렇기에 모든 노드를 다 돌았는지 체크할 필요가 없다
//두번쨰 중요한건 tracking 함수
//TSP 함수와 비슷한 로직으로 돌지만 dp배열의 dp[visited][cur] 값이 visited | (1 << i) 즉 i번째를 방문한 것 + 1의 값인지를 확인하여 역트레킹

//풀이 시간 :50분
public class boj_14590 {
    private static int n;
    private static int[][] map, dp;
    private static StringBuilder sb = new StringBuilder();
    private static int tsp(int visited, int cur) {
        if(dp[visited][cur] != 0) return dp[visited][cur];

        for(int i = 0; i < n; i++) {
            if((visited & (1 << i)) != 0 || map[cur][i] == 0) continue;
            dp[visited][cur] = Math.max(dp[visited][cur], tsp(visited | (1 << i), i) + 1);
        }

        return dp[visited][cur];
    }
    private static void tracking(int visited, int cur) {
        for(int i = 0; i < n; i++) {
            if((visited & (1 << i)) != 0 || map[cur][i] == 0) continue;
            if(dp[visited][cur] == dp[visited | (1 << i)][i] + 1) {
                sb.append((i + 1)).append(" ");
                tracking((visited | (1 << i)), i);
                break;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        dp = new int[(1 << n)][n];

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        System.out.println(tsp(1, 0) + 1);
        sb.append(1).append(" ");
        tracking(1, 0);
        System.out.print(sb);
    }
}
