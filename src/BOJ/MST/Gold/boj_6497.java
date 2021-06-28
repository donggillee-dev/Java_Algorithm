package BOJ.MST.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
//풀이 시간 : 25분
//문제를 제대로 읽지 않아 오래걸렸다
//테케가 하나라고 나와있지 않음
public class boj_6497 {
    private static int[] parents;

    private static StringBuilder solve() throws IOException { //입력값 받고 mst함수 호출하며 최종적으로 출력해야하는 StringBuilder 리턴
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        while(true) {
            stk = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(stk.nextToken());
            int n = Integer.parseInt(stk.nextToken());
            int sum = 0;

            if(m == 0 && n == 0) break;
            parents = new int[m];

            for(int i = 0; i < m; i++) {
                parents[i] = i;
            }

            for(int i = 0; i < n; i++) {
                stk = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(stk.nextToken());
                int y = Integer.parseInt(stk.nextToken());
                int z = Integer.parseInt(stk.nextToken());
                sum += z;
                pq.add(new int[]{x, y, z});
            }

            sb.append(mst(m, sum, pq)).append("\n");
            pq.clear();
        }

        return sb;
    }
    private static int findParent(int x) { //입력받은 노드번호의 루트 노드가 무엇인지 확인
        if(x == parents[x]) return x;
        else return parents[x] = findParent(parents[x]);
    }
    private static boolean unionFind(int a, int b) { //두 노드를 입력받아 unionFind해주는 함수
        a = findParent(a);
        b = findParent(b);

        if(a == b) return false;

        parents[b] = a;

        return true;
    }
    private static int mst(int m, int sum, PriorityQueue<int[]> pq) { //우선순위큐를 이용해서 가장 작은 거리 간선을 뽑아와 mst수행
        int conn = 0, ans = 0;

        while(!pq.isEmpty()) {
            int[] elem = pq.poll();

            if(unionFind(elem[0], elem[1])) {
                ans += elem[2];
                conn++;
            }

            if(conn == m - 1) break;
        }
        return (sum - ans);
    }
    public static void main(String[] args) throws IOException {
        System.out.print(solve());
    }
}
