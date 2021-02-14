package BOJ.BinarySearch.Silver;
import java.io.*;
import java.util.*;

public class boj_10816 {
    static int N, M;
    static int[] Am;
    static HashMap<Integer, Integer> An = new HashMap<Integer, Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        StringTokenizer stk = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int val = Integer.parseInt(stk.nextToken());
            if(An.containsKey(val)) {
                int cnt = An.get(val);
                cnt++;
                An.put(val, cnt);
            } else {
                An.put(val, 1);
            }
        }

        M = Integer.parseInt(br.readLine());
        Am = new int[M];
        stk = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            Am[i] = Integer.parseInt(stk.nextToken());
        }

        for(int i = 0; i < M; i++) {
            if(An.containsKey(Am[i])) {
                sb.append(An.get(Am[i])).append(" ");
            } else {
                sb.append("0 ");
            }
        }

        sb.trimToSize();
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}
