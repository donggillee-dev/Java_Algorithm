package BOJ_StepByStep;
import java.io.*;
import java.util.*;

public class algo_2217 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int max;
        Integer[] rope_arr = new Integer[N];

        for(int i = 0; i < N; i++) {
            rope_arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(rope_arr, Collections.reverseOrder());
        max = rope_arr[0];

        for(int i = 1; i < rope_arr.length; i++) {
            if(max < (rope_arr[i] * (i + 1))) {
                max = (rope_arr[i] * (i + 1));
            }
        }
        sb.append(max).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}
