package BOJ.MST.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
//Logic
//도출될 최소비용의 값 범위를 생각하지 않아 오래걸렸던 문제
//그 부분만 제외하면 크루스칼 알고리즘 + 우선순위큐를 이용해 간단히 풀 수 있다
//풀이 시간 : 18분
public class boj_16398 {
    private static int n;
    private static int findParent(int x, int[] parents) {
        if(parents[x] == x) return x;
        else return parents[x] = findParent(parents[x], parents);
    }
    private static boolean unionFind(int a, int b, int[] parents) {
        a = findParent(a, parents);
        b = findParent(b, parents);

        if(a == b) return false;

        parents[b] = a;

        return true;
    }
    private static long solve(PriorityQueue<int[]> pq) {
        int conn = 0;
        long cost = 0;
        int[] parents = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        while(!pq.isEmpty()) {
            int[] elem = pq.poll();

            if(unionFind(elem[0], elem[1], parents)) {
                conn++;
                cost += elem[2];
            }

            if(conn == n - 1) break;
        }

        return cost;
    }
    private static PriorityQueue<int[]> input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        n = Integer.parseInt(br.readLine());

        for(int i = 1; i <= n; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                if(j > i) {
                    pq.add(new int[]{i, j, Integer.parseInt(stk.nextToken())});
                } else {
                    stk.nextToken();
                }
            }
        }

        return pq;
    }
    public static void main(String[] args) throws IOException {
        PriorityQueue<int[]> pq = input();
        System.out.println(solve(pq));
    }
}
