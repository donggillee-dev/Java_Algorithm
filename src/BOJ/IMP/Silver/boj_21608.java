package BOJ.IMP.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//Logic
//그냥 구현하면 되는 문제
//시간 복잡도 계산 n * n * n * 4 => 20 * 20 * 20 * 4 => 16,000

public class boj_21608 {
    private static int n, totSize;
    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, -1, 0, 1};

    private static class Info implements Comparable<Info> {
        int row, col;
        int favCnt, empCnt;

        public Info(int row, int col, int favCnt, int empCnt) {
            this.row = row;
            this.col = col;
            this.favCnt = favCnt;
            this.empCnt = empCnt;
        }

        @Override
        public int compareTo(Info o) {
            if (this.favCnt == o.favCnt) {
                if (this.empCnt == o.empCnt) {
                    if (this.row == o.row) {
                        return this.col - o.col;
                    } else {
                        return this.row - o.row;
                    }
                } else {
                    return o.empCnt - this.empCnt;
                }
            } else {
                return o.favCnt - this.favCnt;
            }
        }
    }

    private static boolean isRange(int row, int col) {
        if (row < 0 || col < 0 || row >= n || col >= n) return false;

        return true;
    }

    private static void locateStd(int[] order, int[][] map, HashMap<Integer, Set<Integer>> favHash) {
        for (int stdNum : order) {
            PriorityQueue<Info> pq = new PriorityQueue<>();

            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    if (map[row][col] != 0) continue;

                    int empCnt = 0, favCnt = 0;

                    for (int dir = 0; dir < 4; dir++) {
                        int nr = row + dr[dir];
                        int nc = col + dc[dir];

                        if (isRange(nr, nc)) {
                            int nearByStdNum = map[nr][nc];

                            if (nearByStdNum == 0) {
                                empCnt++;
                            } else if (favHash.get(stdNum).contains(nearByStdNum)) {
                                favCnt++;
                            }
                        }
                    }

                    pq.add(new Info(row, col, favCnt, empCnt));
                }
            }

            Info inf = pq.poll();
            map[inf.row][inf.col] = stdNum;
        }
    }

    private static int getAns(int[][] map, HashMap<Integer, Set<Integer>> favHash) {
        int ans = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int stdNum = map[row][col];
                int cnt = 0;
                Set<Integer> favSet = favHash.get(stdNum);

                for (int dir = 0; dir < 4; dir++) {
                    int nr = row + dr[dir];
                    int nc = col + dc[dir];

                    if (isRange(nr, nc)) {
                        int nearByStdNum = map[nr][nc];
                        if (favSet.contains(nearByStdNum)) {
                            cnt++;
                        }
                    }
                }

                switch (cnt) {
                    case 1:
                        ans += 1;
                        break;
                    case 2:
                        ans += 10;
                        break;
                    case 3:
                        ans += 100;
                        break;
                    case 4:
                        ans += 1000;
                        break;
                    default:
                        break;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        totSize = n * n;
        int[] order = new int[totSize];
        int[][] map = new int[n][n];
        HashMap<Integer, Set<Integer>> favHash = new HashMap<>(totSize + 1);
        StringTokenizer stk;

        for (int i = 1; i <= totSize; i++) {
            favHash.put(i, new HashSet<>());
        }

        for (int i = 0; i < totSize; i++) {
            stk = new StringTokenizer(br.readLine());

            int stdNum = Integer.parseInt(stk.nextToken());
            order[i] = stdNum;
            for (int j = 0; j < 4; j++) {
                favHash.get(stdNum).add(Integer.parseInt(stk.nextToken()));
            }
        }

        locateStd(order, map, favHash);

        System.out.println(getAns(map, favHash));
    }
}
