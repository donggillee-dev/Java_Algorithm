package Jungol;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class jungol_1828 {
    private static class Info implements Comparable<Info> {
        int low, high;
        public Info(int low, int high) {
            this.low = low;
            this.high = high;
        }

        @Override
        public int compareTo(Info o) {
            if(this.high > o.high) return 1;
            else if(this.high == o.high) return 0;
            else return -1;
        }
    }
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Info> Q = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());
        int answer = 1, temp = 0;
        StringTokenizer stk;

        while(N --> 0) {
            stk = new StringTokenizer(br.readLine());
            Q.add(new Info(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken())));
        }

        temp = Q.peek().high;
        Info inf;

        while(!Q.isEmpty()) {
            inf = Q.poll();
            if(inf.low <= temp && temp <= inf.high) continue;

            temp = inf.high;
            answer++;
        }
        System.out.println(answer);
        br.close();
    }
}
