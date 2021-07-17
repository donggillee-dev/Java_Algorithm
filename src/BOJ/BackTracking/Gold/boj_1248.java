package BOJ.BackTracking.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Logic
//백트레킹 문제 좀 더 시간을 줄일 수 있을거같지만... 포기
//단순히 숫자 다 넣어보면서 하나씩 검증해서 백트레킹 => depth가 n일떄 탈출
//풀이 시간 : 20분

public class boj_1248 {
    private static boolean finish;
    private static int n;
    private static char[][] arr;
    private static boolean isPossible(int start, int end, int[] ans) {
        int sum = 0;
        boolean ret = false;
        for(int i = start; i <= end; i++) {
            sum += ans[i];
        }

        switch (arr[start][end]) {
            case '+':
                if(sum > 0) ret = true;
                break;
            case '-':
                if(sum < 0) ret = true;
                break;
            case '0':
                if(sum == 0) ret = true;
                break;
        }

        return ret;
    }
    private static void solution(int depth, int[] ans) {
        for(int i = 0; i < depth; i++) {
            for(int j = i; j < depth; j++) {
                if(!isPossible(i, j, ans)) {
                    return;
                }
            }
        }

        if(depth == n) {
            finish = true;
        }

        if(finish)
            return;

        for(int i = -10; i <= 10; i++) {
            ans[depth] = i;
            solution(depth + 1, ans);
            if(finish) return;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        arr = new char[n][n];
        String line = br.readLine();
        int[] ans = new int[n];

        for(int i = 0, idx = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                arr[i][j] = line.charAt(idx++);
            }
        }

        solution(0, ans);
        for(int i = 0; i < n; i++) {
            sb.append(ans[i]).append(" ");
        }

        System.out.print(sb);
    }
}
