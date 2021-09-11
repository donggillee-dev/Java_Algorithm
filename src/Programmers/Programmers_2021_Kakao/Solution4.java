package Programmers.Programmers_2021_Kakao;

import java.util.Arrays;

public class Solution4 {
    private static int diff = 0;
    private static int[] ansArr = new int[11];
    private static int[] lionArr = new int[11];
    private static void calcScore(int[] apeachArr) {
        int apeachScore = 0;
        int lionScore = 0;

        for(int i = 0; i <= 10; i++) {
            if(apeachArr[i] == 0 && lionArr[i] == 0) continue;

            if(apeachArr[i] >= lionArr[i]) {
                apeachScore += (10 - i);
            } else {
                lionScore += (10 - i);
            }
        }

        if((lionScore - apeachScore) != 0 && diff <= lionScore - apeachScore) {
            diff = lionScore - apeachScore;
            int originSum = 0, modiSum = 0;

            for(int i = 0; i <= 10; i++) {
                originSum += ansArr[i] * (i + 1);
            }

            for(int i = 0; i <= 10; i++) {
                modiSum += lionArr[i] * (i + 1);
            }

            if(originSum < modiSum) {
                for(int i = 0; i <= 10; i++) {
                    ansArr[i] = lionArr[i];
                }
            }
        }
    }
    private static void dfs(int[] apeach, int depth, int cnt) {
        if(depth == 11) {
            calcScore(apeach);
            return;
        }

        for(int i = cnt; i >= 0; i--) {
            lionArr[depth] = i;
            dfs(apeach, depth + 1, cnt - i);
        }
    }
    private static int[] solution(int n, int[] info) {
        dfs(info, 0, n);

        if(diff == 0) {
            return new int[]{-1};
        } else {
            return ansArr;
        }
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(5, new int[]{2,1,1,1,0,0,0,0,0,0,0})));
////        System.out.println(Arrays.toString(solution(1, new int[]{1,0,0,0,0,0,0,0,0,0,0})));
////        System.out.println(Arrays.toString(solution(9, new int[]{0,0,1,2,0,1,1,1,1,1,1})));
////        System.out.println(Arrays.toString(solution(10, new int[]{0,0,0,0,0,0,0,0,3,4,3})));
//        System.out.println(Arrays.toString(solution(1, new int[]{0,0,0,0,0,0,0,0,0,0,1})));
    }
}
