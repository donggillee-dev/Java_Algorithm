package BOJ.BackTracking.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

//Logic
//큐에다가 넣어서 하나씩 처리, 마지막 숫자보다 작은 숫자들을 넣으면 되는 거
//우선순위큐를 가지고 하면 된다
//풀이 시간 : 45분

public class boj_1038 {
    private static int n;
    private static void solution(long cnt, PriorityQueue<Long> pq) {
        while(cnt < n) {
            long tmp = pq.poll();
            long mod = tmp % 10;

            for(int i = 0; i < mod; i++) {
                long elem = tmp * 10 + i;
                pq.add(elem);
                cnt++;
                if(cnt == n) {
                    System.out.println(elem);
                    break;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        if (n <= 10 && n >= 0) System.out.println(n);
        else if (n > 1022) {
            System.out.println(-1);
        } else {
            int cnt = 9;
            PriorityQueue<Long> pq = new PriorityQueue<>();

            for(long i = 0; i < 10; i++) {
                pq.add(i);
            }

            solution(cnt, pq);
        }
    }
}
