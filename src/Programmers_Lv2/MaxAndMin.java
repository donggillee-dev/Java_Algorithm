package Programmers_Lv2;

import java.util.StringTokenizer;

//Lv2라고 하기에는 많이 쉬운 문제
//문자열에서 모든 숫자들은 공백으로 구분되기 때문에 StringTokenizer만 사용할 줄 알면 된다
//각 문자를 Integer로 형변환 시켜줘서 max min 모두에 대해 비교하여 정답을 도출할 수 있다.

public class MaxAndMin {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String str = "-1 -2 -3 -4";
        System.out.println(sol.solution(str));
    }
    private static class Solution {
        public String solution(String s) {
            StringBuilder sb = new StringBuilder();
            String answer;
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            StringTokenizer stk = new StringTokenizer(s);
            while(stk.hasMoreTokens()) {
                int num = Integer.parseInt(stk.nextToken());

                min = Math.min(min, num);
                max = Math.max(max, num);
            }

            sb.append(min).append(" ").append(max);
            answer = String.valueOf(sb);
            return answer;
        }
    }
}
