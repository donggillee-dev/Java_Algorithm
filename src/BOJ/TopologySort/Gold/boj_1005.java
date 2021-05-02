package BOJ.TopologySort.Gold;
import java.io.*;
import java.util.*;

public class boj_1005 {
    static int[] DP, Check, Cost;
    static int N, K;
    static ArrayList<ArrayList<Integer>> Queue;//위상정렬을 위한 이중 ArrayList
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stk.nextToken());
            K = Integer.parseInt(stk.nextToken());
            DP = new int[N + 1];
            Check = new int[N + 1];
            Cost = new int[N + 1];
            stk = new StringTokenizer(br.readLine());
            Queue = new ArrayList<>();//ArrayList 초기화
            Queue.add(new ArrayList<Integer>());//각 N개의 Node 노드들에 대해 집이 필요하므로 0 ~ N개의 new ArrayList가 필요

            for(int j = 1; j <= N; j++) {
                Queue.add(new ArrayList<Integer>());//ArrayList 초기화
                Cost[j] = Integer.parseInt(stk.nextToken());
                DP[j] = Cost[j];
            }

            for(int j = 0; j < K; j++) {
                stk = new StringTokenizer(br.readLine());
                int From = Integer.parseInt(stk.nextToken());
                int To = Integer.parseInt(stk.nextToken());
                Check[To]++;
                Queue.get(From).add(To);//노드 세팅
            }
            TopLogicSort();
            sb.append(DP[Integer.parseInt(br.readLine())]).append("\n");
        }
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static void TopLogicSort() {
        Queue<Integer> Q = new LinkedList<>();

        for(int i = 1; i <= N; i++) {
            if(Check[i] == 0) {//To를 짓기 위해서는 From이 필요, 그러므로 한번도 To에서 불려진적 없는애는 초기에 지어야 하는 건물이므로 큐에 초기값으로 세팅
                Q.offer(i);
            }
        }
        for(int i = 0; i < N; i++) {
            int StartNode = Q.poll();//시작노드를 끄집어옴
            for(int EndNode : Queue.get(StartNode)) {//해당 시작노드가 존재하는 위상정렬 ArrayList에 접근해서 빼오고
                Check[EndNode]--;//해당 타겟 노드로의 경로를 하나 제거해줌 why? StartNode에서 EndNode하나 연결해준다는 생각으로!!
                DP[EndNode] = Math.max(DP[EndNode], DP[StartNode] + Cost[EndNode]);//DP배열을 통해 EndNode건물을 짓는데 최대의 시간을 측정 -> EndNode를 짓기 위해서는 StartNode들 중에서 가장 오래 걸리는 건물을 기다려야하기 때문
                if(Check[EndNode] == 0) {//EndNode로의 경로가 모두 삭제됨 -> EndNode를 확실히 건설했다는 뜻!!
                    Q.offer(EndNode);//EndNode로부터 뻗어나가는 경로를 탐색해줌
                }
            }
        }
    }
}