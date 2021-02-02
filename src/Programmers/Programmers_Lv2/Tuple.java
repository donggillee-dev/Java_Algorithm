package Programmers.Programmers_Lv2;
import java.util.*;

public class Tuple {
    public static void main(String[] args) {
        int[] answer;
        String s = "{{4,2,3},{3},{2,3,4,1},{2,3}}";
        Solution sol = new Solution();
        answer = sol.solution(s);

        System.out.println(Arrays.toString(answer));
        return;
    }
    private static class  Solution {
        public int[] solution(String s) {
            int[] answer = {};
            //중복값 존재 가능
            //동일한 원소로 구성되어도 순서가 다르면 다른 튜플
            //튜플의 원소 개수는 유한하다
            String[] arr = s.split("\\},\\{");
            Queue<Integer> Q = new LinkedList<>();

            for (int i = 0; i < arr.length; i++) {
                arr[i] = arr[i].replaceAll("\\{|\\}", "");
            }
            Arrays.sort(arr, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.length() - o2.length();
                }
            });
            for (int i = 0; i < arr.length; i++) {
                String tmp = arr[i];
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < tmp.length(); j++) {
                    if (tmp.charAt(j) - '0' >= 0) {
                        sb.append(tmp.charAt(j));
                        if (j == tmp.length() - 1) {
                            int num = Integer.parseInt(String.valueOf(sb));
                            sb.delete(0, sb.length());
                            if (!Q.contains(num)) Q.offer(num);
                        }
                    } else {
                        int num = Integer.parseInt(String.valueOf(sb));
                        sb.delete(0, sb.length());
                        if (!Q.contains(num)) Q.offer(num);
                    }
                }
            }
            answer = new int[Q.size()];
            int idx = 0;
            while (!Q.isEmpty()) {
                answer[idx] = Q.poll();
                idx++;
            }
            return answer;
        }
    }
}
