package Programmers.Programmers_2021_Kakao;

import java.util.TreeSet;

public class Solution2 {
    private static String transfer(int n, int k) {
        StringBuilder sb = new StringBuilder();

        while(n > 0) {
            sb.insert(0, n % k);
            n /= k;
        }

        return sb.toString();
    }
    private static boolean isPrime(long num, TreeSet<Integer> primeHash) {
        if(num == 1) return false;

        for(int prime : primeHash) {
            if(prime >= num) break;
            if(num % prime == 0) {
                return false;
            }
        }

        return true;
    }
    private static TreeSet<Integer> makePrime() {
        int n = 10000;
        boolean[] prime = new boolean[n + 1];
        TreeSet<Integer> primeHash = new TreeSet<>();

        prime[0] = prime[1] = true;

        for(int i = 2; i * i <= n; i++) {
            if(!prime[i]) {
                for(int j = i * i; j <= n; j += i) {
                    prime[j] = true;
                }
            }
        }

        for(int i = 1; i <= n; i++) {
            if(!prime[i]) {
                primeHash.add(i);
            }
        }

        return primeHash;
    }
    private static int solution(int n, int k) {
        int answer = 0;
        String str = transfer(n, k);
        int length = str.length();
        TreeSet<Integer> primeHash = makePrime();

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < length; i++) {
            if(str.charAt(i) == '0' && sb.length() != 0) {
                long num = Long.parseLong(sb.toString());

                if(isPrime(num, primeHash)) answer++;

                sb.setLength(0);
            } else if(str.charAt(i) != '0'){
                sb.append(str.charAt(i));

                if(i == length - 1) {
                    long num = Long.parseLong(sb.toString());
                    if(isPrime(num, primeHash)) answer++;
                }
            }
        }

        return answer;
    }
    public static void main(String[] args) {
        System.out.println(solution(1, 7));
    }
}
