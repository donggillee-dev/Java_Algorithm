package BOJ.TwoPointer.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//해독하려는 문자열에 대해서 패턴 문자열의 길이만큰 계속 슬라이딩 윈도우 해가면 되는 문제이다
//시간이 오래걸린 이유는 슬라이딩 윈도우 안에 들이있는 문자들 존재여부 배열을 가지고 원본과 패턴을 비교할때
//각 비교 문자열 안에 존재하는 문자의 갯수를 생각 안해줬음... 단순히 존재하냐 안하냐만 봐서는 안됨.

//풀이 시간 : 45분
public class boj_1593 {
    private static boolean isPoss(int[] tar, int[] ori) {
        for(int i = 0; i < 60; i++) {
            if(tar[i] != ori[i]) return false;
        }

        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        int[] ori = new int[60];
        int[] tar = new int[60];
        int g = Integer.parseInt(stk.nextToken());
        int s = Integer.parseInt(stk.nextToken());

        String str1 = br.readLine();
        String str2 = br.readLine();

        for(int i = 0; i < g; i++) {
            char ch = str1.charAt(i);
            ori[ch - 'A']++;
        }

        for(int i = 0; i < g; i++) {
            char ch = str2.charAt(i);
            tar[ch - 'A']++;
        }

        int ans = 0;

        if(isPoss(tar, ori)) ans++;

        for(int start = 0, end = g; end < s; start++, end++) {
            char ch = str2.charAt(start);
            tar[ch - 'A']--;

            ch = str2.charAt(end);
            tar[ch - 'A']++;

            if(isPoss(tar, ori)) ans++;
        }

        System.out.println(ans);
    }
}
