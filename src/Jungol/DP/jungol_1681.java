package Jungol.DP;

import java.io.*;
import java.util.*;

//Logic
//비트 마스킹과 TSP 알고리즘을 접목하여 해결할 수 있는 문제이다.

public class jungol_1681 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int MAX = 987654321;
        int N = Integer.parseInt(br.readLine());
        int[][] Map = new int[N][N];
        int[][] DP = new int[1 << N][N];

        //DP 배열의 행은 이때까지 방문한 도시들을 비트로 나타낸 것
        for(int i = 0; i < (1 << N); i++) {
            Arrays.fill(DP[i], MAX);
        }

        //Map 배열 initialize
        StringTokenizer stk;
        for(int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                Map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        //TSP 알고리즘
        //TSP 알고리즘의 중요한 포인트는 어느 정점을 시작정점으로 하던 어차피 돌아오기 때문에 상관이 없다는 것
        TSP(MAX, N, 1, 0, DP, Map);

        System.out.println(DP[1][0]);
    }
    private static int TSP(int MAX, int N, int visited, int curNode, int[][] DP, int[][] Map) {
        //모든 도시를 다 탐색한 상태라면은 curNode가 마지막 도시로 들어온 것
        if(visited == ((1 << N) - 1)) {
            //마지막 도시와 시작 도시의 연결 가능 유무를 체크
            if(Map[curNode][0] != 0) return Map[curNode][0];
            else return MAX;
        }

        //이미 방문한 visited에서 curNode로 가려는 값이 갱신된 경다우라면은 메모이제이션으로 다시 탐색할 필요가 없음
        if(DP[visited][curNode] != MAX) return DP[visited][curNode];

        //현재 도시에서 N개의 도시중 하나씩 탐색하여본다
        for(int i = 0; i < N; i++) {
            //현재 도시와 탐색하려는 도시가 연결되어있지 않거나 이미 방문한 도시라면은 무시
            if(Map[curNode][i] == 0 || (visited & (1 << i)) != 0) continue;
            //DP배열은 curNode까지 visited도시들을 방문하여 도달하였을때 최소 탐색 비용이 들어있다
            //그렇기에 기존 DP[visited][curNode]값과 현재 노드에서 다음 노드를 TSP한 값이 더 작은지를 비교해준
            DP[visited][curNode] = Math.min(DP[visited][curNode], TSP(MAX, N, visited | (1 << i), i, DP, Map) + Map[curNode][i]);
        }

        return DP[visited][curNode];
    }
}
