package Programmers_Lv2;
import java.util.StringTokenizer;

public class JadenCase {
    public static void main(String[] args) {
        String s = "3people unFollowed me";
        System.out.print(Solution.solution(s));
    }
    private static class Solution {
        public static String solution(String s) {
            StringBuilder answer = new StringBuilder();
            StringTokenizer stk = new StringTokenizer(s);
            StringBuilder spaceSb = new StringBuilder();
            StringBuilder[] sbArr = new StringBuilder[stk.countTokens()];

            for(int i = 0; stk.hasMoreTokens(); i++) {
                sbArr[i] = new StringBuilder();
                sbArr[i].append(stk.nextToken());
            }

            int idx = 0;

            for(int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == ' ') {
                    spaceSb.append(" ");
                    i++;
                    while(i < s.length() && s.charAt(i) == ' ') {
                        spaceSb.append(" ");
                        i++;
                    }
                    answer.append(spaceSb);
                    spaceSb.delete(0, spaceSb.length());
                    i--;
                } else {
                    if(!Character.isDigit(sbArr[idx].charAt(0))) {
                        //첫번째 문자를 빼와서 UpperCase로 바꿔줌
                        char ch = Character.toUpperCase(sbArr[idx].charAt(0));
                        //String 객체를 사용하지 않고 StringBuilder를 통해서만 조작해야 하니까 LowerCase로 바꿔준 문자열을 추가했을시
                        //지울 문자열의 길이정보를 기억하기 위한 변수
                        int length;

                        //첫번째 문자는 ch에 UpperCase로 저장되어있으니까 삭제
                        sbArr[idx].deleteCharAt(0);
                        //현재 StringBuilder에 있는 문자열은 통째로 LowerCase로 해줘야되는데 StringBuilder내에서 조작할 수 없으므로
                        length = sbArr[idx].length();
                        //LowerCase로 된 문자열을 sb에 추가해줌
                        sbArr[idx].append(String.valueOf(sbArr[idx]).toLowerCase());

                        //추가 하기 이전에 있던 문자열은 삭제
                        sbArr[idx].delete(0, length);
                        //UpperCase로 된 첫 문자를 맨 앞에 넣어줌
                        sbArr[idx].insert(0, ch);
                    }
                    answer.append(sbArr[idx]);
                    i += sbArr[idx].length() - 1;
                    idx++;
                }
            }
            return String.valueOf(answer);
        }
    }
}
