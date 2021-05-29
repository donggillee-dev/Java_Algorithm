package AutoEver2021;

import java.util.*;

public class GCD {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{10, 20, 15, 30}));
//        System.out.println(solution(new int[]{2, 3, 5}));
    }
    private static int gcd(int a, int b) {
        while(b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }

        return a;
    }
    private static int solution(int[] nums) {
        int length = nums.length;
        Integer[] arr = new Integer[length];
        for(int i = 0; i < length; i++) {
            arr[i] = nums[i];
        }

        Arrays.sort(arr, Collections.reverseOrder());

        int answer = gcd(arr[0], arr[1]);

        System.out.println(answer);
        for(int i = 2; i < length; i++) {
            if(answer == 1) break;
            answer = gcd(answer, nums[i]);
        }

        return answer;
    }
}
