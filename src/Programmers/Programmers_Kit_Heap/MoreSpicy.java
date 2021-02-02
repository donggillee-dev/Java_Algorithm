package Programmers.Programmers_Kit_Heap;
import java.util.*;

public class MoreSpicy {
    public static void main(String[] args) {
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K = 7;

        Solution sol = new Solution();

        System.out.println(sol.solution(scoville, K));
    }
    private static class Solution {
        public int solution(int[] scoville, int K) {
            int answer = 0;
            PriorityQueue<Integer> Q = new PriorityQueue<>();

            for(int i = 0; i < scoville.length; i++) { //우선순위 큐에 넣어줌
                Q.offer(scoville[i]);
            }

            while(Q.size() > 1 && Q.peek() < K) {
                int first = Q.poll();
                int second = Q.poll();
                int mix = (first + second*2);
                Q.offer(mix);
                answer++;
            }

            if(Q.size() <= 1 && Q.peek() < K) answer = -1;
            return answer;
        }
    }
}
