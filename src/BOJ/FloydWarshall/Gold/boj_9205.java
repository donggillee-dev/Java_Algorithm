package BOJ.FloydWarshall.Gold;

import java.io.*;
import java.util.*;

//Logic
//Floyd-Warshall 알고리즘으로 각 N + 2개의 정점들에 대해서 거리를 계산하여 Map배열을 만들어준다
//다만 내가 틀렸던 점은 각 간선을 연결해줄떄 양방향으로 해줘야하는데 단방향으로 했었던것
//양방향으로 설정하는 중에 거리값이 1000이상 나오는 경우는 뭘 해도 맥주를 채울 수 없기에 걔는 그냥 INF로 냅둔다

//풀이 시간 : 30분

public class boj_9205 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer stk;

        for(int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[][] posInfo = new int[N + 2][2];
            int[][] Map = new int[N + 2][N + 2];

            for(int i = 0; i < N + 2; i++) {
                stk = new StringTokenizer(br.readLine());
                posInfo[i][0] = Integer.parseInt(stk.nextToken());
                posInfo[i][1] = Integer.parseInt(stk.nextToken());
            }

            for(int i = 0; i < N + 2; i++) {
                Arrays.fill(Map[i], 987654321);
                for(int j = 0; j < N + 2; j++) {
                    int dist = Math.abs(posInfo[i][0] - posInfo[j][0]) +
                            Math.abs(posInfo[i][1] - posInfo[j][1]);
                    if(dist <= 1000) {
                        Map[i][j] = dist;
                        Map[j][i] = dist;
                    }
                }
            }

            for(int k = 0; k < N + 2; k++) {
                for(int i = 0; i < N + 2; i++) {
                    if(i == k) continue;
                    for(int j = 0; j < N + 2; j++) {
                        Map[i][j] = Math.min(Map[i][j], Map[i][k] + Map[k][j]);
                    }
                }
            }

            boolean isPossibe = (Map[0][N + 1] != 987654321);

            if(isPossibe) sb.append("happy\n");
            else sb.append("sad\n");
        }
        System.out.print(sb);
    }
}
