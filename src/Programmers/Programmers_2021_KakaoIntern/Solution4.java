package Programmers.Programmers_2021_KakaoIntern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution4 {
    public static void main(String[] args) {
//        solution(3, 1, 3, new int[][]{{1, 2, 2}, {3,2,3}}, new int[]{2});
        System.out.println(solution(4, 1, 4, new int[][]{{1, 2, 1}, {3, 2, 1}, {2, 4, 1}}, new int[]{2, 3}));
    }

    private static class Info implements Comparable<Info> {
        int node;
        int dist;

        public Info(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Info o) {
            return this.dist - o.dist;
        }
    }

    private static int solution(int n, int start, int end, int[][] roads, int[] traps) {
        return 0;
    }
}
