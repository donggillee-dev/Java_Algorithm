package BOJ.Greedy.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

//Logic
//양수들은 양수들끼리
//음수와 0은 끼리끼리 모아준다
//양수들이 있는 큐에서 1이 존재하는 경우에는 곱해주지 않고 그냥 따로 더해주는게 더 큰 경우이므로 처리
//음수의 경우에는 0이 존재할때 곱해주는게 더 큰 수를 만들 수 있으므로 처리

//풀이 시간 : 30분

public class boj_1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> plusPQ = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minusPQ = new PriorityQueue<>();
        int n = Integer.parseInt(br.readLine());
        int ans = 0;

        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num <= 0) minusPQ.add(num);
            else plusPQ.add(num);
        }

        while(plusPQ.size() >= 2) {
            int num1 = plusPQ.poll();
            int num2 = plusPQ.poll();

            if(num1 == 1 || num2 == 1) {
                plusPQ.add(num1);
                plusPQ.add(num2);
                break;
            }
            ans += (num1 * num2);
        }

        while(!plusPQ.isEmpty()) {
            ans += plusPQ.poll();
        }

        while(minusPQ.size() >= 2) {
            int num1 = minusPQ.poll();
            int num2 = minusPQ.poll();

            ans += (num1 * num2);
        }

        while(!minusPQ.isEmpty()) {
            ans += minusPQ.poll();
        }

        System.out.println(ans);
    }
}
