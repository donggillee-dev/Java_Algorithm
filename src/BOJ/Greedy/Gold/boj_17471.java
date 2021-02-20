package BOJ.Greedy.Gold;

import java.io.*;
import java.util.*;

public class boj_17471 {
    private static int N, answer = Integer.MAX_VALUE;
    private static int[] peopleArr;
    private static boolean[][] Map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        N = Integer.parseInt(br.readLine());
        Map = new boolean[N + 1][N + 1];
        peopleArr = new int[N + 1];

        //인구 정보 넣어줌
        stk = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            peopleArr[i] = Integer.parseInt(stk.nextToken());
        }

        //구역간 연결 정보
        for(int i = 1; i <= N; i++) {
            stk = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(stk.nextToken()), tmp;
            for(int j = 0; j < num; j++) {
                tmp = Integer.parseInt(stk.nextToken());
                Map[i][tmp] = true;
                Map[tmp][i] = true;
            }
        }
        solution(0, 0, 0);
        if(answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
    private static void solution(int visited, int curNode, int cnt) {
        if(curNode != 0) {
            int visitedB = ((visited) ^ ((1 << (N + 1)) - 1));
            answer = Math.min(answer, getRemain(visited, visitedB));
        }
        if(cnt == N / 2) return;
        for(int i = 1; i <= N; i++) {
            if(i != curNode && (visited & (1 << i)) == 0) {
                solution(visited | (1 << i), i, cnt + 1);
            }
        }
    }
    private static int getRemain(int visitedA, int visitedB) {
        int sumA = 0, sumB = 0;
        Queue<Integer> aQ = new LinkedList<>();
        Queue<Integer> bQ = new LinkedList<>();

        for(int i = 1; i <= N; i++) {
           if((visitedA & (1 << i)) != 0) {
               visitedA ^= (1 << i);
               aQ.add(i);
               sumA += peopleArr[i];
               while(!aQ.isEmpty()) {
                   int node = aQ.poll();
                   for(int j = 1; j <= N; j++) {
                       if((visitedA & (1 << j)) != 0 && Map[node][j]) {
                           visitedA ^= (1 << j);
                           sumA += peopleArr[j];
                           aQ.add(j);
                       }
                   }
               }
               break;
           }
        }


        if(visitedA != 0) return Integer.MAX_VALUE;

        for(int i = 1; i <= N; i++) {
            if((visitedB & (1 << i)) != 0) {
                visitedB ^= (1 << i);
                bQ.add(i);
                sumB += peopleArr[i];
                while(!bQ.isEmpty()) {
                    int node = bQ.poll();
                    for(int j = 1; j <= N; j++) {
                        if((visitedB & (1 << j)) != 0 && Map[node][j]) {
                            visitedB ^= (1 << j);
                            sumB += peopleArr[j];
                            bQ.add(j);
                        }
                    }
                }
                break;
            }
        }


        if(visitedB - 1 != 0) return Integer.MAX_VALUE;

        return Math.abs(sumA - sumB);
    }
}
