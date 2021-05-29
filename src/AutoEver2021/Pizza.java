package AutoEver2021;

import java.io.IOException;
import java.util.*;

//Logic
//각 위치에서 road 인접 리스트를 이용해서 bfs를 돈다
//시간 복잡도 향상을 위해 최소힙을 이용하여 bfs탐색
    //최소힙 정렬 기준
        //1순위 -> 현재까지 탐색한 거리가 짧은 순
        //2순위 -> 만약 동일한 거리가 존재하면 마을 번호가 작은순
//방문 처리 => visited로 함
//현재 위치가 피자 지점일 경우에는 리턴
public class Pizza {

    public static void main(String[] args) throws IOException {
        System.out.println(Arrays.toString(solution(5, new int[][]{{1, 2, 3}, {1, 4, 4}, {2, 3, 1}, {2, 5, 2}, {3, 5, 1}, {4, 5, 2}}, new int[]{4, 2, 3})));
        System.out.println(Arrays.toString(solution(6, new int[][]{{1, 2, 2}, {1, 5, 1}, {2, 3, 1}, {2, 4, 2}, {2, 6, 3}, {3, 4, 2}, {4, 5, 1}}, new int[]{1, 3})));
    }

    private static int[] solution(int V, int[][] road, int[] branch) {
        HashMap<Integer, Integer> hash = new HashMap<>();
        ArrayList<int[]>[] adj = new ArrayList[V + 1];
        int[] answer = new int[V];

        //인접 리스트 생성
        for (int i = 1; i <= V; i++) {
            adj[i] = new ArrayList<>();
        }

        //양방향 그래프이기에 인접 리스트 생성시 양방향으로 넣어준다
        for (int[] elem : road) {
            int v1 = elem[0];
            int v2 = elem[1];
            int d = elem[2];

            adj[v1].add(new int[]{v2, d});
            adj[v2].add(new int[]{v1, d});
        }

        //각 피자 지점에 대해서 접근 속도 빠르게 하기위해 피자지점 해시맵 생성
        for (int point : branch) {
            hash.put(point, 0);
        }

        //bfs를 돌아서 가장 가까운 피자지점을 넣어준다
        for (int i = 1; i <= V; i++) {
            answer[i - 1] = dijk(i, V, adj, hash);
        }

        return answer;
    }

    private static int dijk(int start, int V, ArrayList<int[]>[] adj, HashMap<Integer, Integer> hash) {
        //방문 배열 생성
        int[] dist = new int[V + 1];
        //bfs를 위한 큐, 다만 시간 복잡도 향상을 위해 최소 힙(우선순위 큐) 사용
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[1] - o2[1];
                }
            }
        });

        Arrays.fill(dist, 987654321);

        //시작지 true로 세팅
        //시작지 값 bfs에 넣어줌
        dist[start] = 0;
        pq.add(new int[]{start, 0});

        while (!pq.isEmpty()) {
            //현재 방문 정보 뽑아옴
            int[] cur = pq.poll();
            int cur_node = cur[0];
            int cur_dist = cur[1];

            //해시맵에서 현재 노드가 피자지점인지 체크
            if (hash.get(cur_node) != null) {
                return cur_node;
            }

            //
            if(dist[cur_node] < cur_dist) continue;

            //현재 노드의 도로 정보를 인접 리스트에서 가져온다
            int length = adj[cur_node].size();

            //도로 정보를 가지고 다음 노드를 체크
            for (int i = 0; i < length; i++) {
                int[] next = adj[cur_node].get(i);
                int next_node = next[0];
                int next_dist = next[1];

                //방문하지 않은 노드에 대해서만 처리
                if (dist[next_node] > cur_dist + next_dist) {
                    pq.add(new int[]{next_node, cur_dist + next_dist});
                    dist[next_node] = cur_dist + next_dist;
                }
            }
        }

        return 0;
    }
}


