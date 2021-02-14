package BOJ.BFS.Gold;

import java.io.*;
import java.util.*;

public class boj_2580 {
    static int N = 9;
    static int cnt = 0;
    static int[][] SdokuMap = new int[9][9];
    static ArrayList<DotInfo> ZeroPos = new ArrayList<DotInfo>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                SdokuMap[i][j] = Integer.parseInt(stk.nextToken());
                if(SdokuMap[i][j] == 0) {
                    ZeroPos.add(new DotInfo(i, j, 0));
                    cnt++;
                }
            }
        }
        dfs(0);
        for(int i = 0; i < N; i++) {
            String str = "";
            for(int j = 0; j < N; j++) {
                str += SdokuMap[i][j];
                str += " ";
            }
            str.trim();
            sb.append(str);
            if(i != N -1) sb.append("\n");
        }
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }

    private static boolean dfs(int idx) {
        if(idx >= cnt) return true;
        else {
            DotInfo tmp = ZeroPos.get(idx);
            for(int i = 1; i <= N; i++) {
                tmp.data = i;
                SdokuMap[tmp.x][tmp.y] = tmp.data;
                if(isPossible(idx)) {
                    if(dfs(idx + 1)) return true;
                }
                SdokuMap[tmp.x][tmp.y] = 0;
            }
        }
        return false;
    }

    private static boolean isPossible(int idx) {
        DotInfo tmp = ZeroPos.get(idx);
        int x = tmp.x;
        int y = tmp.y;
        int start_x = x / 3;
        int start_y = y / 3;
        boolean arr[] = new boolean[N + 1];
        Arrays.fill(arr, false);

        //같은 행에 대해서 중복검사
        for(int i = 0; i < N; i++) {
            if( SdokuMap[x][i] != 0) {
                if(arr[ SdokuMap[x][i] ]) return false;
                else arr[ SdokuMap[x][i] ] = true;
            }
        }
        Arrays.fill(arr, false);

        //같은 열에 대해서 중복검사
        for(int i = 0; i < N; i++) {
            if(SdokuMap[i][y] != 0) {
                if(arr[ SdokuMap[i][y] ]) return false;
                else arr[ SdokuMap[i][y] ] = true;
            }
        }
        Arrays.fill(arr, false);

        //3*3 내애세 중복검사

        for(int i = start_x * 3; i < (start_x + 1) * 3; i++) {
            for(int j = start_y * 3; j < (start_y + 1) * 3; j++) {
                if(SdokuMap[i][j] != 0) {
                    if(arr[ SdokuMap[i][j] ]) return false;
                    else arr[ SdokuMap[i][j] ] = true;
                }
            }
        }
        return true;
    }
}
class DotInfo {
    int x;
    int y;
    int data;

    DotInfo(int x1, int y1, int data1) {
        x = x1;
        y = y1;
        data = data1;
    }
}
