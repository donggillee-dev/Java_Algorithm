package ShinhanCard2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution4 {
    private static String solution(int n, int[] h) {
        int[] ans = new int[n];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();

        ans[0] = 0;
        pq.add(h[0]);

        for(int i = 1; i < n; i++) {
            ans[i] = pq.size();
            if(pq.peek() <= h[i]) {
                while(!pq.isEmpty() && pq.peek() <= h[i]) {
                    pq.poll();
                }
            }

            pq.add(h[i]);
        }

        for(int i = 0; i < n; i++) {
            sb.append(ans[i]).append(" ");
        }

        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] h = new int[n];
        StringTokenizer stk = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            h[i] = Integer.parseInt(stk.nextToken());
        }

        System.out.println(solution(n, h));
    }
}
