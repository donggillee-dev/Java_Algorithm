package Programmers_2018_kakao;
import java.io.*;
import java.util.*;

public class SecretMap {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        Solution S = new Solution();

        int n = Integer.parseInt(br.readLine());
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];
        StringTokenizer stk1 = new StringTokenizer(br.readLine(), ", ");
        StringTokenizer stk2 = new StringTokenizer(br.readLine(), ", ");

        for(int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(stk1.nextToken());
        }

        for(int i = 0; i < n; i++) {
            arr2[i] = Integer.parseInt(stk2.nextToken());
        }

        sb.append(Arrays.toString(S.solution(n, arr1, arr2)));
        bw.write(String.valueOf(sb));

        bw.flush();
        bw.close();
        br.close();
        return;
    }
}

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        String res;
        for(int i = 0; i < n; i++) {
            res = String.format("%" + n + "s", Integer.toBinaryString(arr1[i] | arr2[i]));
            answer[i] = res;
        }

        for(int i = 0; i < n; i++) {
            answer[i] = answer[i].replaceAll("1", "#");
            answer[i] = answer[i].replaceAll("0", " ");
        }
        return answer;
    }
}