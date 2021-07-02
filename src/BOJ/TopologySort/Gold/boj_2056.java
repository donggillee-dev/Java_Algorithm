package BOJ.TopologySort.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
//Logic
//2차원 배열로 작업간의 선수 관계를 따지기에는 작업 개수가 10000개이기에 메모리 초과 => 이중 ArrayList로 문제 해결 가능
//ArrayList에는 현재 작업이 수행되기 전에 작업되어야하는 작업들을 각 작업 번호 노드에다가 add해줌
//동시에 현재 작업의 차수를++
//그리고 위상정렬 수행
//위상 정렬 내에서 가장 중요한 핵심은
//특정 작업이 수행할 다음 작업까지 걸리는 시간이 이전까지 작업시간 + 다음작업 or 이미 큰 값인지를 확인해서 넣어줘야함 => 38 line

//result 배열에서 가장 큰 값이 정답이 된다
//풀이 시간 : 25분
public class boj_2056 {
    private static int topologySort(int n, ArrayList<ArrayList<Integer>> adj, int[] degree, int[] time) {
        int[] result = new int[n + 1];
        Queue<Integer> q = new LinkedList<>();

        for(int i = 1; i <= n; i++) {
            result[i] = time[i];
            if(degree[i] == 0) {
                q.add(i);
            }
        }

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int next : adj.get(cur)) {
                degree[next]--;

                result[next] = Math.max(result[next], result[cur] + time[next]);

                if(degree[next] == 0) {
                    q.add(next);
                }
            }
        }

        int ans = 0;
        for(int elem : result) {
            ans = Math.max(ans, elem);
        }
        return ans;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] degree = new int[n + 1];
        int[] time = new int[n + 1];
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        StringTokenizer stk;

        for(int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for(int i = 1; i <= n; i++) {
            stk = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(stk.nextToken());

            int num = Integer.parseInt(stk.nextToken());

            for(int j = 0; j < num; j++) {
                int prev = Integer.parseInt(stk.nextToken());
                adj.get(prev).add(i); //이전 작업이 해야하는 다음 작업
                degree[i]++;
            }
        }

        System.out.println(topologySort(n, adj, degree, time));
    }
}
