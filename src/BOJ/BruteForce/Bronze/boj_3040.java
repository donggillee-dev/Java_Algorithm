package BOJ.BruteForce.Bronze;

import java.io.*;

public class boj_3040 {
    private static int[] arr = new int[9];
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 9; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        for(int a = 0; a < 3; a++) {
            for(int b = a + 1; b < 4; b++) {
                for(int c = b + 1; c < 5; c++) {
                    for(int d = c + 1; d < 6; d++) {
                        for(int e = d + 1; e < 7; e++) {
                            for(int f = e + 1; f < 8; f++) {
                                for(int g = f + 1; g < 9; g++) {
                                    if(arr[a] + arr[b] + arr[c] + arr[d] + arr[e] + arr[f] + arr[g] == 100) {
                                        sb.append(arr[a]).append("\n")
                                                .append(arr[b]).append("\n")
                                                .append(arr[c]).append("\n")
                                                .append(arr[d]).append("\n")
                                                .append(arr[e]).append("\n")
                                                .append(arr[f]).append("\n")
                                                .append(arr[g]).append("\n");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        bw.write(String.valueOf(sb));
        bw.close();
        br.close();
    }
}
