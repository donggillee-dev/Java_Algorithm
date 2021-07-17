package BOJ.LCS.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Logic
//LCS의 로직은 두 문자열의 동일한 인덱스에 대해 두 문자가 같은지 체크
    //같다면 둘 다 이전의 값에 + 1
    //다르다면 각 문자열의 하나씩 뒤를 탐색해보아 그 중 가장 큰거로
//2차원에서 3차원으로 바뀌고, 세 문자열의 문자가 같지 않을때 각 하나씩 뒤를 보아 가장 큰거 뽑아감
//확신이 없어서 답을 봤다

//풀이 시간 : 40분
public class boj_1958 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();
        String str3 = br.readLine();
        int length1 = str1.length();
        int length2 = str2.length();
        int length3 = str3.length();

        int[][][] dp = new int[length1 + 1][length2 + 1][length3 + 1];
        for(int i = 0; i < length1; i++) {
            for(int j = 0; j < length2; j++) {
                for(int k = 0; k < length3; k++) {
                    if(str1.charAt(i) == str2.charAt(j) && str2.charAt(j) == str3.charAt(k)) {
                        dp[i + 1][j + 1][k + 1] = dp[i][j][k] + 1;
                    } else {
                        dp[i + 1][j + 1][k + 1] = Math.max(dp[i][j + 1][k + 1], Math.max(dp[i + 1][j][k + 1], dp[i + 1][j + 1][k]));
                    }
                }
            }
        }

        System.out.println(dp[length1][length2][length3]);
    }
}
