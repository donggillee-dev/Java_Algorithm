package BOJ.Queue.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//Logic
//우선순위큐를 이용해서 n * n 개 넣어주고 n번쨰 큰 수 뽑아오면됨

//풀이 시간 : 5분

public class boj_2075 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        StringTokenizer stk;

        for(int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                pq.add(Integer.parseInt(stk.nextToken()));
            }
        }

        for(int i = 0; i < n - 1; i++) {
            pq.poll();
        }

        System.out.println(pq.peek());
    }
}
