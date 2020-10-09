package Programmers_2020_Coupang;

import java.util.*;

public class Problem1 {
    public static void main(String[] args) {
        int[] ret = solution(10);
        System.out.println(Arrays.toString(ret));
    }
    public static int[] solution(int N) {
        int[] answer = new int[2];
        int max = 1;
        int init = N;

        while(init > 10) {
            max *= init % 10;
            init /= 10;
        }
        answer[0] = 10;
        answer[1] = max;

        for(int i = 2; i < 10; i++) {
            max = 1;
            init = N;
            while(init != 0) {
                if(init % i != 0) max *= (init % i);
                init /= i;
            }
            if(answer[1] <= max) {
                answer[0] = i;
                answer[1] = max;
            }
        }
        return answer;
    }
}
