package BOJ.Tree.Silver;

import java.io.*;
import java.util.*;
//Logic
//위에서 아래 방향의 느낌인 트리를 먼저 ArrayList형 배열로 만들어줌
//그러고 나서 BFS 탐색을 통해서 현재 노드에서 주어진 node1, node2를 탐색하게끔 처리해주고
//현재 노드에서 그 두개의 노드가 탐색되어졌다면 촌수를 더해준다.

//다른 사람의 풀이를 보니까 그냥 양방향 노드처럼... 물론 트리는 비방향 구조이긴하다...여튼
//2차원 배열로 양방향 노드처럼 해놓고 시작 노드 즉 촌수를 구해야해는 시작점(node1)에서 BFS, DFS탐색을 가지고 존재하는 노드들을 탐색해가며 촌수를 세팅
//(node2)의 촌수 배열에 값이 -1이 아닌 상태이면 해당 촌수를 출력해준다.

public class boj_2644 {
    private static class Info {
        int depth, childNode;
        public Info(int depth, int childNode) {
            this.depth = depth;
            this.childNode = childNode;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] list = new ArrayList[N + 1];
        ArrayList<Info>[] searchRest = new ArrayList[N + 1];

        for(int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
            searchRest[i] = new ArrayList<>();
        }

        int node1, node2;
        StringTokenizer stk = new StringTokenizer(br.readLine());
        node1 = Integer.parseInt(stk.nextToken());
        node2 = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(br.readLine());

        while(M --> 0) {
            stk = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(stk.nextToken());
            int num2 = Integer.parseInt(stk.nextToken());
            list[num1].add(num2);
        }

        for(int i = 1; i <= N; i++) {
            Queue<Info> Q = new LinkedList<>();
            Q.add(new Info(0, i));

            while(!Q.isEmpty()) {
                Info inf = Q.poll();
                searchRest[i].add(new Info(inf.depth, inf.childNode));

                for(int j = 0; j < list[inf.childNode].size(); j++) {
                    Q.add(new Info(inf.depth + 1, list[inf.childNode].get(j)));
                }
            }
        }
        int answer = Integer.MAX_VALUE;
        for(int i = 1; i <= N; i++) {
            if(searchRest[i].size() > 1) {
                int sum = 0, cnt = 0;
                Info inf;
                for(int j = 0; j < searchRest[i].size(); j++) {
                    inf = searchRest[i].get(j);

                    if(inf.childNode == node1 || inf.childNode == node2) {
                        cnt++;
                        sum += inf.depth;
                    }
                }
                if(cnt == 2 && sum < answer) answer = sum;
            }
        }
        if(answer == Integer.MAX_VALUE) {
            sb.append(-1);
        } else {
            sb.append(answer);
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
