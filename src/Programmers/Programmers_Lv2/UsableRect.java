package Programmers.Programmers_Lv2;

public class UsableRect {
    public static void main(String[] args) {
        int w = 8;
        int h = 12;
        Solution sol = new Solution();
        System.out.println(sol.solution(w, h));
        return;
    }
    private static class Solution {
        public long solution(int w, int h) {
            long answer = 1;
            long wl = Long.parseLong(String.valueOf(w));
            long hl = Long.parseLong(String.valueOf(h));

            answer = wl*hl - ((wl + hl) - gcd(wl, hl));
            return answer;
        }
        public long gcd( long w , long h ) {

            long small,big ;

            big = w >= h ? w : h ;
            small = w < h ? w : h ;

            while ( small != 0 ) {
                long nmg = big % small ;
                big = small;
                small = nmg;
            }
            return big;
        }
    }
}
