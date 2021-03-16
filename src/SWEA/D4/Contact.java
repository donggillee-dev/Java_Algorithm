package SWEA.D4;

import java.io.*;
import java.util.*;
//Logic
//초기에는 큐에 현재 노드와 깊이를 저장하여 순간순간 정답을 판단하게 하려고했지만 제대로 나오지 않아
//visited배열을 응용하였다 => visited배열에 깊이를 저장하고 0으로 초기화 되어있지 않은 상태이면 방문여부를 판단할 수 있기때문
//풀이 시간 : 20분

public class Contact {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;

        for(int tc = 1; tc <= 10; tc++) {
            stk = new StringTokenizer(br.readLine());
            int numOfData = Integer.parseInt(stk.nextToken()), startPerson = Integer.parseInt(stk.nextToken());
            boolean[][] Map = new boolean[101][101];


            stk = new StringTokenizer(br.readLine());
            for(int i = 0; i < numOfData / 2; i++) {
                Map[Integer.parseInt(stk.nextToken())][Integer.parseInt(stk.nextToken())] = true;
            }
            sb.append("#" + tc + " ");
            sb.append(BFS(startPerson, Map)).append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
    private static int BFS(int startPerson, boolean[][] Map) {
        int[] visited = new int[101];
        Queue<Integer> Q = new LinkedList<>();
        Q.add(startPerson);
        visited[startPerson] = 1;
        while(!Q.isEmpty()) {
            int startNode = Q.poll();
            for(int i = 1; i <= 100; i++) {
                if(Map[startNode][i] && visited[i] == 0) {
                    Q.add(i);
                    visited[i] = visited[startNode] + 1;
                }
            }
        }
        int depth = 0, answer = 0;
        for(int i = 1; i <= 100; i++) {
            if(visited[i] >= depth) {
                depth = visited[i];
                answer = i;
            }
        }
        return answer;
    }
}
