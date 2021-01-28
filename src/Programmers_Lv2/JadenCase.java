package Programmers_Lv2;
import java.util.StringTokenizer;

//항상 느끼는거... 문자열 문제 진짜 약하다.
//무턱대고 값 넣어보고 출력따라 로직 생각하지말고 논리적으로 로직을 다 짠 다음에 해보자...!
//문제를 잘 읽자

//내 패착
//문자열이 공백과 알파벳으로 이루어진다고 했지 하나의 공백이 문자열 사이사이에 들어간다고는 안했다
//공백 처리하는 로직에 있어서 인덱싱을 제대로 생각하질 못했다
//for문에서 공백 모든 길이를 다 찾고 처리됐겠지 생각했지만 while문 안에서 i값을 1을 증가시켜준채로 다음 문자를 탐색하게됨...
//이렇게 되면 공백을 만났을때 공백의 길이를 계산하고 나와서 한 문자를 건너띄고 그 다음 문자를 탐색하게 된다..

//제발 인덱싱 감으로 하지말고 머리로 안되면 그리기라도 해서 하자

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
