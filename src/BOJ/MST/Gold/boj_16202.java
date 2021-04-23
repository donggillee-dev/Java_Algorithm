package BOJ.MST.Gold;

import java.io.*;
import java.util.*;
//Logic
//Kruskal 알고리즘으로 해결
//우선 순위큐로 범벅을 한 코드이므로 그렇게 효율이 좋지는 않음
    //MST게임이 돌때 MST큐에서 하나를 빼서 다시 초기화해주고 다시 큐 세팅해주는 과정을 반복하기에 시간복잡도 상승
//풀이 시간 : 30분

//더 좋은 로직을 발견
    //간선의 가중치는 간선이 주어지는 순서대로이기때문에 간선 배열을 만들어준다
    //그리고 간선에 대해서 for문을 돌면서 mst여부 체크
        //가능하면 해당 idx다음부터 다시 mst여부 체크
        //불가능하면 이후부터 다 0으로 출력
//public class Main {
//
//    static int N, M;
//    static int[] root;
//
//    public static void main(String[] args) throws IOException {
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//        int K = Integer.parseInt(st.nextToken());
//
//        root = new int[N + 1];
//        for (int i = 1; i <= N; i++) {
//            root[i] = i;
//        }
//
//        int[][] link = new int[M][3];
//
//        int a, b;
//
//        for (int i = 0; i < M; i++) {
//            st = new StringTokenizer(br.readLine());
//            a = Integer.parseInt(st.nextToken());
//            b = Integer.parseInt(st.nextToken());
//
//            link[i] = new int[] { a, b, i + 1 };
//        }
//
//        StringBuilder sb = new StringBuilder();
//
//        int count;
//        int sum;
//
//        for (int i = 0; i < K; i++) {
//
//            count = 1;
//            sum = 0;
//
//            for (int j = i; j < M; j++) {
//
//                int x = findRoot(link[j][0]);
//                int y = findRoot(link[j][1]);
//
//                if (x == y) { // 같은 그룹
//                    continue;
//                }
//
//                root[y] = x;
//                count++;
//                sum += link[j][2];
//
//                if (count == N) {
//                    break;
//                }
//            }
//
//            if (count < N) {
//                for(; i<K; i++){
//                    sb.append(0+" ");
//                }
//                break;
//            }
//
//            sb.append(sum+" ");
//            for(int j = 1; j<=N; j++){
//                root[j] = j;
//            }
//        }
//
//        System.out.println(sb.toString());
//    }
//
//    static int findRoot(int i){ // 같은 그룹인지 판별하기 위함
//        if (root[i] == i) {
//            return i;
//        }else{
//            return root[i] = findRoot(root[i]);
//        }
//    }
//
//}

public class boj_16202 {
    private static int n, m, k;
    private static int[] p;
    private static void initParent() {
        for(int i = 1; i <= n; i++) {
            p[i] = i;
        }
    }
    private static int findParent(int x) {
        if(x == p[x]) return x;
        else return p[x] = findParent(p[x]);
    }
    private static boolean unionFind(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if(a == b) return false;

        p[b] = a;

        return true;
    }
    private static String game(int cnt, int sum, PriorityQueue<int[]> vQ, PriorityQueue<int[]> mstQ) {
        StringBuilder sb = new StringBuilder();
        Queue<int[]> q = new LinkedList<>();

        for(int i = 1; i < k; i++) {
            int[] elem = mstQ.poll();

            sum -= elem[2];
            cnt--;
            initParent();

            while(!mstQ.isEmpty()) {
                elem = mstQ.poll();
                q.add(elem);
                unionFind(elem[0], elem[1]);
            }

            while(!q.isEmpty()) {
                mstQ.add(q.poll());
            }

            while(!vQ.isEmpty()) {
                elem = vQ.poll();
                int start = elem[0], end = elem[1], cost = elem[2];

                if(unionFind(start, end)) {
                    cnt++;
                    sum += cost;
                    mstQ.add(elem);
                    if(cnt == n - 1) {
                        sb.append(sum).append(" ");
                        while(!q.isEmpty()) {
                            vQ.add(q.poll());
                        }
                        break;
                    }
                } else {
                    q.add(elem);
                }
            }

            if(cnt != n -1) {
                for(int rem = i; rem < k; rem++) {
                    sb.append(0).append(" ");
                }
                break;
            }
        }

        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());
        p = new int[n + 1];

        PriorityQueue<int[]> vQ = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        PriorityQueue<int[]> mstQ = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        Queue<int[]> tmpQ = new LinkedList<>();

        for(int i = 1; i <= m; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            vQ.add(new int[]{a, b, i});
        }

        initParent();

        int cnt = 0, sum = 0;
        while(!vQ.isEmpty()) {
            int[] elem = vQ.poll();
            int start = elem[0], end = elem[1], cost = elem[2];

            if(unionFind(start, end)) {
                cnt++;
                sum += cost;
                mstQ.add(elem);
                if(cnt == n - 1) break;
            } else {
                tmpQ.add(elem);
            }
        }

        if(cnt == n - 1) {
            while(!tmpQ.isEmpty()) {
                vQ.add(tmpQ.poll());
            }
            sb.append(sum).append(" ").append(game(cnt, sum, vQ, mstQ));
        } else {
            while(k --> 0) {
                sb.append(0).append(" ");
            }
        }

        System.out.println(sb);
    }
}
