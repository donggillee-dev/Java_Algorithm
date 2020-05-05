package Samsung_Algo;
import java.io.*;
import java.util.*;

public class algo_14499 {
    static int N, M, DiceX, DiceY, K;
    static int[][] Map;
    static int[] Command;
    static int[] DirX = {0, 0, 0, -1, 1};
    static int[] DirY = {0, 1, -1, 0, 0};
    static int[][] Dice = new int[4][3];
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        DiceX = Integer.parseInt(stk.nextToken());
        DiceY = Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(stk.nextToken());
        Map = new int[N][M];
        Command = new int[K];

        for(int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                Map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        stk = new StringTokenizer(br.readLine());

        for(int i = 0; i < K; i++) {
            Command[i] = Integer.parseInt(stk.nextToken());
        }

        for(int i = 0; i < 4; i++) {
            Arrays.fill(Dice[i], 0);
        }

        for(int i = 0; i < K; i++) {
            if(RollDice(Command[i]))
                sb.append(Dice[1][1]).append("\n");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static boolean RollDice(int dir) {
        int tmp = Dice[1][1];
        int nx = DiceX + DirX[dir];
        int ny = DiceY + DirY[dir];
        boolean isZero = true;
        if(nx < 0 || nx > N -1 || ny < 0 || ny > M - 1) {
            return false;
        }
        if(Map[nx][ny] == 0) isZero = true;
        else isZero = false;

        switch ( dir ) {
            case 1:
                Dice[1][1] = Dice[1][0];
                Dice[1][0] = Dice[3][1];
                Dice[3][1] = Dice[1][2];
                Dice[1][2] = tmp;
                break;

            case 2:
                Dice[1][1] = Dice[1][2];
                Dice[1][2] = Dice[3][1];
                Dice[3][1] = Dice[1][0];
                Dice[1][0] = tmp;
                break;

            case 3:
                Dice[1][1] = Dice[2][1];
                Dice[2][1] = Dice[3][1];
                Dice[3][1] = Dice[0][1];
                Dice[0][1] = tmp;
                break;

            case 4:
                Dice[1][1] = Dice[0][1];
                Dice[0][1] = Dice[3][1];
                Dice[3][1] = Dice[2][1];
                Dice[2][1] = tmp;
                break;
        }

        if(isZero) {
            Map[nx][ny] = Dice[3][1];
        } else {
            Dice[3][1] = Map[nx][ny];
            Map[nx][ny] = 0;
        }
        DiceX = nx;
        DiceY = ny;
        return true;
    }
}