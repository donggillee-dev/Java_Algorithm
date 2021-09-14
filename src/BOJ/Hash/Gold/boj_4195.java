package BOJ.Hash.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

//Logic
//유파 + HashMap 이용해서 풀 수 있는 문제
//유파에서의 노드 인덱스를 사용해야하므로 입력받는 친구의 이름(문자열을 하나의 고유 번호로 지정
//이를 통해 각 노드의 부분집합 관계와 그 집단의 루트노드가 가지고 있는 모든 자식노드들의 개수를 level로 관리

public class boj_4195 {
    private static void initArr(int length, int[] parent, int[] level) {
        for(int i = 0; i < length; i++) {
            parent[i] = i;
            level[i] = 1;
        }
    }
    private static int findParent(int node, int[] parent) {
        if(node == parent[node]) return node;
        else return parent[node] = findParent(parent[node], parent);
    }

    private static int unionFind(int a, int b, int[] parent, int[] level) {
        a = findParent(a, parent);
        b = findParent(b, parent);

        if(a != b) {
            parent[b] = a;
            level[a] += level[b];

            level[b] = 1;
        }

        return level[a];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while(t --> 0) {
            int f = Integer.parseInt(br.readLine());
            int[] parent = new int[f * 2];
            int[] level = new int[f * 2];
            int node = 0;

            initArr(f * 2, parent, level);

            HashMap<String, Integer> hash = new HashMap<>();

            for(int i = 0; i < f; i++) {
                stk = new StringTokenizer(br.readLine());
                String f1 = stk.nextToken();
                String f2 = stk.nextToken();

                if(hash.get(f1) == null) {
                    hash.put(f1, node++);
                }

                if(hash.get(f2) == null) {
                    hash.put(f2, node++);
                }

                sb.append(unionFind(hash.get(f1), hash.get(f2), parent, level)).append("\n");
            }
        }

        System.out.print(sb);
    }
}
