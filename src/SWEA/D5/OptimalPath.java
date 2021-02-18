package SWEA.D5;

import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

//Logic
//1. 시작점과 끝점은 고정된 상태 ( 시작점은 0, 끝점은 N + 1로 설정)
//2. 각 노드에서 다른 모든 지점까지의 거리를 구해놓는다
//3. DP배열 선언 ( row => 이때까지 방문 모든 노드들을 비트 연산으로 표현, col => 이때까지의 경로에서 다음에 탐색할 노드 ) => 즉 DP[visited][node]는 이때까지 visited한 곳에서 node로 갔을시에 최소의 거리이다
//4. 재귀를 돌면서 DP배열을 갱신해나간다. 재귀문 내에서 탐색하려는 노드가 끝점 즉 N + 1인 경우에는 해당 노드에서 끝점까지의 거리를 그냥 return해줌

public class OptimalPath {
    private static class posInfo {
        int row, col;
        public posInfo(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    private static int N;
    private static posInfo[] loc;
    private static int[][] dist, dp;
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;

        int TestCase = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= TestCase; tc++) {
            N = Integer.parseInt(br.readLine());

            //각 고객의 위치 정보 입력
            stk = new StringTokenizer(br.readLine());
            loc = new posInfo[N + 2];
            loc[0] = new posInfo(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()));
            loc[N + 1] = new posInfo(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()));

            for(int i = 1; i <= N; i++) {
                loc[i] = new posInfo(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()));
            }

            //각 위치에서 각 노드들까지의 거리 계산
            dist = new int[N + 2][N + 2];

            for(int i = 0; i < N + 1; i++) {
                for(int j = i + 1; j < N + 2; j++) {
                    dist[i][j] = Math.abs(loc[i].row - loc[j].row) + Math.abs(loc[i].col - loc[j].col);
                    dist[j][i] = dist[i][j];
                }
            }

            //visited부터 탐색하려는 노드까지의 최소거리를 저장할 메모이제이션 배열 선언 및 초기화
            dp = new int[1 << (N + 1)][N + 1];

            for(int i = 1; i < dp.length; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
            sb.append("#" + tc + " ").append(tsp(0, 0)).append("\n");
        }
        bw.write(String.valueOf(sb));
        bw.close();
        br.close();
    }
    private static int tsp(int visited, int node) {
        visited |= (1 << node);

        //메모이제이션을 통해서 불필요한 탐색 중지
        if(dp[visited][node] != Integer.MAX_VALUE) return dp[visited][node];

        //끝점 도달시
        if(visited == (1 << (N + 1)) - 1) return dist[node][N + 1];

        for(int i = 0; i <= N; i++) {
            if(i != node && (visited & (1 << i)) == 0) {
                dp[visited][node] = Math.min(dp[visited][node], tsp(visited, i) + dist[node][i]);
            }
        }
        return dp[visited][node];
    }
}
