package Programmers.Programmers_Kit_Sort;
import java.util.*;

public class BiggestNumber {
    public static void main(String[] args) {
        int[] numbers = {3, 30, 34, 5, 9};
        Solution sol = new Solution();
        System.out.println(sol.solution(numbers));
    }
    private static class Solution {
        public String solution(int[] numbers) {
            String answer = "";
            String[] result = new String[numbers.length];
            for(int i = 0; i < numbers.length; i++) {
                result[i] = String.valueOf(numbers[i]);
            }
            Arrays.sort(result, new Comparator<String>(){
                @Override
                public int compare(String o1, String o2) {
                    return (o2+o1).compareTo(o1+o2);
                }
            });
            if(result[0].equals("0")) answer = "0";
            else {
                for(String a : result) {
                    answer += a;
                }
            }

            return answer;
        }
    }
}
