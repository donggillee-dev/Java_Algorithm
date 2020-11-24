package Programmers_Lv2;

public class NextBiggestNumber {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 78;
        int ans = sol.solution(n);

        System.out.println(ans);

        return;
    }
    private static class Solution {
        public int solution(int n) {
            int answer = 0;
            int cntNum = 0;
            int cntTarget = 0;
            int tmp = n;

            cntNum = getCnt(tmp);
            for(int i = n + 1; i <= Integer.MAX_VALUE; i++) {
                tmp = i;
                cntTarget = getCnt(tmp);
                if(cntTarget == cntNum) {
                    answer = i;
                    break;
                }
            }
            return answer;
        }
        public int getCnt(int num) {
            int cnt = 0;

            while(num > 0) {
                int rem = num % 2;
                if(rem == 1) cnt++;
                num /= 2;
            }
            return cnt;
        }
    }
}
