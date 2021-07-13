package BOJ.DFS.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//Logic
//우선 순환선 내부에 존재하는 역을 찾아내기 위한 DFS 탐색 필요!
//나머지 로직은 코드 내에 작성
//DFS의 기저조건때문에 많이 어려웠던 문제

//풀이 시간 : 60분
public class boj_16947 {
    private static boolean dfs(int start, int cur, boolean[] visited, ArrayList<Integer>[] adj) {
        boolean flag = false;
        int cnt = 0;
        visited[cur] = true;

        for (int next : adj[cur]) {
            if (!visited[next]) {
                if (dfs(start, next, visited, adj)) return true;
                visited[next] = false;
            } else {
                cnt++;
                if(next == start) flag = true;
            }
        }

        //현재 노드에서 이미 탐색한 노드 개수가 2개 이상이며
        //그 노드들 중에 시작 노드가 존재하면 순환고리 완성
        if(cnt >= 2 && flag)
            return true;

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[n + 1];
        int[] ans = new int[n + 1];
        ArrayList<Integer>[] adj = new ArrayList[n + 1];
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;

        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());

            adj[a].add(b);
            adj[b].add(a);
        }

        for (int i = 1; i <= n; i++) {
            if (dfs(i, i, visited, adj)) break;
            visited[i] = false;
        }

        //모든 노드 중에 순환역이 아닌 역을 가지고 순환역까지의 거리를 구해주는 로직
        for (int i = 1; i <= n; i++) {
            //아직 방문 한번도 안했고, 순환역중에 연결된 역이 3개 이상이면 지선의 시작점이다
            //지선에 대해서는 BFS 수행
            if(visited[i] && adj[i].size() >= 3) {
                Queue<int[]> q = new LinkedList<>();
                q.add(new int[]{i, 0});

                while(!q.isEmpty()) {
                    int[] elem = q.poll();
                    int cur = elem[0];
                    int dist = elem[1];

                    for(int next : adj[cur]) {
                        if(!visited[next]) {
                            visited[next] = true;
                            ans[next] = dist + 1;
                            q.add(new int[]{next, ans[next]});
                        }
                    }
                }
            }
        }

        for(int i = 1; i <= n; i++) {
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb);
    }
}
