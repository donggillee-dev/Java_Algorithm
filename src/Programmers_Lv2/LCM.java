package Programmers_Lv2;

import java.util.ArrayList;

public class LCM {
    public static void main(String[] args) {
        System.out.println(Solution.solution(new int[]{2, 6, 8, 14}));
    }
    private static class Solution {
        private static int solution(int[] arr) {
            int answer = 1;
            ArrayList<Integer> primeList = eratosThenes(100);

            for(int primeIdx = 0; primeIdx < primeList.size(); primeIdx++) {
                int prime = primeList.get(primeIdx);
                int divCnt = 0;

                for(int i = 0; i < arr.length; i++) {
                    if(arr[i] % prime == 0) divCnt++;
                }

                if(divCnt >= 2) {
                    answer *= prime;
                    for(int i = 0; i < arr.length; i++) {
                        if(arr[i] % prime == 0) {
                            arr[i] /= prime;
                        }
                    }
                    primeIdx--;
                }
            }

            for(int elem : arr) {
                answer *= elem;
            }
            return answer;
        }
        private static ArrayList<Integer> eratosThenes(int end) {
            boolean[] sieve = new boolean[end + 1];
            ArrayList<Integer> primeList = new ArrayList<>();

            for(int i = 2; i < sieve.length; i++) {
                int j = 2;
                while(i * j < sieve.length) {
                    sieve[i * j] = true;
                    j++;
                }
            }
            for(int i = 2; i < sieve.length; i++) {
                if(!sieve[i]) {
                    primeList.add(i);
                }
            }

            return primeList;
        }
    }
}
