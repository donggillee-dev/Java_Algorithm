package BOJ.Tree.Silver;

import java.io.*;
import java.util.*;

//Logic

//노드 정보를 저장할 자료구조 => ArrayList 배열 형태로
//노드 지우는거 => 해당 노드로부터 그 자식노드들 재귀로 돌면서 제거
//리프 노드 개수 찾는거 => 모든 노드를 깊이우선으로 탐색해가면서 visited체크해주고 더이상 다음 노드 탐색할 수 없으면 answer증가

public class boj_1068 {
    private static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] tree = new ArrayList[N];
        ArrayList<Integer> root = new ArrayList<>();
        boolean[] visited = new boolean[N];
        StringTokenizer stk = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(stk.nextToken());
            if(num == -1) {
                root.add(i);
                continue;
            }
            if(tree[num] == null) {
                tree[num] = new ArrayList<>();
            }
            tree[num].add(i);
        }

        //지우려는 목표노드 아래로 다 가지치기
        int target = Integer.parseInt(br.readLine());
        visited[target] = true;
        remove(target, tree, visited);

        //남아있는 리프 노드 탐색 부분
        for(int i = 0; i < root.size(); i++) {
            int node = root.get(i);
            if(visited[node]) continue;
            visited[node] = true;
            DFS(node, visited, tree);
        }

        for(int i = 0; i < visited.length; i++) {
            if(!visited[i]) answer++;
        }
        sb.append(answer);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
    private static void remove(int target, ArrayList<Integer>[] tree, boolean[] visited) {
        ArrayList<Integer> tmp = tree[target];
        if(tmp == null || tmp.size() == 0) return;
        else {
            for(int i = 0; i < tmp.size(); i++) {
                int next = tmp.get(i);
                visited[next] = true;
                remove(next, tree, visited);
            }
        }
    }
    private static void DFS(int startNode, boolean[] visited, ArrayList<Integer>[] tree) {
        if(tree[startNode] == null) {
            answer++;
            return;
        }

        boolean flag = true;
        for(int i = 0; i < tree[startNode].size(); i++) {
            int node = tree[startNode].get(i);
            if(!visited[node]) {
                flag = false;
                visited[node] = true;
                DFS(node, visited, tree);
            }
        }
        if(flag) answer++;
    }
}
