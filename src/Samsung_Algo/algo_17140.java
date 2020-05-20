package Samsung_Algo;
import java.io.*;
import java.util.*;
public class algo_17140 {
    private static class Info {
        int x, y;
        int val;
        public Info(int nx, int ny, int v) {
            this.x = nx;
            this.y = ny;
            this.val = v;
        }
    }
    static int Row = 3, Column = 3;
    static int R, C, K;
    static int ans = 0;
    static LinkedList<Info> List = new LinkedList<>();

    public static void main(String[] args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        R = Integer.parseInt(stk.nextToken());
        C = Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(stk.nextToken());
        loop1:for(int i = 1; i <= 3; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 1; j <= 3; j++) {
                int val = Integer.parseInt(stk.nextToken());
                if(val == K && i == R && j == K) {
                    break loop1;
                }
                List.add(new Info(i, j, val));
            }
        }
        if(ans > 100) ans = -1;
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static void solve() {
        Collections.sort(List, Comparator.comparingInt(o -> o.x));//행에 대해 정렬
        Collections.sort(List, Comparator.comparingInt(o -> o.y));//열에 대해 정렬
        Collections.sort(List, Comparator.comparingInt(o -> o.val));//값에 대해 정렬
    }
}