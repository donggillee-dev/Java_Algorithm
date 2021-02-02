package Programmers.Programmers_Lv2;

import java.util.*;

public class MaxiForm {
    public static void main(String[] args) {
        System.out.println(Solution.solution("100-200*300-500+20"));

        //숫자들과 3가지 연산문자가 주어짐
        //수식에 포함된 연산자의 우선순위를 조작하여 최대값을 도출해 내는 것
        //연산자 우선순위에 같은 순위의 연산자는 없음

        //나의 생각
            //연사자 3개가 모두 들어있는 경우가 아닐수도 있음
            //연산자의 개수가 3개이므로 각 연산자의 우선순위 경우의 수를 다 도출해 봐야함 => 연산자 3개 다 들어있는 경우에는 3!
                //연산자 우선순위는 Character 배열을 이용해서 swap 함수로 배열 뒤바꿔주면 됨
            //
    }
    private static class Solution {
        static long answer;
        static String str;
        static List<Character> opList = new LinkedList<>();
        static char[] existOp;
        static long solution(String expression) {
            str = expression;

            if(str.contains("+")) {
                opList.add('+');
            }
            if(str.contains("-")) {
                opList.add('-');
            }
            if(str.contains("*")) {
                opList.add('*');
            }

            existOp = new char[opList.size()];

            for(int i = 0; i < opList.size(); i++) {
                existOp[i] = opList.get(i);
            }
            DFS(0);
            return answer;
        }

        static void swap(int idx1, int idx2) {
            char tmp = existOp[idx1];
            existOp[idx1] = existOp[idx2];
            existOp[idx2] = tmp;
        }

        static void DFS(int depth) {
            if(depth >= existOp.length) {
                answer = Math.max(calc(), answer);
                return;
            }
            for(int i = depth; i < existOp.length; i++) {
                swap(i, depth);
                DFS(depth + 1);
                swap(i, depth);
            }
        }

        static long calc() {
            HashMap<String, Integer> hash = new HashMap<>();
            StringBuilder sb = new StringBuilder();
            Stack<String> s = new Stack<>();
            Stack<Long> calcS = new Stack<>();
            List<String> prefix = new LinkedList<>();

            for(int i = 0; i < existOp.length; i++) {
                hash.put(existOp[i] + "", (i + 1));
            }

            for(int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);

                if(ch == '+' || ch == '-' || ch == '*') {
                    prefix.add(String.valueOf(sb));
                    sb.delete(0, sb.length());

                    while(!s.isEmpty() && hash.get(ch + "") <= hash.get(s.peek())) {
                        prefix.add(s.pop());
                    }
                    s.push(ch + "");
                } else {
                    sb.append(ch);
                }
            }

            prefix.add(String.valueOf(sb));
            sb.delete(0, sb.length());

            while(!s.isEmpty()) {
                prefix.add(s.pop());
            }

            for(int i = 0; i < prefix.size(); i++) {
                if(prefix.get(i).equals("+") || prefix.get(i).equals("-") || prefix.get(i).equals("*")) {
                    long value2 = calcS.pop();
                    long value1 = calcS.pop();

                    if(prefix.get(i).equals("+")) {
                        calcS.push(value1 + value2);
                    } else if(prefix.get(i).equals("-")) {
                        calcS.push(value1 - value2);
                    } else {
                        calcS.push(value1 * value2);
                    }
                } else {
                    calcS.push(Long.parseLong(prefix.get(i)));
                }
            }

            return Math.abs(calcS.pop());
        }
    }
}