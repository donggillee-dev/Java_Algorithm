package BOJ.Greedy.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

//Logic
//오름차순 정렬해서 하나씩 차이 더해주면 되는 문제
//다만 합의 크기가 long을 벗어나는 경우 있을 수 있다

//풀이 시간 : 5분

public class boj_2012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long ans = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0; i < n; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        for(int i = 1; i <= n; i++) {
            ans += Math.abs(i - pq.poll());
        }

        System.out.println(ans);
    }
}
