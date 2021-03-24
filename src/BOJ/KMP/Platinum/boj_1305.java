package BOJ.KMP.Platinum;

import java.io.*;
//Logic
//KMP를 이용하여 가장 긴 접미사의 길이를 빼주면 안보인 부분 포함 현재 광고판의 길이에서 가장 긴 문자열을 뽑아낼 수 있음
//풀이 시간 : 10분

public class boj_1305 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String pattern = br.readLine();
        int[] table = new int[N];

        for(int i = 1, j = 0; i < N; i++) {
            while(j > 0 && pattern.charAt(j) != pattern.charAt(i)) {
                j = table[j - 1];
            }
            if(pattern.charAt(j) == pattern.charAt(i)) {
                j++;
                table[i] = j;
            }
        }
        System.out.println(N - table[N - 1]);
    }
}
