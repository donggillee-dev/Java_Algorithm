package BOJ.MST.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//Logic
//문제를 제대로 파악할 필요가 있음
//이장은 마을을 두 개의 분리된 마을로 분할할 계획 => 즉 MST이되 두개의 분리 집합으로 만들고 싶어함
//n - 2개의 간선이 연결되면 되는 문제
//풀이 시간 : 20분

public class boj_1647 {
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
    private static int solve(PriorityQueue<int[]> pq) {
        int conn = 0, cost = 0;
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

            if(conn == n - 2) break;
        }

        return cost;
    }
    private static PriorityQueue<int[]> input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        while (m-- > 0) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int c = Integer.parseInt(stk.nextToken());

            pq.add(new int[]{a, b, c});
        }

        return pq;
    }

    public static void main(String[] args) throws IOException {
        PriorityQueue<int[]> pq = input();
        System.out.print(solve(pq));
    }
}
