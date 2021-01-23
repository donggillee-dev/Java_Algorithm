package Programmers_Lv2;

import java.util.*;

public class MulMatrix {
    public static void main(String[] args) {
        int[][] arr1 = {{1,4}, {3,2}, {4,1}};
        int[][] arr2 = {{3, 3}, {3, 3}};

        int[][] result = Solution.solution(arr1, arr2);

        System.out.println(Arrays.toString(result));
    }
    private static class Solution {
        static int[][] solution(int[][] arr1, int[][] arr2) {
            int[][] result = new int[arr1.length][arr2[0].length];

            for(int i = 0; i < result.length; i++) {
                for(int j = 0; j < result[i].length; j++) {
                    for(int k = 0; k < arr1[i].length; k++) {
                        result[i][j] += (arr1[i][k] * arr2[k][j]);
                        System.out.println(result[i][j]);
                    }
                }
            }

            return result;
        }
    }
}