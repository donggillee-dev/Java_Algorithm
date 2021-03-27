package BOJ.MST.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//Logic
//Kruskal 알고리즘을 사용하면 되는 문제
//풀이 시간 : 5분
public class boj_1197 {
    private static int[] parents;
    private static int findParent(int x) {
        if(parents[x] == 0) return x;
        return parents[x] = findParent(parents[x]);
    }
    private static boolean unionFind(int x, int y) {
        x = findParent(x);
        y = findParent(y);

        if(x == y) return false;

        parents[y] = x;
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(stk.nextToken());
        int E = Integer.parseInt(stk.nextToken());
        int cnt = 0, answer = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        parents = new int[V + 1];

        for(int i = 0; i < E; i++) {
            stk = new StringTokenizer(br.readLine());
            pq.add(new int[]{Integer.parseInt(stk.nextToken()),
                    Integer.parseInt(stk.nextToken()),
                    Integer.parseInt(stk.nextToken())});
        }

        while(!pq.isEmpty()) {
            int[] elem = pq.poll();

            if(unionFind(elem[0], elem[1])) {
                cnt++;
                answer += elem[2];
            }

            if(cnt == E - 1) break;
        }

        System.out.print(answer);
    }
}
