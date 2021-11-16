package BOJ.TwoPointer.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

//Logic
//투 포인터 + dfs 로 풀 수 있는 문제
//시간 복잡도 N * 2^(N - 1)인데
//문자열 N의 최대길이가 8이므로 시간복잡도 초과 안함, String에 의한 메모리 낭비는 좀 심할지도...?

public class boj_17255 {
    private static String str;
    private static int length;
    private static HashSet<String> set = new HashSet<>();

    private static void dfs(int leftIdx, int rightIdx, String cur, String full) {
        if(leftIdx <= 0 && rightIdx >= length - 1) {
            set.add(full);
            return;
        }

        if(leftIdx > 0) {
            String next = str.charAt(leftIdx - 1) + cur;
            dfs(leftIdx - 1, rightIdx, next, full + "->" + next);
        }

        if(rightIdx < length - 1) {
            String next = cur + str.charAt(rightIdx + 1);
            dfs(leftIdx, rightIdx + 1, next, full + "->" + next);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        length = str.length();

        for(int i = 0; i < length; i++) {
            dfs(i, i, "" + str.charAt(i), "" + str.charAt(i));
        }

        System.out.println(set.size());
    }
}
