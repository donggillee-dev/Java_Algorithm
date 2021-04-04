package Programmers.Programmers_2021_KakaoCommerce;

import java.util.HashMap;

public class Solution2 {
    public static void main(String[] args) {
        Solution2 sol = new Solution2();
        int[][] arr = {
                {1,0,0},
                {1,1,0},
                {1,1,0}, {1,0,1}, {1,1,0}, {0,1,1}
        };
        System.out.println(sol.solution(arr, 2));
    }
    private int solution(int[][] needs, int r) {
        int answer = 0;
        int row = needs.length;
        int col = needs[0].length;
        HashMap<Integer, Integer> hash = new HashMap<>();

        for(int i = 0; i < row; i++) {
            int key = 0;
            int needsRobot = 0;
            for(int j = 0; j < col; j++) {
                if(needs[i][j] == 1) {
                    needsRobot++;
                    key |= (1 << j);
                }
            }
            if(needsRobot <= r) {
                hash.put(key, hash.getOrDefault(key, 0) + 1);
            }
        }

        for(int key : hash.keySet()) {
            int elem = hash.get(key);
            if(answer <= elem) {
                if(key % 2 == 1) {
                    answer = Math.max(answer, elem + hash.get(1));
                } else {
                    answer = elem;
                }
            }
        }

        return answer;
    }
}
