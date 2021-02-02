package Programmers.Programmers_Kit_Hash;

import java.util.*;

public class Hide {
    public static void main(String[] args) {
        String[][] clothes = new String[4][4];
        Solution sol = new Solution();
        int answer = sol.solution(clothes);

        System.out.println(answer);
        return;
    }
}

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String, Integer> hash = new HashMap<String, Integer>();

        for(int i = 0; i < clothes.length; i++) {
            hash.put(clothes[i][1], hash.getOrDefault(clothes[i][1], 0) + 1);
        }
        for(String e: hash.keySet()) {
            answer *= (hash.get(e) + 1);
        }
        return answer - 1;
    }
}
