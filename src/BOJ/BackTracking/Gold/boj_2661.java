package BOJ.BackTracking.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Logic
//하나씩 넣어가서 만들면서 이때까지 만든 수열의 절반 길이만큼 계속 검증해나가서 백트레킹 수행
//백트레킹 로직 짜는데 오래걸림

//풀이 시간 : 34분

public class boj_2661 {
    private static int n;
    private static boolean chk;
    private static void solution(int depth, int[] arr) {
        for(int i = 1; i <= (depth - 1) / 2; i++) {
            int sum = 0;
            for(int idx1 = (depth - 1), idx2 = (depth - i - 1); idx1 > (depth - i - 1); idx1--, idx2--) {
                if(arr[idx1] == arr[idx2]) sum++;
            }

            if(sum == i)
                return;
        }

        if(depth == n + 1) {
            chk = true;
            return;
        }


        for(int num = 1; num <= 3; num++) {
            arr[depth] = num;
            solution(depth + 1, arr);
            if(chk)
                return;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 2];

        solution(1, arr);

        for(int i = 1; i <= n; i++) {
            sb.append(arr[i]);
        }

        System.out.println(sb);
    }
}
