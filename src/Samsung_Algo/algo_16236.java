package Samsung_Algo;
import java.io.*;
import java.util.*;

public class algo_16236 {
    static int Time = 0;
    static int BabyShark = 2, Eaten = 0, N = 0;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] Arr;
//    static Queue<>
    static boolean[][] Exist;//먹을게 있는지 확인하기 위한 boolean형 2차원 배열
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int Shark_x = 0, Shark_y = 0;

        N = Integer.parseInt(br.readLine());
        Arr = new int[N][N];
        Exist = new boolean[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                Arr[i][j] = Integer.parseInt(stk.nextToken());
                if(Arr[i][j] == 9) {
                    Shark_x = i;
                    Shark_y = j;
                }
                if(Arr[i][j] == 0) {
                    Exist[i][j] = true;
                }
            }
        }
        //1. 현재 상어의 위치에서 먹을 수 있는 물고기를 모두 탐색 ( bfs )
            //1-1. 탑색을 하기전에 방문관련 배열 초기화 및 최소거리 변수 초기화
            //1-2. 탑색을 하면서 각 물고기의 거리에 대해 최소거리를 갖는 물고기에 대한 정보 가지고 있어야함
            //1-3. 방문했거나 배열을 넘어서는 경우 BackTracking
        //2. 탐색이 끝나면 현재 상어의 위치에서 먹을 수 있는 물고기 -> 최단거리의 물고기를 먹고 상어의 위치 초기화 시켜주고 해당 물고기가 있는 곳 0 으로 초기화
        //3. 현재 먹을 수 있는 물고기가 없을 경우 종료하고 엄마 상어를 부른다.
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static void BFS(){

    }
}
class BranchInfo {
    int x;
    int y;

}
