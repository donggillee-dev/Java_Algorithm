package BOJ.BOJ_Samsung_Category;
import java.io.*;
import java.util.*;

public class algo_16235 {
    static class Tree {
        int x;
        int y;
        int z;
        public Tree(int nx, int ny, int nz) {
            this.x = nx;
            this.y = ny;
            this.z = nz;
        }
    }

    static int N, M, K, TreeCnt;
    static LinkedList<Tree> Trees = new LinkedList<>();
    static int[][] Ground;
    static int[][] A;
    static int[][] Dead;
    static int[] dir_x= {-1,-1,-1,0,0,1,1,1};
    static int[] dir_y= {-1,0,1,-1,1,-1,0,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(stk.nextToken());
        Ground = new int[N + 1][N + 1];
        A = new int[N + 1][N + 1];
        Dead = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                Ground[i][j] = 5;
                A[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        for(int i = 0; i < M; i++) {
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            int z = Integer.parseInt(stk.nextToken());
            Trees.add(new Tree(x, y, z));
        }
        Collections.sort(Trees, Comparator.comparingInt(o -> o.z));
        Grow();
        sb.append(Trees.size()).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static void Grow() {
        int nth_year = 0;
        Tree cur_tree;
        LinkedList<Tree> newTrees = new LinkedList<>();
        while(nth_year < K) {
            //Spring && Fall
            for(Iterator<Tree> tree = Trees.iterator(); tree.hasNext();) {
                cur_tree = tree.next();
                int x = cur_tree.x;
                int y = cur_tree.y;
                int z = cur_tree.z;

                if(Ground[x][y] >= z) {//양분 먹기
                    Ground[x][y] -= z;
                    cur_tree.z++;

                    if(cur_tree.z % 5 == 0) {
                        int nx;
                        int ny;
                        for(int i = 0; i < 8; i++) {
                            nx = x + dir_x[i];
                            ny = y + dir_y[i];

                            if(nx < 1 || ny < 1 || nx > N || ny > N) continue;
                            newTrees.add(new Tree(nx, ny, 1));
                        }
                    }
                } else {//죽은 나무 처리
                    Dead[x][y] += cur_tree.z / 2;
                    tree.remove();
                }
            }
            Trees.addAll(0, newTrees);
            newTrees.clear();

            //Summer && Winter
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    Ground[i][j] += Dead[i][j];
                    Ground[i][j] += A[i][j];
                    Dead[i][j] = 0;
                }
            }
            nth_year++;
        }
    }
}