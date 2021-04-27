package BOJ.LCA.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
//Logic
//여러가지가 섞여있어서 좀 어려웠던 문제
//스택과 주어진 문자열을 이용해서 각 노드의 depth, 2^0번째 부모, 문자열 idx에 따른 노드번호를 저장해놓는다
//LCA DP ver을 가지고 공통 조상 노드를 찾는다
//공통 조상이 문자열에서 처음 나타난 곳과 두번쨰 나타난 곳을 pos배열을 통해서 가져온다

//풀이 시간 : 90분
public class boj_2233 {
    private static int n, k;
    private static int[] depth;
    private static int[][] parent;
    private static void initParent() {
        for(int i = 1; i < k; i++) {
            for(int j = 1; j <= n; j++) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
            }
        }
    }
    private static int lca(int a, int b) {
        if(depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        for(int i = k - 1; i >= 0; i--) {
            if(Math.pow(2, i) <= depth[a] - depth[b]) {
                a = parent[a][i];
            }
        }

        if(a == b) return a;

        for(int i = k - 1; i >= 0; i--) {
            if(parent[a][i] != parent[b][i]) {
                a = parent[a][i];
                b = parent[b][i];
            }
        }

        return parent[a][0];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        Stack<Integer> s = new Stack<>();
        n = Integer.parseInt(br.readLine());
        k = 0;

        int tmp = 1;
        while(tmp <= n) {
            tmp <<= 1;
            k++;
        }

        parent = new int[n + 1][k];
        depth = new int[n + 1];
        String str = br.readLine();
        stk = new StringTokenizer(br.readLine());
        int node1 = Integer.parseInt(stk.nextToken()), node2 = Integer.parseInt(stk.nextToken());
        int length = str.length();
        int[] pos = new int[length];

        for(int i = 0, idx = 1; i < length; i++) {
            if(str.charAt(i) == '0') {
                pos[i] = idx;
                s.push(idx);
                idx++;
            } else {
                int cur = s.pop();
                if(!s.isEmpty()) {
                    parent[cur][0] = s.peek();
                }
                depth[cur] = s.size() + 1;
                pos[i] = cur;
            }
        }

        initParent();
        node1 = pos[node1 - 1];
        node2 = pos[node2 - 1];

        int ans = lca(node1, node2);
        int[] answer = new int[2];
        for(int i = 0, idx = 0; i < length && idx < 2; i++) {
            if(pos[i] == ans) {
                answer[idx] = (i + 1);
                idx++;
            }
        }

        Arrays.sort(answer);

        System.out.print(answer[0] + " " + answer[1]);
    }
}
