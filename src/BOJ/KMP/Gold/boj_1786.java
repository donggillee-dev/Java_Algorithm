package BOJ.KMP.Gold;

import java.io.*;

//Logic
//KMP 알고리즘의 가장 대표적인 문제 , 그냥 KMP를 구현하면 된다 그리고 패턴과 동일한 문자열이 존재하는 경우에는
//해당 부모의 위치를 StringBuilder에 넣어주고 패턴 찾은 횟수를 증가하여 정답을 도출해낸다.
//KMP 알고리즘을 제대로 이해했으면 바로 풀 수 있는 문제... 알고리즘을 이해하기까지가 좀 걸림
//풀이 시간 : 60분

public class boj_1786 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder answer = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        char[] parent = br.readLine().toCharArray();
        char[] pattern = br.readLine().toCharArray();
        int[] table = makeTable(pattern);
        int cnt = KMP(parent, pattern, table, sb);
        answer.append(cnt).append("\n");
        answer.append(sb);
        bw.write(answer.toString());
        bw.close();
        br.close();

    }
    private static int KMP(char[] parent, char[] pattern, int[] table, StringBuilder sb) {
        int cnt = 0, j = 0, parentSize = parent.length, patternSize = pattern.length;

        for(int i = 0; i < parentSize; i++) {
            while(j > 0 && pattern[j] != parent[i]) {
                j = table[j - 1];
            }
            if(pattern[j] == parent[i]) {
                if(j == patternSize - 1) {
                    cnt++;
                    sb.append(i + 1 - (patternSize - 1)).append(" ");
                    j = table[j];
                } else {
                    j++;
                }
            }
        }
        return cnt;
    }
    private static int[] makeTable(char[] pattern) {
        int patternSize = pattern.length;
        int[] result = new int[patternSize];
        int j = 0;

        for(int i = 1; i < patternSize; i++) {
            while(j > 0 && pattern[j] != pattern[i]) {
                j = result[j - 1];
            }
            if(pattern[j] == pattern[i]) {
                result[i] = ++j;
            }
        }

        return result;
    }
}
