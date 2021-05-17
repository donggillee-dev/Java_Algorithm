package BOJ.String.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
//Logic
//dfs로 해경한 문제
//기저조건과 hashmap을 이용해서 문자열 존재여부를 체크하여 dfs 탐색 필요여부를 거른다
//추후에 dfs돌떄 이미 만들 수 있는 문자열로 판별 나 있으면 더이상 들어갈 필요 없음

//풀이 시간 : 120분
public class boj_9177 {
    private static boolean p;
    private static String s1, s2, s3;
    private static int l1, l2, l3;
    private static boolean[] v1, v2;

    private static boolean check() {
        HashMap<Character, Integer> hash = new HashMap<>();

        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();

        for(char ch : ch1) {
            hash.put(ch, hash.getOrDefault(ch, 0));
        }

        for(char ch : ch2) {
            hash.put(ch, hash.getOrDefault(ch, 0));
        }

        char[] ch3 = s3.toCharArray();

        for(char ch : ch3) {
            if(hash.get(ch) == null) return false;
        }

        return true;
    }
    private static void dfs(int idx1, int idx2, int idx3) {
        if(p) return;

        if (idx3 == l3) {
            p = true;
            return;
        }
        if (idx1 < l1 && s1.charAt(idx1) == s3.charAt(idx3) && !v1[idx1]) {
            v1[idx1] = true;
            dfs(idx1 + 1, idx2, idx3 + 1);
            v1[idx1] = false;
        }


        if (idx2 < l2 && s2.charAt(idx2) == s3.charAt(idx3) && !v2[idx2]) {
            v2[idx2] = true;
            dfs(idx1, idx2 + 1, idx3 + 1);
            v2[idx2] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;
        int n = Integer.parseInt(br.readLine());

        for (int i = 1; i <= n; i++) {
            sb.append("Data set ").append(i);
            stk = new StringTokenizer(br.readLine());
            p = false;
            s1 = stk.nextToken();
            s2 = stk.nextToken();
            s3 = stk.nextToken();
            l1 = s1.length();
            l2 = s2.length();
            l3 = s3.length();
            v1 = new boolean[l1];
            v2 = new boolean[l2];

            if(!check()) {
                sb.append(": no").append("\n");
                continue;
            }

            dfs(0, 0, 0);

            if (p) {
                sb.append(": yes");
            } else {
                sb.append(": no");
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }
}
