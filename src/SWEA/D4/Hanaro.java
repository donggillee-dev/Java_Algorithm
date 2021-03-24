package SWEA.D4;

import java.io.*;
import java.util.*;
//Logic
//Kruskal 알고리즘으로 해결하면 되는 문제이다.
//풀이 시간 : 20분
public class Hanaro {
    private static int parent[];
    private static class Info implements Comparable<Info>{
        int start, end;
        double dist;

        public Info(int start, int end, double dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(Info o) {
            return Double.compare(this.dist, o.dist);
        }
    }
    private static int findParent(int x) {
        if(parent[x] == x) return x;
        return parent[x] = findParent(parent[x]);
    }
    private static boolean isUnion(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if(a == b) return true;

        if(a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;
        int TestCase = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= TestCase; tc++) {
            int N = Integer.parseInt(br.readLine());
            double E;
            PriorityQueue<Info> Q = new PriorityQueue<>();

            int[][] islands = new int[N][2];
            parent = new int[N + 1];

            for(int i = 1; i <= N; i++) {
                parent[i] = i;
            }

            for(int j = 0; j < 2; j++) {
                stk = new StringTokenizer(br.readLine());
                for(int i = 0; i < N; i++) {
                    islands[i][j] = Integer.parseInt(stk.nextToken());
                }
            }

            E = Double.parseDouble(br.readLine());
            //입력 완료

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(i != j) {
                        int x = Math.abs(islands[i][0] - islands[j][0]);
                        int y = Math.abs(islands[i][1] - islands[j][1]);
                        double dist = (Math.pow(x, 2) + Math.pow(y, 2));
                        Q.add(new Info(i, j, dist));
                    }
                }
            }
            //해저터널 거리 계산 완료

            //Kruskal 알고리즘 시작
            long answer = 0;
            int cnt = 0;
            while(!Q.isEmpty()) {
                if(cnt == N - 1) break;

                Info inf = Q.poll();
                if(isUnion(inf.start, inf.end)) continue;

                answer += inf.dist;
                cnt++;
            }
            sb.append("#" + tc + " " + Math.round(answer * E)).append("\n");
        }
        System.out.println(sb);
    }
}

//아래 풀이는 실행시간이 엄청 빠른 사람의 코드를 보고 인상깊어서 긁어왔다
//그때그때 선택되어지지 않은 정점중 가장 최소의 거리를 가지는 정점을 선택해서 그 정점으로부터 선택되어지지 않은 애들까지의 거리를 다 계산하고
//다시 반복 => N번

//public class Hanaro {
//    static int N;
//    static double E;
//    static int[][] map;
//    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//        int T = Integer.parseInt(br.readLine());
//        for(int tc = 1; tc <= T; tc++) {
//            N = Integer.parseInt(br.readLine());
//            map = new int[N][N];
//            StringTokenizer st = null;
//            for(int i = 0; i < 2; i++) {
//                st = new StringTokenizer(br.readLine());
//                for(int j = 0; j < N; j++) {
//                    map[j][i] = Integer.parseInt(st.nextToken());
//                }
//            }
//            E = Double.parseDouble(br.readLine());
//            boolean[] visited = new boolean[N];
//            double[] length = new double[N];
//            Arrays.fill(length, Double.MAX_VALUE);
//            length[0] = 0;
//
//            for(int i = 0; i < N; i++) {
//                int min = -1;
//                double minLength = Double.MAX_VALUE;
//                for(int j = 0; j < N; j++) {
//                    if(!visited[j] && length[j] < minLength) {
//                        min = j;
//                        minLength = length[j];
//                    }
//                }
//                visited[min] = true;
//
//                for(int j = 0; j < N; j++) {
//                    if(!visited[j] && length[j] > distance(min, j)) {
//                        length[j] = distance(min, j);
//                    }
//                }
//            }
//            double ans = 0;
//            for(int i = 0; i < length.length; i++) {
//                ans += length[i] * E;
//            }
//
//            sb.append("#" + tc + " " + Math.round(ans)).append("\n");
//        }
//        System.out.print(sb);
//    }
//    private static double distance(int a1, int a2) {
//        return Math.pow(map[a1][0] - map[a2][0], 2) + Math.pow(map[a1][1] - map[a2][1], 2);
//    }
//}