package BOJ.BOJ_Brute_Force;

import java.io.*;
import java.util.*;

public class algo_7568 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;

        int N = Integer.parseInt(br.readLine());
        int[][] PeopleWeightHeight = new int[N][3];

        for(int i = 0; i < N; i++) { //input data to array
            stk = new StringTokenizer(br.readLine());
            PeopleWeightHeight[i][0] = Integer.parseInt(stk.nextToken());
            PeopleWeightHeight[i][1] = Integer.parseInt(stk.nextToken());
        }

        for(int i = 0; i < N; i++) {//Brute Force Algorithm
            int IsBig = 0;
            for(int j = 0; j < N; j++) {
                if(i == j) continue;//Don't compare data of same person

                if(PeopleWeightHeight[j][0] > PeopleWeightHeight[i][0] && PeopleWeightHeight[j][1] > PeopleWeightHeight[i][1])
                    IsBig++;
            }
            PeopleWeightHeight[i][2] = IsBig + 1;
        }

        for(int i = 0; i < N; i++) {//Output Result by StringBuilder
            sb.append(PeopleWeightHeight[i][2]);
            if(i == N -1) sb.append("\n");
            else sb.append(" ");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}
