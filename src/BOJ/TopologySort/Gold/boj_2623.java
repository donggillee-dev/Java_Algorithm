package BOJ.TopologySort.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//Logic
//전통적인 위상 정렬 문제이다
//현재의 노드를 꺼내와 다음 노드를 탐색하고 다음 노드를 필요로하는 개수가 0이 되면 걔도 탐색시작
//불가능할 경우 => 즉 모든 degree가 0이 안되어있으면 0 출력

//풀이 시간 : 20분

public class boj_2623 {
    private static int n, m;
    private static int[] degree;

    private static int stoi(String str) {
        return Integer.parseInt(str);
    }

    private static ArrayList<Integer>[] input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        n = stoi(stk.nextToken());
        m = stoi(stk.nextToken());
        degree = new int[n + 1];

        ArrayList<Integer>[] retList = new ArrayList[n + 1];

        for(int i = 0; i <= n; i++) {
            retList[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());

            int cnt = stoi(stk.nextToken());
            int prev = stoi(stk.nextToken()), next = 0;

            for(int j = 0; j < cnt - 1; j++) {
                next = stoi(stk.nextToken());

                degree[next]++;
                retList[prev].add(next);

                prev = next;
            }
        }

        return retList;
    }

    private static String ts(ArrayList<Integer>[] list) {
        Queue<Integer> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= n; i++) {
            if(degree[i] == 0) q.add(i);
        }

        while(!q.isEmpty()) {
            int cur = q.poll();

            sb.append(cur).append("\n");

            for(int next : list[cur]) {
                if(--degree[next] == 0) {
                    q.add(next);
                }
            }
        }

        for(int i = 1; i <= n; i++) {
            if(degree[i] != 0) return "0";
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            ArrayList<Integer>[] list = input();
            System.out.print(ts(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
