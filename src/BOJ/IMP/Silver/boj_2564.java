package BOJ.IMP.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2564 {
    private static int[] parseDir(int dir, int pos, int row, int col) {
        int r = 0, c = 0;
        if(dir == 1) {
            r = 0;
            c = pos;
        } else if(dir == 2) {
            r = row;
            c = pos;
        } else if(dir == 3) {
            r = pos;
            c = 0;
        } else {
            r = pos;
            c = col;
        }

        return new int[]{r, c};
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(stk.nextToken());
        int R = Integer.parseInt(stk.nextToken());
        int N = Integer.parseInt(br.readLine());

        ArrayList<int[]> storeList = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            storeList.add(parseDir(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()), R, C));
        }

        int ans = 0;
        stk = new StringTokenizer(br.readLine());
        int[] start = parseDir(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()), R, C);

        for(int[] elem : storeList) {
            int r = elem[0], c = elem[1];

            if(start[0] == r) {
                ans += Math.abs(c - start[1]);
            } else {
                int left = Math.abs(0 - start[1]) + R + Math.abs(0 - c);
                int right = Math.abs(C - start[1]) + R + (C - c);

                if(c == 0 || c == C) {

                    if(start[0] == R) {
                        left -= r;
                        right -= r;
                    } else {
                        left -= (R - r);
                        right -= (R - r);
                    }
                }
                ans += Math.min(left, right);
            }
        }
        System.out.println(ans);
    }
}
