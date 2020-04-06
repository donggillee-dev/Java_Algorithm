package DP;

import java.io.*;
import java.util.*;

public class algo_2565 {
    static int N;
    static int cnt = 0;
    static int[] DP;
    static ArrayList<Info> An;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;
        int idx = 1, lis = 0;
        N = Integer.parseInt(br.readLine());
        DP = new int[N];
        An = new ArrayList<Info>();
        for(int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            Info tmp = new Info(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()));
            An.add(tmp);
        }

        Collections.sort(An); //A 전봇대에 대해 정렬 -> B전봇대의 엉킴을 보기 위함
        DP[0] = An.get(0).Right;

        while(idx < N) {
            int right = An.get(idx).Right;
            if(DP[lis] < right) {//B전봇대에 대해 오름차순인지 확인 맞을경우 DP배열에  그대로 추가
                DP[++lis] = right;
            } else {//오름차순이 아닌 섞여 있는 전깃줄이 있으면 잘라야함
                int ret = lower_bound(lis, right); //해당 전깃줄이 원래 있어야하는 위치로
                DP[ret] = right;//값 재설정
                cnt++;
            }
            idx++;
        }

        sb.append(cnt).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }

    private static int lower_bound(int end, int val) {
        int start = 0, mid;
        while(start < end) {
            mid = (start + end) / 2;

            if(DP[mid] < val) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return end;
    }
}

class Info implements Comparable<Info>{
    int Left;
    int Right;

    public Info(int A, int B) {
        this.Left = A;
        this.Right = B;
    }
    @Override
    public int compareTo(Info info) {
        if(this.Left < info.Left) {
            return -1;
        } else if(this.Left > info.Left){
            return 1;
        }
        return 0;
    }
}