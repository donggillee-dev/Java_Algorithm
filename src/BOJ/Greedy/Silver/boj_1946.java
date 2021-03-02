package BOJ.Greedy.Silver;

import java.io.*;
import java.util.StringTokenizer;
// Logic

// 서류 성적순으로 사람들을 나열해 놓고 그중에 면접성적을 O(N)으로 탐색하면서 서류가 현재 기준점보다 낮은 사람이 나오면
// 넘길 수 있는 사람 1명 줄여주고 걔로 기준점 다시 잡음

// 풀이 시간 : 40분
public class boj_1946 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int TestCase = Integer.parseInt(br.readLine());
        StringTokenizer stk;

        for(int i = 0; i < TestCase; i++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N + 1];
            for(int j = 0; j < N; j++) {
                stk = new StringTokenizer(br.readLine());
                arr[Integer.parseInt(stk.nextToken())] =  Integer.parseInt(stk.nextToken());
            }

            int num = arr[1];
            int answer = N;
            for(int j = 2; j <= N; j++) {
                if(arr[j] > num) answer--;
                if(arr[j] < num) num = arr[j];
            }
            sb.append(answer).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
