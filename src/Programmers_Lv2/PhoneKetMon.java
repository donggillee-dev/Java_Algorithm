package Programmers_Lv2;
import java.util.*;

public class PhoneKetMon {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {3,3,3,2,2,4};
        System.out.println(sol.solution(nums));

        return;
    }
    private static class Solution {
        Queue<Integer> Q = new LinkedList<>();
        public int solution(int[] nums) {
            int answer = 0;
            int[] arr;

            for(int i = 0; i < nums.length; i++) {
                if(Q.isEmpty()) Q.offer(nums[i]);
                else {
                    if(!Q.contains(nums[i]))
                        Q.offer(nums[i]);
                }
            }

            arr = new int[Q.size()];
            int idx = 0;
            while(!Q.isEmpty()) {
                arr[idx] = Q.poll();
                idx++;
            }

            if(arr.length > nums.length / 2) {
                answer = nums.length / 2;
            } else
                answer = arr.length;
            return answer;
        }
    }
}
