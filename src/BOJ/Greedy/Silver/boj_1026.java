package BOJ.Greedy.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//Logic
//가장 큰 수에 가장 작은 수를 곱해주어서 합을 더하면 된다

public class boj_1026 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()), ans = 0;
        PriorityQueue<Integer> invQ = new PriorityQueue<>();
        PriorityQueue<Integer> revQ = new PriorityQueue<>(Collections.reverseOrder());
        StringTokenizer stk = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            invQ.add(Integer.parseInt(stk.nextToken()));
        }

        stk = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            revQ.add(Integer.parseInt(stk.nextToken()));
        }

        while(!invQ.isEmpty()) {
            ans += invQ.poll() * revQ.poll();
        }

        System.out.println(ans);
    }
}
