package Programmers.Programmers_Lv2;

public class StringCompress {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "abcabcabcabcdededededede";

        int ans = sol.solution(s);

        System.out.println(ans);

        return;
    }

    private static class Solution {
        public int solution(String s) {
            int answer = Integer.MAX_VALUE;

            if(s.length() == 1) answer = 1;

            for(int i = 1; i <= s.length() / 2; i++) {
                int cnt = 1;
                StringBuilder tmp = new StringBuilder();
                StringBuilder std = new StringBuilder();
                StringBuilder str = new StringBuilder();

                std.append(s.substring(0, i));

                for(int j = i; j < s.length(); j += i) {
                    if(i <= s.length() - j) {
                        str.append(s.substring(j, j + i));
                        if(std.compareTo(str) == 0) {
                            cnt++;
                        } else {
                            if(cnt != 1)
                                tmp.append(cnt);
                            tmp.append(std);
                            std.delete(0, std.length());
                            std.append(str);
                            cnt = 1;
                        }
                        str.delete(0, str.length());
                    } else {
                        if(cnt != 1) {
                            tmp.append(cnt).append(std);
                        } else {
                            tmp.append(std);
                        }
                        tmp.append(s.substring(j, s.length()));
                    }
                    if(s.length() % i == 0 && s.length() - i == j) {
                        if(cnt != 1) {
                            tmp.append(cnt).append(std);
                        } else {
                            tmp.append(std);
                        }
                    }
                }
                if(tmp.length() < answer) answer = tmp.length();
            }
            return answer;
        }
    }
}
