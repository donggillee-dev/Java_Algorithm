package Programmers_Kit_Greedy;
import java.util.*;

public class Camera {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] routes = {{-20,15}, {-14,-5}, {-18,-13}, {-5,-3}};

        System.out.println(sol.solution(routes));
    }
    private static class Solution {
        public int solution(int[][] routes) {
            int answer = 0;
            int camera = Integer.MIN_VALUE;

            Arrays.sort(routes, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if(o1[1] > o2[1]) return 1;
                    else if(o1[1] == o2[1]) return 0;
                    else return -1;
                }
            });

            for(int i = 0; i < routes.length; i++) {
                if(camera < routes[i][0]) {
                    camera = routes[i][1];
                    answer++;
                }
            }
            return answer;
        }
    }
}
