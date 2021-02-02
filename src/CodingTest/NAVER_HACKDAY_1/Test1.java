package CodingTest.NAVER_HACKDAY_1;

import java.io.*;
import java.util.*;

public class Test1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        Solution1 sol = new Solution1();



        bw.flush();
        bw.close();
        br.close();
        return;
    }
}

class Solution1 {
    public String solution(int n, int[][] delivery) {
        String answer = "";
        int[] able = new int[n + 1];

        Arrays.fill(able, 0);

        for(int j = 0; j < 2; j++) {
            for(int i = 0; i < delivery.length; i++) {
                if(delivery[i][2] == 1) {
                    able[delivery[i][0]] = 1;
                    able[delivery[i][1]] = 1;
                } else if(delivery[i][2] == 0) {
                    if(able[delivery[i][0]] == 1 || able[delivery[i][1]] == 1) {
                        if(able[delivery[i][0]] == 1) {
                            able[delivery[i][1]] = -1;
                        } else {
                            able[delivery[i][0]] = -1;
                        }
                    }
                } else continue;
            }
        }


        for(int i = 1; i <= n; i++) {
            if(able[i] == 1) {
                answer += "O";
            } else if(able[i] == -1) {
                answer += "X";
            } else {
                answer += "?";
            }
        }
        return answer;
    }
}