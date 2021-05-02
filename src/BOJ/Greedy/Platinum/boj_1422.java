package BOJ.Greedy.Platinum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1422 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length() == o2.length()) {
                    return Integer.parseInt(o2) - Integer.parseInt(o1);
                } else
                    return o1.length() - o2.length();
            }
        });

        int n = Integer.parseInt(stk.nextToken()), m = Integer.parseInt(stk.nextToken());

        for(int i = 0; i < n; i++) {
            pq.add(br.readLine());
        }

        for(int i = 0; i < m - n; i++) {
            sb.append(pq.peek());
        }

        while(!pq.isEmpty()) {
            sb.append(pq.poll());
        }

        System.out.print(sb);
    }
}
