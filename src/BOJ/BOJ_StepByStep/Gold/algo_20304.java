package BOJ.BOJ_StepByStep.Gold;

import java.io.*;
import java.util.*;

public class algo_20304 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        Queue<Info> Q = new LinkedList<>();
        int N = Integer.parseInt(br.readLine());
        int cpy = N;
        int M = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[N + 1];
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int answer = 0;
        int length = 0;
        while(cpy > 0) {
            length++;
            cpy/=2;
        }
        while(stk.hasMoreTokens()) {
            Q.offer(new Info(Integer.parseInt(stk.nextToken()), 0));
        }

        while(!Q.isEmpty()) {
            Info tmp = Q.poll();
            answer = tmp.length;
            for(int i = 0; i < length; i++) {
                int num = tmp.num ^ (1 << i);

                if(num <= N && !visited[num]) {
                    visited[num] = true;
                    Q.offer(new Info(num, tmp.length + 1));
                }
            }
        }
        System.out.println(answer);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
    private static class Info {
        int num;
        int length;
        public Info(int num, int length) {
            this.num = num;
            this.length = length;
        }
    }
}
