package BOJ_Binary_Search;
        import java.io.*;

public class algo_1300 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int start = 1, end = K, ans = 0;

        while(start <= end) {
            int mid = (start + end) / 2;
            int cnt = 0;
            for(int i = 1; i <= N; i++) { // N * N 배열의 각 행은 i의 배수
                cnt += Math.min(mid / i, N); //행 전채 원소 개수 or mid값을 i로 나눴을시 몫이 mid보다 작은 값들의 개수
            }

            if(cnt >= K) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}