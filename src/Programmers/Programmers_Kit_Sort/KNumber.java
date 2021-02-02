package Programmers.Programmers_Kit_Sort;
import java.util.*;

public class KNumber {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};

        System.out.println(Arrays.toString(sol.solution(array, commands)));
    }
    private static class Solution {
        public int[] solution(int[] array, int[][] commands) {
            int[] answer = new int[commands.length];
            int cnt = 0;
            for(int[] command : commands) {
                int start = command[0];
                int end = command[1];
                int target = command[2];
                List<Integer> sliced = new ArrayList<>();


                for(int i = start - 1; i <= end - 1; i++) {
                    sliced.add(array[i]);
                }
                Collections.sort(sliced, new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1 - o2;
                    }
                });


                answer[cnt] = sliced.get(target - 1);
                cnt++;
            }
            return answer;
        }
    }
}
