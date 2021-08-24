package Programmers.Programmers_2021_KakaoIntern;

import java.io.IOException;
import java.util.HashMap;

public class Solution1 {
    private static HashMap<String, Integer> hash = new HashMap<>();
    private static void initHash() {
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
    private static int solution(String s) {
        StringBuilder sb = new StringBuilder();
        int length = s.length();
        int ans = 0;

        for(int i = 0; i < length; i++) {
            if(hash.get(sb.toString()) != null) {
                ans *= 10;
                ans += hash.get(sb.toString());
                sb.setLength(0);
            }
            if(Character.isDigit(s.charAt(i))) {
                ans *= 10;
                ans += s.charAt(i) - '0';
            } else if (Character.isAlphabetic(s.charAt(i))) {
                sb.append(s.charAt(i));
            }
        }

        if(sb.length() != 0) {
            ans *= 10;
            ans += hash.get(sb.toString());
        }
        return ans;
    }
    public static void main(String[] args) throws IOException {
        String[] arr = new String[]{
                "one4seveneight",
                "23four5six7",
                "1zerotwozero3"
        };
        int length = arr.length;



        for(int i = 0; i < length; i++) {
            System.out.println(solution(arr[i]));
        }
    }
}