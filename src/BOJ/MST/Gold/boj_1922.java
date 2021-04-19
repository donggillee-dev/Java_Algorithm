package BOJ.MST.Gold;

import java.io.*;
import java.util.*;

//Logic
//MST, Kruskal 알고리즘으로 간단하게 해결할 수 있는 문제
//인접리스트나 인접 행렬을 만들 필요 없이 바로 최소힙에 넣어서 O(nlogn)으로 해결 가능
//풀이 시간 : 10분

public class boj_1922 {
    private static int[] p;
    private static int findParent(int x) {
        if(x == p[x]) return x;
        else return p[x] = findParent(p[x]);
    }
    private static boolean unionFind(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if(a == b) return false;

        p[b] = a;

        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        p = new int[n + 1];
        StringTokenizer stk;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        for(int i = 1; i <= n; i++) {
            p[i] = i;
        }

        for(int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int w = Integer.parseInt(stk.nextToken());

            pq.add(new int[]{a, b, w});
        }

        int cnt = 0;
        int ans = 0;

        while(!pq.isEmpty()) {
            int[] tmp = pq.poll();

            if(unionFind(tmp[0], tmp[1])) {
                cnt++;
                ans += tmp[2];

                if(cnt == n - 1) break;
            }
        }

        System.out.println(ans);
    }
}
