package BOJ.Hash.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//Logic
//해시 맵을 이용하여 풀 수 있는 문제
//풀이 시간 : 5분

public class boj_1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        HashMap<String, Integer> hash = new HashMap<>();
        PriorityQueue<String> pq = new PriorityQueue<>();
        int n = Integer.parseInt(stk.nextToken()), m = Integer.parseInt(stk.nextToken());

        while(n --> 0) {
            hash.put(br.readLine(), 0);
        }
        while(m --> 0) {
            String tmp = br.readLine();
            if(hash.get(tmp) != null) {
                pq.add(tmp);
            }
        }

        sb.append(pq.size()).append("\n");
        while(!pq.isEmpty()) {
            sb.append(pq.poll()).append("\n");
        }
        System.out.print(sb);
    }
}
