package Sorting;

import java.io.*;
import java.util.*;

public class algo_2108 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] check = new int[8001];
        int sum = 0;

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            check[arr[i] + 4000]++;
            sum += arr[i];
        }

        int maxidx = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < 8001; i++) {
            if(check[maxidx] < check[i]) {
                maxidx = i;
                list.clear();
            } else if(check[i] != 0 && check[i] == check[maxidx]) {
                list.add(i - 4000);
            }
        }

        Arrays.sort(arr);

        sb.append((int)Math.round((double)sum / N)).append("\n");
        sb.append(arr[N / 2]).append("\n");
        if(list.size() != 0) sb.append(list.get(0)).append("\n");
        else sb.append(maxidx - 4000).append("\n");

        sb.append(arr[N -1] - arr[0]).append("\n");

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}