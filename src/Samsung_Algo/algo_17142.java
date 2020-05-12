package Samsung_Algo;
import java.io.*;
import java.util.*;

public class algo_17142 {
    static int N, M, VirusCnt;
    static int[][] VirusLoc;
    static int[][] Lab;
    static Stack<VirusInfo> S = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine()); //Input N, M;
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        Lab = new int[N][N];
        VirusLoc = new int[10][2];

        for(int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                Lab[i][j] = Integer.parseInt(stk.nextToken());
                if(Lab[i][j] == 2) {
                    VirusLoc[VirusCnt][0] = i;
                    VirusLoc[VirusCnt][1] = j;
                    VirusCnt++;
                }
            }
        }
        PutVirus(0, 0);
        bw.flush();
        bw.close();
        br.close();
        return;
    }

    private static void PutVirus(int start, int cnt) {
        if(cnt == M) {
            BFS();
            return;
        } else {
            for(int i = start; i < VirusCnt; i++) {
//                System.out.println(VirusLoc[i][0] + " " + VirusLoc[i][1]);
                S.push(new VirusInfo(VirusLoc[i][0], VirusLoc[i][1]));
                PutVirus(i + 1, cnt + 1);
                S.pop();
            }
        }
    }

    private static void BFS() {
        Stack<VirusInfo> Stack = new Stack<>();
        Stack.addAll(S);
        System.out.println(S.size());
        Stack.pop();
        System.out.println(S.size());
    }
}
class VirusInfo {
    int x;
    int y;
    public VirusInfo(int nx, int ny) {
        this.x = nx;
        this.y = ny;
    }
}
