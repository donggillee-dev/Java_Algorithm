package BOJ.DP.Silver;

import java.io.*;
import java.util.*;

public class boj_4883 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int[][] zeroDir = {{1, 0}, {1, 1}, {0, 1}};
        int[][] oneDir = {{1, -1}, {1, 0}, {1, 1}, {0, 1}};
        int[][] twoDir = {{1, -1}, {1, 0}};

        int tc = 1;
        while(true) {
            int N = Integer.parseInt(br.readLine());
            if(N == 0) break;
            long[][] DP = new long[N][3];
            int[][] Map = new int[N][3];
            StringTokenizer stk;
            PriorityQueue<int[]> Q = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if(DP[o1[0]][o1[1]] > DP[o2[0]][o2[1]]) return 1;
                    else if(DP[o1[0]][o1[1]] == DP[o2[0]][o2[1]]) return 0;
                    else return -1;
                }
            });

            for(int i = 0; i < N; i++) {
                Arrays.fill(DP[i], Integer.MAX_VALUE);
                stk = new StringTokenizer(br.readLine());
                for(int j = 0; j < 3; j++) {
                    Map[i][j] = Integer.parseInt(stk.nextToken());
                }
            }
            DP[0][1] = Map[0][1];
            Q.add(new int[]{0, 1});

            while(!Q.isEmpty()) {
                int[] elem = Q.poll();
                int[][] tmp;
                if(elem[1] == 0) {
                    tmp = zeroDir;
                } else if(elem[1] == 1) {
                    tmp = oneDir;
                } else {
                    tmp = twoDir;
                }

                for(int i = 0; i < tmp.length; i++) {
                    int nx = elem[0] + tmp[i][0];
                    int ny = elem[1] + tmp[i][1];
                    if(nx >= N) continue;
                    if(DP[nx][ny] != Integer.MAX_VALUE && DP[elem[0]][elem[1]] + Map[nx][ny] > DP[elem[0]][elem[1]]) continue;

                    if(DP[elem[0]][elem[1]] + Map[nx][ny] < DP[nx][ny]) {
                        DP[nx][ny] = DP[elem[0]][elem[1]] + Map[nx][ny];
                        Q.add(new int[]{nx, ny});
                    }
                }
            }
            sb.append(tc + ". " + DP[N - 1][1]).append("\n");
            tc++;
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
