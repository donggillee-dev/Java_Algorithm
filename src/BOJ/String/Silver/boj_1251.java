package BOJ.String.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

//Logic
//단순히 조합 + 브루트포스 + 문자열 문제이다
//다만 막혔던 부분은
    //1. 굳이 재귀로 풀려고 했던것과
    //2. StringBuilder에 대해서 reverse해버리면 reverse된 값이 자체에 덮어씌워진다는 것!!
//풀이 시간 : 25분

public class boj_1251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int length = str.length();

        PriorityQueue<String> pq = new PriorityQueue<>();

        StringBuilder sb1 = new StringBuilder();
        for(int i = 0; i < length - 2; i++) {
            sb1.append(str.charAt(i));
            StringBuilder sb2 = new StringBuilder();
            for(int j = i + 1; j < length - 1; j++) {
                sb2.append(str.charAt(j));
                StringBuilder sb3 = new StringBuilder();
                for(int k = j + 1; k < length; k++) {
                    sb3.append(str.charAt(k));
                }
                pq.add(sb1.reverse().toString() + sb2.reverse() + sb3.reverse());
                sb1.reverse();
                sb2.reverse();
            }
        }
        System.out.println(pq.poll());
    }
}
