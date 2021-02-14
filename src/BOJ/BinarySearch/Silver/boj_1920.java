package BOJ.BinarySearch.Silver;
import java.io.*;
import java.util.*;

public class boj_1920 {
    static int N, M;
    static int[] An, Am;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        StringTokenizer stk = new StringTokenizer(br.readLine());
        An = new int[N];

        for(int i = 0; i < N; i++) {
            An[i] = Integer.parseInt(stk.nextToken());
        }

        Arrays.sort(An);


        M = Integer.parseInt(br.readLine());
        stk = new StringTokenizer(br.readLine());
        Am = new int[M];

        for(int i = 0; i < M; i++) {
            sb.append(BSearch(Integer.parseInt(stk.nextToken()))).append("\n");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static int BSearch(int input) {
        int start = 0, end = N;

        while(start < end) {
            int mid = (start + end) / 2;

            if(input > An[mid]) {
                start = mid + 1;
            } else if(input < An[mid]) {
                end = mid;
            } else return 1;
        }
        return 0;
    }
}
