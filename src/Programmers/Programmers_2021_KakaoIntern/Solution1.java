package Programmers.Programmers_2021_KakaoIntern;

import java.util.HashMap;

public class Solution1 {
    private static class Solution {
        private static void initHash(HashMap<String, Integer> hash) {
            hash.put("zero", 0);
            hash.put("one", 1);
            hash.put("two", 2);
            hash.put("three", 3);
            hash.put("four", 4);
            hash.put("five", 5);
            hash.put("six", 6);
            hash.put("seven", 7);
            hash.put("eight", 8);
            hash.put("nine", 9);
        }
        public int solution(String s) {
            HashMap<String, Integer> hash = new HashMap<>();

            initHash(hash);

            int idx = 0;
            int length = s.length();
            char[] arr = s.toCharArray();

            StringBuilder sb = new StringBuilder();
            StringBuilder ans = new StringBuilder();
            while(idx < length) {
                if(Character.isAlphabetic(arr[idx])) {
                    if(hash.get(sb.toString()) != null) {
                        ans.append(hash.get(sb.toString()));
                        sb.setLength(0);
                    }
                    sb.append(arr[idx]);
                } else {
                    if(sb.length() != 0) {
                        ans.append(hash.get(sb.toString()));
                        sb.setLength(0);
                    }
                    ans.append(arr[idx]);
                }
                idx++;
            }

            if(sb.length() != 0) {
                ans.append(hash.get(sb.toString()));
            }
            return Integer.parseInt(ans.toString());
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "one4seveneight";
        System.out.println(sol.solution(s));
    }
}