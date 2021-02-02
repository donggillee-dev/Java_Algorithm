package Programmers.Programmers_Lv2;
import java.util.*;
public class BracketTransfer {
    public static void main(String[] args) {
        String p = "()()()()))";
        Solution sol = new Solution();

        String answer = sol.solution(p);

        System.out.println(answer);

        return;
    }
    private static class Solution {
        public String solution(String p) {
            String answer = "";
            answer = transfer(p);
            return answer;
        }
        public String transfer(String w) {
            if(w.length() == 0) return "";

            String u = "";
            String v = "";

            int cntRight = 0;
            int cntLeft = 0;

            for(int i = 0; i < w.length(); i++) {
                if(w.charAt(i) == '(') {
                    cntLeft++;
                    u += '(';
                } else if(w.charAt(i) == ')'){
                    cntRight++;
                    u += ')';
                }

                if(cntLeft == cntRight){
                    for(int j = i + 1; j < w.length(); j++) {
                        v += w.charAt(j);
                    }
                    break;
                }
            }

            Stack<Character> S = new Stack<>();

            for(int i = 0; i < u.length(); i++) {
                if(u.charAt(i) == ')') {
                    if(!S.isEmpty()) {
                        char p = S.pop();
                        if(p == ')') {
                            S.push(p);
                            S.push(')');
                        }
                    } else {
                        S.push(')');
                    }
                } else S.push(u.charAt(i));
            }

            if(S.size() == 0) { //u가 올바른 문자열
                u += transfer(v);
                return u;
            } else { //u가 올바르지 않은 문자열
                String tmp = "(";
                String tmp2 = "";

                tmp += transfer(v);
                tmp += ")";
                u = u.substring(1, u.length() - 1);
                for(int i = 0; i < u.length(); i++) {
                    if(u.charAt(i) == '(')
                        tmp2 += ")";
                    else
                        tmp2 += "(";
                }
                tmp += tmp2;
                return tmp;
            }
        }
    }
}
