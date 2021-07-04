package Programmers.Programmers_2021_NaverWebtoon;

import java.util.*;

//Logic
//문자열의 앞에서부터 맨 뒤 문자와 동일한 문자를 계속 찾아나가다가 찾으면
//해당 위치까지의 문자열 길이를 맨 뒤의 부분 문자열과 검증

public class Solution2 {
    private static class Solution {
        private Queue<String> makeQueue(StringBuilder sb) {
            //부분 문자열을 담을 자료구조
            Queue<String> q = new LinkedList<>();

            //문자열의 조작을 용이하게 하기 위해 StringBuilder 사용
            while (sb.length() != 0) {
                int length = sb.length();
                int prefix = 0, postfix = length - 1, cnt = 0;

                //맨 뒤의 문자와 동일한 문자가 나올때까지 앞 index 이동
                for (; prefix < length; prefix++) {
                    if (sb.charAt(prefix) == sb.charAt(postfix))
                        break;
                }

                //앞 부분문자열 길이와 뒷 부분문자열이 어느 길이만큼 동일한지 검증
                for (int i = prefix; i >= 0; i--) {
                    if (sb.charAt(i) == sb.charAt(postfix)) {
                        cnt++;
                        postfix--;
                    }
                }

                //검증한 부분 문자열 길이가 원본 문자열 길이와 동일하면 일치하는 앞뒤가 존재하지 않다는 뜻
                if (cnt == length) {
                    break;
                } else if (cnt == (prefix + 1)) { //cnt가 앞 부분 문자열 index와 동일하면 앞/뒤 부분 문자열 없애주고 큐에 넣어줌
                    q.add(sb.substring(0, cnt));
                    sb.delete(length - cnt, length);
                    sb.delete(0, cnt);
                }
            }

            //만들어진 부분 문자열 큐를 리턴한다
            return q;
        }

        private String[] solution(String s) {
            int length = 0;
            String[] answer;
            StringBuilder sb = new StringBuilder(s);
            Queue<String> q = makeQueue(sb);

            //원본 문자열에 덜 잘라진 부분 문자열이 존재한다는건 걔는 조건에 일치하는 게 없다는 뜻이므로 홀수개만큼 배열 크기 만들어준다
            if (sb.length() != 0) {
                length = q.size() * 2 + 1;
            } else { // 그 외에는 깔끔하게 앞뒤로 잘린거니까 짝수개
                length = q.size() * 2;
            }

            answer = new String[length];
            //홀수의 경우를 생각해 원본 문자열에 남은 문자열을 미리 넣어놓는다 => 어차피 짝수일때 뒤덮여짐
            answer[length / 2] = sb.toString();

            //큐에서 빼서 하나씩 넣어줌
            for (int i = 0, j = length - 1; !q.isEmpty(); i++, j--) {
                answer[i] = q.peek();
                answer[j] = q.peek();
                q.poll();
            }

            return answer;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution("abcdef")));
    }
}
