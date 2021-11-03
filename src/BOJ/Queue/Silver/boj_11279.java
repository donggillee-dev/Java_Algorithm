package BOJ.Queue.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

//Logic
//우선순위큐를 이용해서 해결 가능

public class boj_11279 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int N = Integer.parseInt(br.readLine());


        while(N --> 0) {
            int num = Integer.parseInt(br.readLine());

            if(num == 0 ) {
                if(pq.isEmpty()) {
                    sb.append(0);
                } else {
                    sb.append(pq.poll());
                }

                sb.append("\n");

                continue;
            }

            pq.add(num);
        }

        System.out.print(sb);
    }
}
