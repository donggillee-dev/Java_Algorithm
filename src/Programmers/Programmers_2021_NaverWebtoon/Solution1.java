package Programmers.Programmers_2021_NaverWebtoon;

import java.util.Collections;
import java.util.PriorityQueue;

//Logic
//그리디 문제
//가장 큰 가격에 가장 큰 쿠폰을 먹이면 된다

public class Solution1 {
    private static class Solution {
        private int solution(int[] prices, int[] discounts) {
            int answer = 0;
            PriorityQueue<Integer> priceQ = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> discQ = new PriorityQueue<>(Collections.reverseOrder());
            //Arrays.sort를 사용하면 최악의 경우 n^2의 시간복잡도가 발생할 수 있으므로 두개의 힙구조 사용

            //가격, 쿠폰 둘 다 내림차순 정렬해준다
            for (int price : prices) {
                priceQ.add(price);
            }

            for (int discount : discounts) {
                discQ.add(discount);
            }

            //하나씩 빼서 할인된 가격 정답에 더해줌
            while (!discQ.isEmpty() && !priceQ.isEmpty()) {
                int price = priceQ.poll();
                int discount = discQ.poll();
                int diff = price / 100 * discount;

                answer += (price - diff);
            }

            while (!priceQ.isEmpty()) {
                answer += priceQ.poll();
            }

            return answer;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(new int[]{13000, 88000, 10000}, new int[]{30, 20}));
    }
}
