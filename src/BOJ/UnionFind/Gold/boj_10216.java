package BOJ.UnionFind.Gold;

import java.io.*;
import java.util.*;
//Logic
//Union-Find로 해결한 문제이다
//각 그룹을 지어줄때 트리의 깊이를 따져서 그 깊이가 더 깊은 곳 즉 그룹이 더 큰 쪽으로 합쳐준다
//각각의 그룹들을 합쳐주고 나서 결과값을 도출할때 N의 노드들에 대해서 findParent를 호출해주어야 1 -> 2 , 3 -> 4일때 2 -> 3의 경우에
//1 -> 4가 최종적으로 연결이 되개 된다.

//풀이 시간 : 38분
public class boj_10216 {
    private static class Info {
        int x, y, r;

        public Info(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }
    private static int findParent(int x, int[] p) {
        if(x == p[x]) return x;
        else return p[x] = findParent(p[x], p);
    }
    private static void union(int a, int b, int[] p, int[] depth) {
        a = findParent(a, p);
        b = findParent(b, p);

        if(a == b) return;

        if(depth[a] < depth[b]) {
            p[a] = b;
            depth[b]++;
        } else {
            p[b] = a;
            depth[a]++;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;
        int T = Integer.parseInt(br.readLine());

        while(T --> 0) {
            int N = Integer.parseInt(br.readLine());
            Info[] list = new Info[N + 1];
            int[] p = new int[N + 1];
            int[] depth = new int[N + 1];

            for(int i = 1; i <= N; i++) {
                p[i] = i;
                stk = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(stk.nextToken());
                int y = Integer.parseInt(stk.nextToken());
                int r = Integer.parseInt(stk.nextToken());
                list[i] = new Info(x, y, r);
            }

            for(int i = 1; i < N; i++) {
                for(int j = i + 1; j <= N; j++) {
                    Info inf_a = list[i];
                    Info inf_b = list[j];

                    double dist = inf_a.r + inf_b.r;
                    int x1 = Math.abs(inf_a.x - inf_b.x);
                    int y1 = Math.abs(inf_a.y - inf_b.y);
                    double width = Math.sqrt(x1 * x1 + y1 * y1);

                    if(width <= dist) {
                        union(i, j, p, depth);
                    }
                }
            }
            HashSet<Integer> set = new HashSet<>();
            for(int i = 1; i <= N; i++) {
                set.add(findParent(i, p));
            }
            sb.append(set.size()).append("\n");
        }
        System.out.print(sb);
    }
}
