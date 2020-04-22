package Binary_Search;
import java.io.*;
import java.util.*;

public class algo_2100 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stk.nextToken()), C = Integer.parseInt(stk.nextToken());
        int min, max, ans = 0;
        int[] Position = new int[N];

        for(int i = 0; i < N; i++) {
            Position[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(Position);
        min = 1; //집 사이 최소거리
        max = Position[N - 1] - Position[0]; //집 사이 최대거래

        while(min <= max) {
            int mid = (min + max) / 2; //중간값 거리
            int cnt = 1; //공유기는 시작점에 하나 설치했다고 생각
            int point = Position[0]; //시작점에는 하나 설치했다고 생각

            for(int i = 1; i < N; i++) {
                int d = Position[i] - point;
                if(d >= mid) { //설정한 거리값 mid보다 작으므로 현재 설치지점 point에 널고 cnt는 증가
                    cnt++;
                    point = Position[i];
                }
            }

            if(cnt < C) { //설치해야되는 갯수보다 덜 설치했으니까 간격 줄이자
                max = mid - 1;
            } else { //설치해야하는 갯수보다 더 설치했으니까 간격 넓힘
                if(ans < mid) ans = mid;
                min = mid + 1;

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
