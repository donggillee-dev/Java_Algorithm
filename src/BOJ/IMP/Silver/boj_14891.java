package BOJ.IMP.Silver;
import java.io.*;
import java.util.*;

public class boj_14891 {
    static int LeftSideIdx = 6, RightSideIdx = 2, UpperSideIdx = 0, K = 0, N = 4, M = 8;
    static boolean[][] Gears = new boolean[N + 1][M];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            String Gear = br.readLine();
            for(int j = 0; j < Gear.length(); j++) {
                if(Gear.charAt(j) == '0')
                    Gears[i][j] = false;
                else
                    Gears[i][j] = true;
            }
        }
        K = Integer.parseInt(br.readLine());
        for(int i = 0; i < K; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int nthGear = Integer.parseInt(stk.nextToken());
            int Direction = Integer.parseInt(stk.nextToken());
            int OppDirec = Direction * -1;
            boolean First = false, Second = false, Third = false, Fourth = false;

            if(nthGear == 1) {
                First = true;
                if(Gears[nthGear][RightSideIdx] != Gears[nthGear + 1][LeftSideIdx])
                    Second = true;
                if(Second && Gears[nthGear + 1][RightSideIdx] != Gears[nthGear + 2][LeftSideIdx])
                    Third = true;
                if(Third && Gears[nthGear + 2][RightSideIdx] != Gears[nthGear + 3][LeftSideIdx])
                    Fourth = true;

                if(Second) Rotate(nthGear + 1, OppDirec);
                if(Third) Rotate(nthGear + 2, Direction);
                if(Fourth) Rotate(nthGear + 3, OppDirec);
                Rotate(nthGear, Direction);

            } else if(nthGear == N) {
                Fourth = true;
                if(Gears[nthGear][LeftSideIdx] != Gears[nthGear - 1][RightSideIdx])
                    Third = true;
                if(Third && Gears[nthGear - 1][LeftSideIdx] != Gears[nthGear - 2][RightSideIdx])
                    Second = true;
                if(Second && Gears[nthGear - 2][LeftSideIdx] != Gears[nthGear - 3][RightSideIdx])
                    First = true;

                if(Third) Rotate(nthGear - 1, OppDirec);
                if(Second) Rotate(nthGear - 2, Direction);
                if(First) Rotate(nthGear - 3, OppDirec);
                Rotate(nthGear, Direction);

            } else if(nthGear == 3) {
                Third = true;
                if(Gears[nthGear][RightSideIdx] != Gears[nthGear + 1][LeftSideIdx])
                    Fourth = true;
                if(Gears[nthGear][LeftSideIdx] != Gears[nthGear - 1][RightSideIdx])
                    Second = true;
                if(Second && Gears[nthGear - 1][LeftSideIdx] != Gears[nthGear - 2][RightSideIdx])
                    First = true;

                if(Fourth) Rotate(nthGear + 1, OppDirec);
                if(Second) Rotate(nthGear - 1, OppDirec);
                if(First) Rotate(nthGear - 2, Direction);
                Rotate(nthGear, Direction);

            } else {
                Second = true;
                if(Gears[nthGear][RightSideIdx] != Gears[nthGear + 1][LeftSideIdx])
                    Third = true;
                if(Gears[nthGear][LeftSideIdx] != Gears[nthGear - 1][RightSideIdx])
                    First = true;
                if(Third && Gears[nthGear + 1][RightSideIdx] != Gears[nthGear + 2][LeftSideIdx])
                    Fourth = true;

                if(Third) Rotate(nthGear + 1, OppDirec);
                if(First) Rotate(nthGear - 1, OppDirec);
                if(Fourth) Rotate(nthGear + 2, Direction);
                Rotate(nthGear, Direction);
            }
        }

        sb.append(GetScore()).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static void Rotate(int nthGear, int Direction) {
        boolean tmp;
        if(Direction == -1) {
            tmp = Gears[nthGear][0];
            for(int i = 0; i < M - 1; i++)
                Gears[nthGear][i] = Gears[nthGear][i + 1];
            Gears[nthGear][M - 1] = tmp;
        } else {
            tmp = Gears[nthGear][M - 1];
            for(int i = M - 1; i > 0; i--)
                Gears[nthGear][i] = Gears[nthGear][i - 1];
            Gears[nthGear][0] = tmp;
        }
        return;
    }
    private static int GetScore() {
        int ans = 0;
        if(Gears[1][UpperSideIdx]) ans += 1;
        if(Gears[2][UpperSideIdx]) ans += 2;
        if(Gears[3][UpperSideIdx]) ans += 4;
        if(Gears[4][UpperSideIdx]) ans += 8;
        return ans;
    }
}
