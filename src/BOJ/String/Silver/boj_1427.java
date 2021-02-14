package BOJ.String.Silver;

import java.io.*;
import java.util.*;

public class boj_1427 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringBuilder zero = new StringBuilder();
        String N = br.readLine();
        int[] arr = new int[N.length()];

        for(int i = 0; i < N.length(); i++) {
            arr[i] = Character.getNumericValue(N.charAt(i));
        }


        Arrays.sort(arr);

        for(int i = arr.length - 1; i >= 0; i--) {
            if(arr[i] != 0) sb.append(arr[i]);
            else zero.append(0);
        }
        sb.append(zero);
        sb.append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}
