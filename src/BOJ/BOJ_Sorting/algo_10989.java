package BOJ.BOJ_Sorting;

import java.io.*;

public class algo_10989 {//QuickSort & MergeSort is not possible (Time & Memory Exceed))
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[10001];

        for(int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());
            arr[input]++;
        }

        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[i]; j++) {
                sb.append(i).append("\n");
            }
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}