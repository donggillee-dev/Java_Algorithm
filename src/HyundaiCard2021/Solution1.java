package HyundaiCard2021;

import java.util.*;

public class Solution1 {
    public static void main(String[] args) {
        System.out.println((solution(new String[]{"RG", "WR", "BW", "GG"}, new int[]{2000, 6000})));
    }
    private static int solution(String[] color, int[] prices) {
        HashMap<Character, Integer> top = new HashMap<>();
        HashMap<Character, Integer> bottom = new HashMap<>();
        for(int i = 0 ; i < color.length; i++) {
            char topColor = color[i].charAt(0);
            char bottomColor = color[i].charAt(1);
            top.put(topColor, top.getOrDefault(topColor, 0) + 1);
            bottom.put(bottomColor, bottom.getOrDefault(bottomColor, 0) + 1);
        }

        int answer = 0;
        // 같은거 처리
        for(Map.Entry<Character, Integer> entry: top.entrySet()) {
            int num = Math.min(entry.getValue(), bottom.getOrDefault(entry.getKey(), 0));
            answer += num * prices[0];
            if(num != 0) {
                top.put(entry.getKey(), top.get(entry.getKey()) - num);
                bottom.put(entry.getKey(), bottom.get(entry.getKey()) - num);
            }
        }
        // 나머지 처리
        for(Map.Entry<Character, Integer> entry: top.entrySet()) {
            int num = entry.getValue();
            answer += num * Math.min(prices[0] * 2, prices[1]);
        }
        return answer;
    }
}
