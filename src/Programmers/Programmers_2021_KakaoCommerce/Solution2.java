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


//public class Solution2 {
//    static int row, col, answer;
//
//    public static int solution(int[][] needs, int r) {
//        answer = 0;
//        row = needs.length;
//        col = needs[0].length;
//        dfs(0, 0, new boolean[col], r, needs);
//        return answer;
//    }
//
//    private static void dfs(int cnt, int idx, boolean[] canUse, int r, int[][] needs) {
//        if (cnt == r) {
//            int count = 0;
//            for (int x = 0; x < needs.length; x++) {
//                boolean canMake = true;
//                for (int y = 0; y < needs[x].length && canMake; y++) {
//                    if (needs[x][y] == 1 && !canUse[y]) {
//                        canMake = false;
//                    }
//                }
//                if (canMake)
//                    count++;
//            }
//            answer = Math.max(answer, count);
//            return;
//        }
//        for (int i = idx; i < col; i++) {
//            canUse[i] = true;
//            dfs(cnt + 1, i + 1, canUse, r, needs);
//            canUse[i] = false;
//        }
//    }
//
//    public static void main(String[] args) {
//        int[][] needs = { { 1, 0, 0 }, { 1, 1, 0 }, { 1, 1, 0 }, { 1, 0 }, { 1, 1, 0 }, { 0, 1, 1 } };
//        int r = 3;
//        System.out.println(solution(needs, r));
//    }
//}
