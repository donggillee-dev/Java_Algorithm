package BOJ_DP;
import java.io.*;
import java.util.*;

public class algo_9251 {//LCS 알고리즘
    static String str1;
    static String str2;
    static int ans = Integer.MIN_VALUE;
    static int[][] DP;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        str1 = new String(br.readLine());
        str2 = new String(br.readLine());

        DP = new int[str1.length() + 1][str2.length() + 1];
        Arrays.fill(DP[0], 0);
        for(int i = 0; i <= str1.length(); i++) {
            DP[i][0] = 0;
        }

        for(int i = 0; i < str1.length(); i++) {//시간복잡도 -> O(str1.length() * str2.length())
            for(int j = 0; j < str2.length(); j++) {
                if(str1.charAt(i) == str2.charAt(j)) { //문자가 같을경우 대각선 뒤의 값 + 1
                    DP[i + 1][j + 1] = DP[i][j] + 1;
                } else { //문자가 다를경우 이전값과 이전 비교 문자열의 값중 최대값 가져와서 넣어줌
                    DP[i + 1][j + 1] = Math.max(DP[i][j + 1], DP[i + 1][j]);
                }
            }
        }
        for(int i = 0; i < str2.length(); i++) {
            if(ans < DP[str1.length()][i + 1]) {
                ans = DP[str1.length()][i + 1];
            }
        }

        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}
