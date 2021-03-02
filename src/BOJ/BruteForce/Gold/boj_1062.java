package BOJ.BruteForce.Gold;

import java.io.*;
import java.util.StringTokenizer;
//Logic
//Brute-Force 알고리즘으로 해결하면 되는 문제...
//각 단어를 하나씩 픽업해보고 그걸로 단어를 가르칠 수 있는지를 확인
//풀이 시간 : 40분
public class boj_1062 {
    private static int answer = 0;
    private static String[] words;
    private static char[] chArr = new char[26];
    private static boolean[] isExist = new boolean[26];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int defaultMask = 0, N = Integer.parseInt(stk.nextToken()), K = Integer.parseInt(stk.nextToken()) - 5;
        words = new String[N];
        int myWord = 0;

        if(K >= 0) {
            for(int i = 0; i < chArr.length; i++) {
                chArr[i] = (char)('a' + i);
                if(chArr[i] == 'a' || chArr[i] == 'c' || chArr[i] == 'i' || chArr[i] == 'n' || chArr[i] == 't') {
                    defaultMask |= (1 << (chArr[i] - 'a'));
                    isExist[chArr[i] - 'a'] = true;
                    myWord++;
                }
            }
            for(int i = 0; i < N; i++) {
                words[i] = br.readLine();
                for(int j = 4; j < words[i].length() - 4; j++) {
                    if(!isExist[words[i].charAt(j) - 'a']) {
                        isExist[words[i].charAt(j) - 'a'] = true;
                        myWord++;
                    }
                }
            }
            bruteForce(defaultMask, 0, K, 0, myWord);
        } else if(K == 26) answer = N;
        sb.append(answer);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
    private static void bruteForce(int mask, int cnt, int K, int idx, int myWord) {
        if(cnt == K || myWord == cnt + 5) {
            int possible = 0;

            for(int i = 0; i < words.length; i++) {
                boolean flag = true;
                for(int j = 4; j < words[i].length() - 4; j++) {
                    char ch = words[i].charAt(j);

                    if((mask & (1 << (ch - 'a'))) == 0) {
                        flag = false;
                        break;
                    }
                }
                if(flag) possible++;
            }

            if(possible > answer) answer = possible;
            return;
        }

        for(int i = idx; i < 26; i++) {
            if(isExist[i] && (mask & (1 << (chArr[i] - 'a'))) == 0) {
                bruteForce(mask | (1 <<(chArr[i] - 'a')), cnt + 1, K, i + 1, myWord);
            }
        }
    }
}
