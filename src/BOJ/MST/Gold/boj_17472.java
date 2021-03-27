package BOJ.MST.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//Logic
//각 섬들에 대해서 번호를 매겨주고 각 섬들을 연결시켜줄 수 있는 간선을 다 만들어준다
//그 이후에 그 간선들을 길이 순으로 정렬하여 하나씩 뽑아서 두 섬을 연결시켜본다 => 연결시키기 이전에 UnionFind를 하여 동일한 부모인지 아닌지 체크
//그렇게 모든 섬들을 MST로 연결시키면 정답 도출 가능
//풀이 시간 : 30분

public class boj_17472 {
    private static int[] parents;
    private static int[] dir_x = {-1, 0, 1, 0};
    private static int[] dir_y = {0, -1, 0, 1};
    private static int init(int N, int M, int[][] Map) {
        boolean[][] visited = new boolean[N][M];
        int cnt = 1;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(Map[i][j] == 1 && !visited[i][j]) {
                    Map[i][j] = cnt;
                    visited[i][j] = true;
                    Queue<int[]> Q = new LinkedList<>();
                    Q.offer(new int[]{i, j});

                    while(!Q.isEmpty()) {
                        int[] elem = Q.poll();

                        for(int dir = 0; dir < 4; dir++) {
                            int nx = elem[0] + dir_x[dir];
                            int ny = elem[1] + dir_y[dir];

                            if(nx < 0 || ny < 0 || nx >= N || ny >= M || Map[nx][ny] == 0 || visited[nx][ny]) continue;

                            Map[nx][ny] = cnt;
                            visited[nx][ny] = true;
                            Q.add(new int[]{nx, ny});
                        }
                    }
                    cnt++;
                }
            }
        }
        return cnt - 1;
    }
    private static PriorityQueue BF(int N, int M, int[][] Map) {
        PriorityQueue<int[]> Q = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(Map[i][j] >= 1) {
                    for(int dir = 0; dir < 4; dir++) {
                        int nx = i + dir_x[dir];
                        int ny = j + dir_y[dir];
                        int cnt = 1;

                        if(nx < 0 || ny < 0 || nx >= N || ny >= M || Map[nx][ny] != 0) continue;

                        int tmpx = nx;
                        int tmpy = ny;
                        int endNode = 0;
                        while(true) {
                            tmpx += dir_x[dir];
                            tmpy += dir_y[dir];

                            if(tmpx < 0 || tmpy < 0 || tmpx >= N || tmpy >= M) {
                                cnt = 0;
                                break;
                            }
                            if(Map[tmpx][tmpy] != 0) {
                                endNode = Map[tmpx][tmpy];
                                break;
                            }

                            cnt++;
                        }

                        if(cnt >= 2) {
                            Q.add(new int[]{Map[i][j], endNode, cnt});
                        }
                    }
                }
            }
        }
        return Q;
    }
    private static int findParent(int x) {
        if(parents[x] == x) return x;
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
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int[][] Map = new int[N][M];

        for(int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                Map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        int cnt = init(N, M, Map);
        parents = new int[cnt + 1];
        for(int i = 1; i <= cnt; i++) {
            parents[i] = i;
        }

        PriorityQueue<int[]> Q = BF(N, M, Map);
        int vertCnt = 0;
        int answer = 0;
        while(!Q.isEmpty()) {
            int[] elem = Q.poll();
            int node1 = elem[0], node2 = elem[1], weight = elem[2];

            if(unionFind(node1, node2)) {
                vertCnt++;
                answer += weight;
            }

            if(vertCnt == cnt - 1) break;
        }

        if(vertCnt != cnt - 1) System.out.println(-1);
        else System.out.println(answer);
    }
}
