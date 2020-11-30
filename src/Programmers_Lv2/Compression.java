package Programmers_Lv2;
import java.util.*;

public class Compression {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String msg = "KAKAO";
        int[] answer = sol.solution(msg);
        System.out.println(Arrays.toString(answer));
//
//        System.out.println((char)65);
        return;
    }
    private static class Solution {
        public int[] solution(String msg) {
            int[] answer;
            int end = 26;
            Queue<Integer> Q = new LinkedList<>();
            HashMap<String, Integer> H = new HashMap<>();
            StringBuilder sb = new StringBuilder();

            for(int i = 1; i <= end; i++) {
                sb.append((char)(i + 64));
                H.put(String.valueOf(sb), i);
                sb.delete(0, sb.length());
            }

            for(int i = 0; i < msg.length(); i++) {
                for(int j = i; j < msg.length(); j++) {
                    sb.append(msg.charAt(j));
                    if(!H.containsKey(String.valueOf(sb))) {
                        end++;
                        H.put(String.valueOf(sb), end);
                        sb.delete(sb.length() - 1, sb.length());
                        Q.offer(H.get(String.valueOf(sb)));
                        i = j - 1;
                        break;
                    } else {
                        if(j == msg.length() - 1) {
                            Q.offer(H.get(String.valueOf(sb)));
                            i = j;
                            break;
                        }
                    }
                }
                sb.delete(0, sb.length());
            }

            answer = new int[Q.size()];
            int idx = 0;

            while(!Q.isEmpty()) {
                answer[idx] = Q.poll();
                idx++;
            }
            Q.clear();
            return answer;
        }
    }
}
