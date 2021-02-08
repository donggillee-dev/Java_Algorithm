package BOJ.BOJ_StepByStep.Gold;

import java.io.*;
import java.util.*;

public class algo_20440 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        TreeSet<Integer> tSet = new TreeSet<>();
        StringTokenizer stk;
        int[] stArr = new int[N];
        int[] enArr = new int[N];
        for(int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            int stIdx = Integer.parseInt(stk.nextToken());
            int enIdx = Integer.parseInt(stk.nextToken());
            stArr[i] = stIdx;
            enArr[i] = enIdx;
            tSet.add(stIdx);
            tSet.add(enIdx);
        }
        ArrayList<Integer> idxList = new ArrayList<>(tSet);
        int[] partArr = new int[idxList.size()];

        for(int i = 0; i < N; i++) {
            int start = Collections.binarySearch(idxList, stArr[i]);
            int end = Collections.binarySearch(idxList, enArr[i]);

            for(int idx = start; idx < end; idx++) {
                partArr[idx]++;
            }
        }
        int maxStartIdx = 0;
        int maxVal = -1;
        int maxEndIdx = 0;

        for(int i = 0; i < partArr.length; i++) {
            if(partArr[i] > maxVal) {
                maxStartIdx = i;
                maxVal = partArr[i];
                maxEndIdx = i;
            }

            if(i-1 == maxEndIdx && partArr[i] == maxVal) {
                maxEndIdx = i;
            }
        }

        sb.append(maxVal).append("\n").append(idxList.get(maxStartIdx)).append(" ").append(idxList.get(maxEndIdx + 1)).append("\n");
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
