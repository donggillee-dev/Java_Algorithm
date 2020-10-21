package Programmers_Kit_StackQueue;
import java.util.*;

public class Stock {
    public static void main(String[] args) {
        int[] prices = new int[10];
        Solution sol = new Solution();
        int[] answer = sol.solution(prices);

        System.out.println(Arrays.toString(answer));
        return;
    }
    private static class Solution {
        public int[] solution(int[] prices) {
            int[] answer = new int[prices.length];
            int i = 0;
            Stack<Integer> S = new Stack<Integer>(); //인덱스를 넣는 스택

            S.push(i);//0번째 idx넣어줌

            for(i = 1; i < prices.length; i++) {
                while(!S.isEmpty() && prices[S.peek()] > prices[i]) { //현재 내가 집은 값보다 스택에 넣어져있는 전 값이 높으면 이전 값이 현재 하락되었다는 것
                    //하락하지 않는 지점까지 탐색
                    //하락했으면 내가 현재 집은 idx에서 하락한 지점의 idx를 빼줌 -> 가격이 떨어지지 않은 기간
                    int topIdx = S.pop();
                    answer[topIdx] = i - topIdx;
                }
                S.push(i);//while문 탈출하면 내가 집은 주식가격은 앞으로의 가격들과 비교하기 위해 스택에 넣어줌
            }

            while(!S.isEmpty()) { //위 반복문을 거치고 나면 끝까지 하락하지 않은 값들의 idx만이 스택에 남는다.
                int idx = S.pop();
                answer[idx] = i - idx - 1;
            }
            return answer;
        }
    }
}

