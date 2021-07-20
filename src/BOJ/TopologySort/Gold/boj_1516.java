package BOJ.TopologySort.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//Logic
//위상정렬로 풀 수 있는 문제
//위상정렬에 들어가는 점화식 생각좀 해야함

//풀이 시간 : 25분

public class boj_1516 {
    private static int n;
    private static StringBuilder topologySort(int[] degree, int[] times, ArrayList<ArrayList<Integer>> adj) {
        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new LinkedList<>();
        int[] result = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            result[i] = times[i];
            if(degree[i] == 0) {
                q.add(i);
            }
        }

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int next : adj.get(cur)) {
                degree[next]--;

                //선행된 건물로부터 다음에 지어야할 건물들 뽑아서 짓는다
                //현재 지으려는 건물의 최소 시간은 선행건물 짓는데 가장 오래 걸린 시간에다가 현재 건물 짓는 시간을 더해줘야함
                result[next] = Math.max(result[next], result[cur] + times[next]);

                if(degree[next] == 0) {
                    q.add(next);
                }
            }
        }

        for(int i = 1; i <= n; i++) {
            sb.append(result[i]).append("\n");
        }

        return sb;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        n = Integer.parseInt(br.readLine());
        int[] degree = new int[n + 1];
        int[] times = new int[n + 1];
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for(int i = 1; i <= n; i++) {
            stk = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(stk.nextToken());
            times[i] = time;

            int prev = Integer.parseInt(stk.nextToken());

            while(prev != -1) {
                adj.get(prev).add(i); //선행되어야 하는 건물 번호노드에 현재 건물번호를 넣어줌
                degree[i]++; //현재 건물번호 차수 증가

                prev = Integer.parseInt(stk.nextToken());
            }
        }

        System.out.print(topologySort(degree, times, adj));
    }
}
