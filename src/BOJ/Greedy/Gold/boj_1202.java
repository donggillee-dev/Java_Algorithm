package BOJ.Greedy.Gold;

import java.io.*;
import java.util.*;

public class boj_1202 {
    private static class Info implements Comparable<Info>{
        int weight, price;
        public Info(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        @Override
        public int compareTo(Info o) {
            return this.weight - o.weight;
        }
    }
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());
        PriorityQueue<Integer> priceQ = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Info> jewQ = new PriorityQueue<>();
        PriorityQueue<Integer> bagQ = new PriorityQueue<>();

        for(int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            jewQ.add(new Info(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken())));
        }

        for(int i = 0; i < K; i++) {
            bagQ.add(Integer.parseInt(br.readLine()));
        }

        int j = 0;
        long answer = 0;
        for(int i = 0; i < K; i++) {
            int w = bagQ.poll();
            while(j < N && w >= jewQ.peek().weight ) {
                priceQ.add(jewQ.peek().price);
                jewQ.poll();
                j++;
            }
            if(!priceQ.isEmpty()) {
                answer += priceQ.poll();
            }
        }

        System.out.println(answer);
    }
}
