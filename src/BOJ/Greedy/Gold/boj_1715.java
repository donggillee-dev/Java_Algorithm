package BOJ.Greedy.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

//Logic
//그때그때 가장 작은 카드 더미수 두개를 뽑아와서 비교하면 되는 문제
//풀이 시간 : 10분

public class boj_1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int n = Integer.parseInt(br.readLine());
        int answer = 0;

        for(int i = 0; i < n; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        for(int i = 0; i < n - 1; i++) {
            int a = pq.poll();
            int b = pq.poll();
            answer += (a + b);

            pq.add(a + b);
        }

        System.out.println(answer);
    }
}
