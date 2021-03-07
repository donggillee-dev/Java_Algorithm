package BOJ.Tree.Gold;

import java.io.*;
import java.util.*;
//Logic

//V개의 노드들에 대해서 다 깊이 우선 탐색을 하면 무조건 시간초과
//어차피 양방향 노드이기에 한 정점에서 갈 수 있는 가장 긴 거리를 먼저 구해줌 => 1에서 2로 가는데 가장 긴거면 2에서 1로 가는것도 가장 긴거
//도달한 위치에서 다른 방향으로 탐색해봄 => 트리 구조상 모든 노드들이 다 연결되어있는게 아니니까 다른 방향으로도 갈 수 있는 최장 길이가 존재하는지
//도착한 위치에서부터 탐색 시작

public class boj_1167 {
    private static int answer = -987654321, endNode = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer stk;
        int N = Integer.parseInt(br.readLine());
        ArrayList<int[]>[] Tree = new ArrayList[N + 1];
        boolean[] visited = new boolean[N + 1];

        for(int i = 1; i <= N; i++) {
            stk = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stk.nextToken());
            Tree[start] = new ArrayList<>();

            while(true) {
                int node = Integer.parseInt(stk.nextToken());
                if(node == -1) break;
                else {
                    int length = Integer.parseInt(stk.nextToken());
                    Tree[start].add(new int[]{node, length});
                }
            }
        }

        DFS(1, 0, Tree, visited);
        visited = new boolean[N + 1];
        DFS(endNode, 0, Tree, visited);
        sb.append(answer);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
    private static void DFS(int node, int length, ArrayList<int[]>[] Tree, boolean[] visited) {
        visited[node] = true;
        if(length > answer) {
            answer = length;
            endNode = node;
        }

        for(int[] arr : Tree[node]) {
            if(!visited[arr[0]]) {
                DFS(arr[0], length + arr[1], Tree, visited);
            }
        }
    }
}
