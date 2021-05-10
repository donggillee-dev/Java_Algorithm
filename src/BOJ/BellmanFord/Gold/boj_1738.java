package BOJ.BellmanFord.Gold;

import java.io.*;
import java.util.*;

//Logic
//민식이의 고민 문제와 매우 비슷한 문제이다
//다만 경로의 역추적을 할 경우 나는 시작지에서 도착지까지 bfs를 돌면서 optimal 배열의 최적값과 비교를 해주면서 경로를 세팅해갔지만
//도저히 왜 틀리는지 모르겠어서 해설지를 본 결과 bf를 돌때 각 도착지에 대해 최적의 출발지를 찾아서 넣어주면 되는 거였고
//사이클의 도착지 도착 가능 여부를 판별한 이후에 해당 path배열을 역추적하여 경로를 확인해서 출력해주면 되는 문제이다
//풀이 시간 : 1시간

public class boj_1738 {
    private static final int inf = -987654321;
    private static void bf(int n, int m, int[] optimal, int[] path, ArrayList<int[]> list, ArrayList<Integer> cycleList) {
        optimal[1] = 0;

        for(int i = 0; i < n - 1; i++) {
            for(int j = 0; j < m; j++) {
                int[] elem = list.get(j);

                if(optimal[elem[0]] == inf) continue;

                if(optimal[elem[1]] < optimal[elem[0]] + elem[2]) {
                    optimal[elem[1]] = optimal[elem[0]] + elem[2];
                    path[elem[1]] = elem[0];
                }
            }
        }

        for(int i = 0; i < m; i++) {
            int[] elem = list.get(i);

            if(optimal[elem[0]] == inf) continue;

            if(optimal[elem[1]] < optimal[elem[0]] + elem[2]) {
                cycleList.add(elem[1]);
            }
        }
    }

    private static boolean bfs(int n, ArrayList<Integer> cycleList, ArrayList<int[]> list) {
        boolean[] visited = new boolean[n + 1];
        for(int node : cycleList) {
            if(!visited[node]) {
                Queue<Integer> q = new LinkedList<>();
                visited[node] = true;
                q.add(node);

                while(!q.isEmpty()) {
                    int cur = q.poll();

                    for(int[] elem : list) {
                        int start = elem[0], next = elem[1];

                        if(start == cur && !visited[next]) {
                            visited[next] = true;
                            q.add(next);
                        }
                    }
                }
            }
        }

        return visited[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        int[] optimal = new int[n + 1];
        ArrayList<int[]> list = new ArrayList<>();
        ArrayList<Integer> cycleList = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(stk.nextToken());
            int v = Integer.parseInt(stk.nextToken());
            int w = Integer.parseInt(stk.nextToken());

            list.add(new int[]{u, v, w});
        }

        int[] path = new int[n + 1];
        Arrays.fill(optimal, inf);
        bf(n, m, optimal, path, list, cycleList);

        if(optimal[n] == inf) {
            System.out.print(-1);
            return;
        }

        if(bfs(n, cycleList, list)) {
           System.out.print(-1);
        } else {
            StringBuilder sb = new StringBuilder();
            int i = n;
            sb.append(n);

            while(path[i] != 0) {
                sb.insert(0, path[i] + " ");
                i = path[i];
            }
            System.out.print(sb.toString());
        }
    }
}
